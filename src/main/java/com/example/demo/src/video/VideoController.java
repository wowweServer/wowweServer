package com.example.demo.src.video;


import com.example.demo.config.BaseResponse;
import com.example.demo.src.file.FileService;
import com.example.demo.src.video.model.Video;
import com.example.demo.src.video.model.VideoReq;
import com.example.demo.src.video.model.VideoRes;
import com.example.demo.utils.JwtService;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.example.demo.config.BaseResponseStatus.SUCCESS;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private VideoService videoService;
    private FileService fileService;
    private JwtService jwtService;

    public VideoController(VideoService videoService, FileService fileService,JwtService jwtService) {
        this.videoService = videoService;
        this.fileService = fileService;
        this.jwtService=jwtService;
    }

    @ResponseBody
    @GetMapping("/videolist")
    public BaseResponse<VideoRes> getVideo(){
        try{

            List<Video> videoList=videoService.findVideo();
            VideoRes videoRes=new VideoRes();
            videoRes.setVideoList(videoList);

            return new BaseResponse<>(videoRes);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return new BaseResponse(SUCCESS);
    }


    @ResponseBody
    @PostMapping("/upload")
    public BaseResponse upload(@RequestParam("file") MultipartFile file,
                               VideoReq videoReq,@RequestHeader Map<String, String> headers){
        try{


            Long userId=jwtService.getUserId();


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String current_date = simpleDateFormat.format(new Date());




            String basePath = new File("").getAbsolutePath() + "/src/main/resources/static/";
            String[] fileFlags = file.getOriginalFilename().split("\\.");
            String videoPath = basePath +"video"+current_date + "." + fileFlags[fileFlags.length-1];
            String videoUrl="http://localhost:9000/static/"+"video"+current_date + "." + fileFlags[fileFlags.length-1];

            File dest = new File(videoPath);
            file.transferTo(dest);

            FFmpeg ffmpeg=new FFmpeg("C:/ffmpeg/bin/ffmpeg");
            FFprobe ffprobe=new FFprobe("C:/ffmpeg/bin/ffprobe");



/*            FFmpeg ffmpeg=new FFmpeg("/usr/bin/ffmpeg");
            FFprobe ffprobe=new FFprobe("/usr/bin/ffprobe");

*/

            String imgPath=basePath+"img"+current_date+".png";
            String imgUrl="http://localhost:9000/static/"+"img"+current_date+".png";

            FFmpegBuilder builder = new FFmpegBuilder()
                    .overrideOutputFiles(true)
                    .setInput(videoPath)
                    .addExtraArgs("-ss","00:00:01")
                    .addOutput(imgPath)
                    .setFrames(1)
                    .done();

            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg,ffprobe);
            executor.createJob(builder).run();

            FFmpegProbeResult probeResult=ffprobe.probe(videoPath);
            FFmpegFormat format = probeResult.getFormat();

            videoService.createVideo(videoReq,format.duration,videoUrl,imgUrl,userId);


            return new BaseResponse(SUCCESS);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return new BaseResponse(SUCCESS);
    }

}