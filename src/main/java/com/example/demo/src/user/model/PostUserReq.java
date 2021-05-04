package com.example.demo.src.user.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserReq {
    private String userName;
    private String userEmail;
    private String password;
}
