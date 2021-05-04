package com.example.demo.src.video;

import com.example.demo.src.video.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("recently")
    public String recentlyVideo(Model model) {

        videoService.save(new Video("chang", "first", 10));

        List<Video> videoList = videoService.findVideo();
        model.addAttribute("videos", videoList);
        return "videos/videoHome";
    }
}
