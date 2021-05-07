package com.example.demo.src.video.model;

import com.example.demo.config.BaseTimeEntity;
import com.example.demo.src.user.model.User;
import com.example.demo.src.videoComment.model.VideoComment;
import com.example.demo.src.videoLike.model.VideoLike;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Video extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "video_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String fileUrl;
    private String title;
    private String description;
    private boolean isLive;
    private String thumnailImg;
    private int duration;
    private LocalDateTime startTime;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private List<VideoLike> videoLikes = new ArrayList<>();

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private List<VideoComment> videoComments = new ArrayList<>();

    public Video(Long id, String fileUrl, String title, String description, boolean isLive, String thumnailImg, int duration, LocalDateTime startTime) {

        this.id = id;
        this.fileUrl = fileUrl;
        this.title = title;
        this.description = description;
        this.isLive = isLive;
        this.thumnailImg = thumnailImg;
        this.duration = duration;
        this.startTime = startTime;
    }

    public Video(String title, String thumnailImg, User user) {
        this.title = title;
        this.thumnailImg = thumnailImg;
        this.user = user;
    }

    public Video(String title, String thumnailImg, User user, List<VideoLike> videoLikes) {

        this.title = title;
        this.thumnailImg = thumnailImg;
        this.user = user;
        this.videoLikes = videoLikes;
    }

    public Video(String title, String description, int duration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
    }


    public void videoAddLike(VideoLike videoLikes) {
        this.videoLikes.add(videoLikes);
        videoLikes.setVideo(this);
    }

    public void videoAddComment(VideoComment videoComment) {
        this.videoComments.add(videoComment);
        videoComment.setVideo(this);
    }
}
