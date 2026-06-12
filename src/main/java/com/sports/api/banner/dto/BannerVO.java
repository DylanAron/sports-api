package com.sports.api.banner.dto;

import com.sports.api.banner.entity.Banner;
import lombok.Data;

@Data
public class BannerVO {
    private Long id;
    private String title;
    private String imageUrl;
    private Integer sortOrder;
    private Integer jumpType;
    private String jumpContent;

    public static BannerVO from(Banner banner) {
        BannerVO vo = new BannerVO();
        vo.setId(banner.getId());
        vo.setTitle(banner.getTitle());
        vo.setImageUrl(banner.getImageUrl());
        vo.setSortOrder(banner.getSortOrder());
        vo.setJumpType(banner.getJumpType());
        vo.setJumpContent(banner.getJumpContent());
        return vo;
    }
}
