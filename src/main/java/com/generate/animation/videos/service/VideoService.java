package com.generate.animation.videos.service;

import com.generate.animation.videos.dtos.VideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

@Service
public class VideoService {

    @Value("${video.duration}")
    private int duration;
/*

    @Autowired
    private AICaptionService aiService;
*/

    public String processImages(VideoRequest request) throws Exception {

        File inputDir = new File(request.getInputPath());
        File[] images = inputDir.listFiles((d, name) ->
                name.endsWith(".jpg") || name.endsWith(".png"));

        if (images == null || images.length == 0) {
            throw new RuntimeException("No images found");
        }

        Arrays.sort(images);

        // Create output folder
        File outputDir = new File(request.getOutputPath());
        if (!outputDir.exists()) outputDir.mkdirs();

        int index = 1;

        for (File img : images) {

            String outputVideo = request.getOutputPath() + "/video_" + index + ".mp4";
            // Optional AI caption
        /*    String caption = aiService.generateCaption(img.getName());
            System.out.println("Caption: " + caption);
*/
            String filter = "zoompan=z='min(zoom+0.002,1.5)':" +
                    "x='iw/2-(iw/zoom/2)':" +
                    "y='ih/2-(ih/zoom/2)':" +
                    "d=150:s=1080x1920";
            int fps = 30;
            int totalFrames = duration * fps;
            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg",
                    "-y",
                    "-loop", "1",
                    "-i", img.getAbsolutePath(),
                    "-vf",
                    "scale=3000:5333:force_original_aspect_ratio=increase," +
                            "zoompan=z='min(zoom+0.0012,1.25)':" +
                            "x='iw/2-(iw/zoom/2)':" +
                            "y='ih/2-(ih/zoom/2)':" +
                            "d=" + totalFrames + ":s=2160x3840:fps=30",

                    "-t", String.valueOf(duration),
                    "-c:v", "libx264",
                    "-preset", "slow",
                    "-crf", "18",
                    "-pix_fmt", "yuv420p",
                    "-r", "30",
                    "-movflags", "+faststart",
                    outputVideo
            );
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();

            index++;
        }
        File listFile = new File(request.getOutputPath() + "/list.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(listFile))) {
            for (int i = 1; i < index; i++) {
                writer.write("file 'video_" + i + ".mp4'");
                writer.newLine();
            }
        }
        String finalVideo = request.getOutputPath() + "/final_reel.mp4";

        ProcessBuilder mergePb = new ProcessBuilder(
                "ffmpeg",
                "-f", "concat",
                "-safe", "0",
                "-i", listFile.getAbsolutePath(),
                "-c", "copy",
                "-y",
                finalVideo
        );

        mergePb.inheritIO();
        Process mergeProcess = mergePb.start();
        int mergeExit = mergeProcess.waitFor();

        System.out.println("Merge Exit Code: " + mergeExit);

        return "Videos generated in: " + request.getOutputPath();
    }
}