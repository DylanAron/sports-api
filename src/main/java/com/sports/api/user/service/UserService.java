package com.sports.api.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sports.api.user.entity.User;

public interface UserService extends IService<User> {
    User login(String username, String password);
    User register(String username, String password, String nickname);
    User updateProfile(Long userId, String nickname, String avatar, String bio);
}
