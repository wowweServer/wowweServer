package com.example.demo.src.user.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private Long Id;

    private String name;
    private String email;
    private String password;
    private String profileImg;

}