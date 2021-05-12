package com.example.demo.src.videoLike;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.videoLike.dto.AddLikeDto;
import com.example.demo.src.videoLike.dto.AddLikeResDto;
import com.example.demo.src.videoLike.dto.TopLikeVideoResDto;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VideoLikeApi {

    @Autowired
    VideoLikeService videoLikeService;
    @Autowired
    JwtService jwtService;

    @GetMapping("/loadTop100Video")
    public BaseResponse<Page<TopLikeVideoResDto>> paging(@RequestParam int offset, @RequestParam int limit) {
        Page<TopLikeVideoResDto> topLikeVideoPaging = videoLikeService.topLikeVideoPaging(offset, limit);

        return new BaseResponse<>(topLikeVideoPaging);
    }

    @PostMapping("/video/{videoId}/addLike")
    public BaseResponse<String> addlike(@PathVariable("videoId") Long videoId) throws BaseException {

        Long userId = jwtService.getUserId();
        videoLikeService.videoLike(userId, videoId);

        return new BaseResponse<>("");

    }
}
