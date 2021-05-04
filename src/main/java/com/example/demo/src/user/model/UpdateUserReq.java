package com.example.demo.src.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserReq {

//    private String jwt;

    private Long id;

    private String name;
    private String email;
    private String password;
    private String profileImg;
}
