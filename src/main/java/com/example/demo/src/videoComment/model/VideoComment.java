package com.example.demo.src.videoComment.model;

import com.example.demo.config.BaseTimeEntity;
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
public class VideoComment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "videoComment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    private String comment;
//    private boolean classes;
//
//    private int order; 이거 왜 안되냐?
    private int groupNum;
    private int commentLike;
    private int commentUnlike;

    public VideoComment(User user, Video video, String comment) {
        this.user = user;
        this.video = video;
        this.comment = comment;

        user.videoAddComment(this);
        video.videoAddComment(this);

    }
}
