package com.sports.api.attribution.service.impl;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.attribution.entity.Attribution;
import com.sports.api.attribution.mapper.AttributionMapper;
import com.sports.api.attribution.service.AttributionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttributionServiceImpl extends ServiceImpl<AttributionMapper, Attribution>
        implements AttributionService {

    private final com.sports.api.config.AppTrackConfig appTrackConfig;

    @Override
    public boolean handleCallback(Map<String, String> params) {
        log.info("Received Baidu AppTrack callback, raw params: {}", params);

        // 测试/开发环境的数据不存库
        String env = params.get("env");
        if (env != null && !env.equals("prod")) {
            log.info("Test/dev callback skipped (env={}), not storing", env);
            return true;
        }

        // 1. 提取并验证签名
        String signature = params.remove("signature");
        if (signature == null || signature.isBlank()) {
            log.warn("Callback missing signature parameter");
            return false;
        }

        if (!verifySignature(params, signature)) {
            log.warn("Callback signature verification FAILED");
            return false;
        }
        log.info("Callback signature verification PASSED");

        // 2. 映射参数到实体
        Attribution record = mapParamsToEntity(params);

        // 3. 幂等检查：同一设备相同回调类型和时间的记录视为重复
        if (isDuplicate(record)) {
            log.info("Duplicate callback detected, skipped. oaid={}, type={}, time={}",
                    record.getOaid(), record.getCallbackType(), record.getCallbackTime());
            return true; // 重复回调不报错，返回true即可
        }

        // 4. 持久化
        save(record);
        log.info("Attribution record saved. oaid={}, channelId={}, type={}",
                record.getOaid(), record.getChannelId(), record.getCallbackType());
        return true;
    }

    /**
     * 验证百度回调签名
     * 算法: HMAC-SHA256
     * 步骤:
     *   1. 所有参数按 key 字母序排序（已移除 signature）
     *   2. 拼接为 key=value&key=value... 格式
     *   3. 追加密钥: sortedString + appSecret
     *   4. 以 appSecret 为密钥，计算 HMAC-SHA256
     *   5. 十六进制编码后与 signature 比较（不区分大小写）
     */
    private boolean verifySignature(Map<String, String> params, String signature) {
        String appSecret = appTrackConfig.getAppSecret();
        if (appSecret == null || appSecret.isBlank()) {
            log.error("AppTrack app_secret is not configured!");
            return false;
        }

        // 按 key 字母序排序
        TreeMap<String, String> sortedMap = new TreeMap<>(params);

        // 拼接为 key=value&key=value...
        String sortedString = sortedMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        // 待签名字符串 = 排序后参数 + appSecret（不额外加 & 分隔符）
        String stringToSign = sortedString + appSecret;

        // 计算 HMAC-SHA256
        HMac hmac = new HMac(HmacAlgorithm.HmacSHA256, appSecret.getBytes(StandardCharsets.UTF_8));
        String computedSignature = hmac.digestHex(stringToSign);

        log.debug("Signature verification - sorted params: {}, computed: {}, received: {}",
                sortedString, computedSignature, signature);

        return computedSignature.equalsIgnoreCase(signature);
    }

    /**
     * 将百度回调参数映射到 Attribution 实体
     */
    private Attribution mapParamsToEntity(Map<String, String> params) {
        Attribution record = new Attribution();

        // 百度回调标准参数
        record.setAid(params.get("aid"));
        record.setPid(params.get("pid"));
        record.setUid(params.get("uid"));
        record.setUserid(params.get("userid"));
        record.setClickId(params.get("click_id"));
        record.setCombId(params.get("comb_id"));

        // 设备标识
        record.setOaid(params.getOrDefault("oaid", params.get("device_id")));
        record.setOaidMd5(params.get("oaid_md5"));
        record.setImei(params.get("imei"));
        record.setImeiMd5(params.get("imei_md5"));
        record.setAndroidId(params.get("android_id"));
        record.setAndroidIdMd5(params.get("androidid_md5"));
        record.setMac(params.get("mac"));
        record.setMacMd5(params.get("mac_md5"));
        record.setIdfa(params.get("idfa"));

        // 设备信息
        record.setIp(params.get("ip"));
        record.setUa(params.get("ua"));
        String osStr = params.get("os");
        if (osStr != null && !osStr.isBlank()) {
            try { record.setOs(Integer.parseInt(osStr)); } catch (NumberFormatException ignored) {}
        }
        record.setSize(params.get("size"));

        // 渠道信息
        record.setChannelId(params.get("channel_id"));
        record.setCampaignId(params.get("campaign_id"));
        record.setAdId(params.get("ad_id"));
        record.setAdName(params.get("ad_name"));

        // 百度特有标识
        record.setBdVid(params.get("bd_vid"));
        record.setDeeplinkUrl(params.get("deeplink_url"));
        record.setExtInfo(params.get("ext_info"));

        // 回调类型
        String typeStr = params.getOrDefault("callback_type", params.get("t"));
        if (typeStr != null && !typeStr.isBlank()) {
            try {
                record.setCallbackType(Integer.parseInt(typeStr));
            } catch (NumberFormatException e) {
                log.warn("Invalid callback_type value: {}", typeStr);
                record.setCallbackType(0);
            }
        } else {
            record.setCallbackType(0);
        }

        // 回调时间（秒级时间戳）
        String timeStr = params.get("callback_time");
        if (timeStr != null && !timeStr.isBlank()) {
            try {
                long timestamp = Long.parseLong(timeStr);
                record.setCallbackTime(LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(timestamp), ZoneId.of("Asia/Shanghai")));
            } catch (NumberFormatException e) {
                log.warn("Invalid callback_time value: {}", timeStr);
            }
        }

        // 原始时间戳
        String tsStr = params.get("ts");
        if (tsStr != null && !tsStr.isBlank()) {
            try { record.setTs(Long.parseLong(tsStr)); } catch (NumberFormatException ignored) {}
        }

        // 原始回调数据持久化
        record.setCallbackData(JSONUtil.toJsonStr(params));
        record.setStatus(0);

        return record;
    }

    /**
     * 幂等检查：同一OAID + 回调类型 + 回调时间 视为重复回调
     */
    private boolean isDuplicate(Attribution record) {
        if (record.getOaid() == null && record.getImei() == null) {
            return false; // 没有任何设备标识，不太可能重复
        }

        LambdaQueryWrapper<Attribution> wrapper = new LambdaQueryWrapper<>();

        if (record.getOaid() != null) {
            wrapper.eq(Attribution::getOaid, record.getOaid());
        } else {
            wrapper.eq(Attribution::getImei, record.getImei());
        }

        if (record.getCallbackType() != null) {
            wrapper.eq(Attribution::getCallbackType, record.getCallbackType());
        }

        if (record.getCallbackTime() != null) {
            wrapper.eq(Attribution::getCallbackTime, record.getCallbackTime());
        }

        return count(wrapper) > 0;
    }
}
