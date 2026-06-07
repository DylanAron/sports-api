package com.sports.api.user.controller;

import com.sports.api.common.Result;
import com.sports.api.config.JwtUtil;
import com.sports.api.user.dto.*;
import com.sports.api.user.entity.User;
import com.sports.api.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.login(request.getUsername(), request.getPassword());
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        return Result.success(new LoginResponse(token, UserInfo.from(user)));
    }

    @PostMapping("/register")
    public Result<LoginResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = userService.register(request.getUsername(), request.getPassword(), request.getNickname());
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        return Result.success(new LoginResponse(token, UserInfo.from(user)));
    }

    @GetMapping("/info")
    public Result<UserInfo> info(@RequestAttribute Long userId) {
        User user = userService.getById(userId);
        return Result.success(UserInfo.from(user));
    }

    @PutMapping("/profile")
    public Result<UserInfo> updateProfile(@RequestAttribute Long userId,
                                          @Valid @RequestBody UpdateProfileRequest request) {
        User user = userService.updateProfile(userId, request.getNickname(), request.getAvatar(), request.getBio());
        return Result.success(UserInfo.from(user));
    }
}
