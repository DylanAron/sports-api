package com.sports.api.attribution.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_attribution")
public class Attribution {

    @TableId(type = IdType.AUTO)
    private Long id;

    // ========== 百度回调标准参数 ==========

    /** 创意ID（aid） */
    private String aid;

    /** 计划ID（pid） */
    private String pid;

    /** 单元ID（uid） */
    private String uid;

    /** 账户ID（userid） */
    private String userid;

    /** 点击曝光唯一标识（click_id） */
    private String clickId;

    /** 程序化创意组合ID（comb_id） */
    private String combId;

    // ========== 设备标识 ==========

    /** 匿名设备标识符(OAID) */
    private String oaid;

    /** OAID的MD5值 */
    private String oaidMd5;

    /** IMEI设备标识 */
    private String imei;

    /** IMEI的MD5值 */
    private String imeiMd5;

    /** Android ID */
    private String androidId;

    /** Android ID的MD5值 */
    private String androidIdMd5;

    /** MAC地址 */
    private String mac;

    /** MAC地址的MD5值 */
    private String macMd5;

    /** iOS设备标识（IDFA） */
    private String idfa;

    // ========== 设备信息 ==========

    /** IP地址 */
    private String ip;

    /** User Agent */
    private String ua;

    /** 操作系统 1:iOS 2:Android */
    private Integer os;

    /** 屏幕尺寸 */
    private String size;

    // ========== 广告渠道 ==========

    /** 渠道ID */
    private String channelId;

    /** 广告计划ID */
    private String campaignId;

    /** 广告ID */
    private String adId;

    /** 广告名称 */
    private String adName;

    // ========== 转化信息 ==========

    /** 归因回调类型 0:激活 1:注册 2:付费 3:次日留存 */
    private Integer callbackType;

    /** 百度回调的完整原始JSON数据 */
    private String callbackData;

    /** 百度回调时间 */
    private LocalDateTime callbackTime;

    /** 百度回调时间戳 */
    private Long ts;

    /** 百度点击曝光唯一标识（bd_vid） */
    private String bdVid;

    /** 调起URL（deeplink_url） */
    private String deeplinkUrl;

    /** 广告信息透传字段（ext_info） */
    private String extInfo;

    // ========== 客户端上报 ==========

    /** 应用激活时间（客户端上报） */
    private LocalDateTime appActiveTime;

    // ========== 状态 ==========

    /** 处理状态 0:待处理 1:已处理 2:已忽略 */
    private Integer status;

    /** 备注 */
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
