package com.example.demo.src.support;

import com.example.demo.src.user.UserService;
import com.example.demo.src.video.VideoService;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SupportController {

    private final VideoService videoService;
    private final JwtService jwtService;
    private final UserService userService;
}
