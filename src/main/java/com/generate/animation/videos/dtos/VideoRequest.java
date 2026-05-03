package com.generate.animation.videos.dtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VideoRequest {
    private String inputPath;
    private String outputPath;
}