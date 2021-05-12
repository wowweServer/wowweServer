package com.example.demo.src.video;

import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.model.User;
import com.example.demo.src.video.model.Video;
import com.example.demo.src.video.model.VideoReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private VideoRepository videoRepository;
    private UserRepository userRepository;

    public VideoService(VideoRepository videoRepository,UserRepository userRepository){
        this.videoRepository=videoRepository;
        this.userRepository=userRepository;
    }

    @Transactional
    public List<Video> findVideo(){
        return videoRepository.findAll();
    }


    @Transactional
    public void createVideo(VideoReq videoReq,double duration,String videoUrl,String imgUrl,Long userId){


        Video video=new Video();

        Optional optional = userRepository.findById(userId);
        if(optional.isPresent()) {
            User user = (User) optional.get();
            video.setUser(user);
        }

        video.setTitle(videoReq.getTitle());
        video.setDescription(videoReq.getDescription());
        video.setDuration(duration);
        video.setThumnailUrl(imgUrl);
        video.setVideoUrl(videoUrl);


        videoRepository.save(video);
    }


}
