package com.example.demo.src.videoLike;

import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.model.User;
import com.example.demo.src.video.VideoRepository;
import com.example.demo.src.video.model.Video;
import com.example.demo.src.videoLike.dto.TopLikeVideoResDto;
import com.example.demo.src.videoLike.model.VideoLike;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoLikeService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    VideoLikeRepository videoLikeRepository;


    @Transactional
    public Page<TopLikeVideoResDto> topLikeVideoPaging(int offset, int limit) {
        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<VideoLike> page = videoLikeRepository.findVideoByLike(pageRequest);
        Page<TopLikeVideoResDto> apiDto = page.map(vl -> new TopLikeVideoResDto(vl.getVideo().getId(),
                vl.getVideo().getThumnailImg(), vl.getVideo().getTitle(), vl.getVideo().getCreatedTime(), vl.getUser().getId(), vl.getUser().getName()));

        return apiDto;
    }


    @Transactional
    public void videoLike(Long userId, Long videoId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Video> video = videoRepository.findById(videoId);

        // 중복안되는 것두 넣어야함
        // 이건 jwt 그거에 대한 이해가 필요함?
        
        
        if (user.isPresent() && video.isPresent()) {
            videoLikeRepository.save(new VideoLike(user.get(), video.get()));
        }

    }
}
