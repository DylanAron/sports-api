package com.sports.api.user.dto;

import com.sports.api.user.entity.User;
import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String username;
    private String nickname;
    private String avatar;
    private String bio;

    public static UserInfo from(User user) {
        UserInfo info = new UserInfo();
        info.setId(user.getId());
        info.setUsername(user.getUsername());
        info.setNickname(user.getNickname());
        info.setAvatar(user.getAvatar());
        info.setBio(user.getBio());
        return info;
    }
}
