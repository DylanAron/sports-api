package com.sports.api.content.controller;

import com.sports.api.common.Result;
import com.sports.api.content.service.ContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/help")
    public Result<Map<String, String>> help() {
        return Result.success(Map.of("content", contentService.getHelpContent()));
    }

    @GetMapping("/about")
    public Result<Map<String, String>> about() {
        return Result.success(Map.of("content", contentService.getAboutContent()));
    }
}
