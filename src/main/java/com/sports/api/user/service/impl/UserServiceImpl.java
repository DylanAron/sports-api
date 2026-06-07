package com.sports.api.user.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sports.api.common.BusinessException;
import com.sports.api.user.entity.User;
import com.sports.api.user.mapper.UserMapper;
import com.sports.api.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(String username, String password) {
        User user = getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (user.getStatus() == 0) {
            throw new BusinessException("账号已被禁用");
        }
        String encryptedPassword = DigestUtil.sha256Hex(password);
        if (!user.getPassword().equals(encryptedPassword)) {
            throw new BusinessException("密码错误");
        }
        return user;
    }

    @Override
    public User register(String username, String password, String nickname) {
        long count = count(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        if (count > 0) {
            throw new BusinessException("用户名已存在");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtil.sha256Hex(password));
        user.setNickname(nickname != null ? nickname : "用户" + username);
        user.setAvatar(null);
        user.setBio(null);
        save(user);
        return user;
    }

    @Override
    public User updateProfile(Long userId, String nickname, String avatar, String bio) {
        User user = getById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if (nickname != null) {
            user.setNickname(nickname);
        }
        if (avatar != null) {
            user.setAvatar(avatar);
        }
        if (bio != null) {
            user.setBio(bio);
        }
        updateById(user);
        return user;
    }
}
