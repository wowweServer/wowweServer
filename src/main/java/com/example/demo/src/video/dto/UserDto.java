package com.example.demo.src.video.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String userName;

    public UserDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
