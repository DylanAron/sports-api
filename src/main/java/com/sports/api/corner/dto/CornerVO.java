package com.sports.api.corner.dto;

import com.sports.api.corner.entity.Corner;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CornerVO {
    private Long id;
    private String leagueName;
    private String leagueLogo;
    private String homeName;
    private String homeLogo;
    private String awayLogo;
    private String awayName;
    private String recommendContent;
    private Integer isTodayData;
    private Integer isHit;
    private LocalDateTime matchDate;

    public static CornerVO from(Corner entity) {
        CornerVO vo = new CornerVO();
        vo.setId(entity.getId());
        vo.setLeagueName(entity.getLeagueName());
        vo.setLeagueLogo(entity.getLeagueLogo());
        vo.setHomeName(entity.getHomeName());
        vo.setHomeLogo(entity.getHomeLogo());
        vo.setAwayLogo(entity.getAwayLogo());
        vo.setAwayName(entity.getAwayName());
        vo.setRecommendContent(entity.getRecommendContent());
        vo.setIsTodayData(entity.getIsTodayData());
        vo.setIsHit(entity.getIsHit());
        vo.setMatchDate(entity.getMatchDate());
        return vo;
    }
}
