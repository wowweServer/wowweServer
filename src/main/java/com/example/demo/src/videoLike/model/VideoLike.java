package com.example.demo.src.videoLike.model;

import com.example.demo.src.user.model.User;
import com.example.demo.src.video.model.Video;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VideoLike {

    @Id
    @GeneratedValue
    @Column(name = "videoLike_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    public VideoLike(User user, Video video) {
        this.user = user;
        this.video = video;
        user.videoAddLike(this);
        video.videoAddLike(this);
    }

}
