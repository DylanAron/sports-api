package com.sports.api.tabguide.dto;

import com.sports.api.tabguide.entity.TabGuide;
import lombok.Data;

@Data
public class TabGuideVO {
    private String tabKey;
    private String imageUrl;
    private Integer isGlobalEnabled;

    public static TabGuideVO from(TabGuide g) {
        TabGuideVO vo = new TabGuideVO();
        vo.setTabKey(g.getTabKey());
        vo.setImageUrl(g.getImageUrl());
        vo.setIsGlobalEnabled(g.getIsGlobalEnabled());
        return vo;
    }
}
