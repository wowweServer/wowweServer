package com.example.demo.src.video.model;

import com.example.demo.src.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="VIDEO_ID")
    private Long id;

    private String title;
    private String description;
    private String videoUrl;
    private String thumnailUrl;
    private double duration;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

}