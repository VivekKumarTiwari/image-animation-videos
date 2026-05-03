package com.generate.animation.videos.controller;

import com.generate.animation.videos.dtos.VideoRequest;
import com.generate.animation.videos.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private VideoService service;

    @PostMapping("/generate")
    public String generate(@RequestBody VideoRequest request) throws Exception {
        return service.processImages(request);
    }
}