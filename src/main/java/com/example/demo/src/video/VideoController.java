package com.example.demo.src.video;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.User;
import com.example.demo.src.video.dto.VideoUploadReqDto;
import com.example.demo.src.video.model.Video;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.demo.config.BaseResponseStatus.*;

@Controller
@RequiredArgsConstructor
public class VideoController {


    @Value("${file.upload.path}")
    private String fileUploadPath;
    @Value("${file.down.baseUrl}")
    private String fileDownBaseUrl;
    @Value("${file.ffmpeg.path}")
    private String ffmpegPath;
    @Value("${file.ffprobe.path}")
    private String ffprobePath;

    private final VideoService videoService;
    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("recently")
    public String recentlyVideo(Model model) {

        videoService.save(new Video("chang", "first", 10));

        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videos", videoList);
        return "videos/videoHome";
    }


    //비디오 업로드
    @ResponseBody
    @PostMapping("/video/upload")
    public BaseResponse upload(@RequestParam("video") MultipartFile videoFile,
                               @RequestParam("image") MultipartFile imageFile,
                               VideoUploadReqDto videoUploadReqDto) throws BaseException{
        try{

            Long userId=jwtService.getUserId();
            Optional<User> opt=userService.findByUserId(userId);
            opt.orElseThrow(()->new BaseException(INVALID_JWT));
            User user=opt.get();

            if(videoFile==null||imageFile==null||videoUploadReqDto.getTitle()==null
            ||videoUploadReqDto.getDescription()==null||new Double(videoUploadReqDto.getDuration())==null){
                return new BaseResponse(REQUEST_ERROR);
            }

            String[] videoFileFlags = videoFile.getOriginalFilename().split("\\.");
            String videoExt=videoFileFlags[videoFileFlags.length-1];

            if(videoExt!="mp4") {
                return new BaseResponse(INVALID_VIDEO_EXT);
            }

            String[] imageFileFlags = imageFile.getOriginalFilename().split("\\.");
            String imageExt=imageFileFlags[imageFileFlags.length-1];

            if(imageExt!="png"&&imageExt!="jpg"){
                return new BaseResponse(INVALID_IMAGE_EXT);
            }

            videoService.createVideo(videoUploadReqDto,
                    videoCreate(videoFile,videoExt)
                    ,imageCreate(imageFile,imageExt)
                    ,user);

            return new BaseResponse(SUCCESS);
        }
        catch(Exception e) {
            e.printStackTrace();
            return new BaseResponse(SERVER_ERROR);
        }
    }

    String videoCreate(MultipartFile videoFile,String videoExt) throws BaseException{
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String current_date = simpleDateFormat.format(new Date());

            String basePath = new File("").getAbsolutePath() + fileUploadPath;


            String videoPath = basePath + "video" + current_date + "." + videoExt;
            String videoDownUrl = fileDownBaseUrl + "video" + current_date + "." + videoExt;

            File dest = new File(videoPath);
            videoFile.transferTo(dest);

            return videoDownUrl;
        }
        catch(Exception e){
            throw new BaseException(VIDEO_UPLOAD_ERROR);
        }

    }
    String imageCreate(MultipartFile imageFile,String imageExt) throws BaseException{
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String current_date = simpleDateFormat.format(new Date());

            String basePath = new File("").getAbsolutePath() + fileUploadPath;

            String imagePath = basePath + "image" + current_date + "." + imageExt;
            String imageDownUrl = fileDownBaseUrl + "image" + current_date + "." + imageExt;

            File dest = new File(imagePath);
            imageFile.transferTo(dest);

            return imageDownUrl;
        }
        catch(Exception e){
            throw new BaseException(IMAGE_UPLOAD_ERROR);
        }

    }

}
