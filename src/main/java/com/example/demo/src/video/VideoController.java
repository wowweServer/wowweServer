package com.example.demo.src.video;


import com.example.demo.config.BaseResponse;
import com.example.demo.src.file.FileService;
import com.example.demo.src.video.model.VideoDto;
import jdk.internal.jline.internal.Log;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.demo.config.BaseResponseStatus.SUCCESS;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private VideoService videoService;
    private FileService fileService;

    public VideoController(VideoService videoService, FileService fileService) {
        this.videoService = videoService;
        this.fileService = fileService;
    }

    @ResponseBody
    @PostMapping("/upload")
    public BaseResponse upload(@RequestParam("file") MultipartFile file, VideoDto videoDto){
        try{

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String current_date = simpleDateFormat.format(new Date());




            String basePath = new File("").getAbsolutePath() + "/src/main/resources/static/";
            String[] fileFlags = file.getOriginalFilename().split("\\.");
            String filePath = basePath + current_date + "." + fileFlags[fileFlags.length-1];
            File dest = new File(filePath);
            file.transferTo(dest);

            FFmpeg ffmpeg=new FFmpeg("/usr/bin/ffmpeg");
            FFprobe ffprobe=new FFprobe("/usr/bin/ffprobe");

            FFmpegProbeResult probeResult =ffprobe.probe(filePath);
            logger.warn(probeResult.getFormat().filename);

            return new BaseResponse(SUCCESS);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return new BaseResponse(SUCCESS);
    }



}