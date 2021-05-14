package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserServiceTest {

    @Autowired
    UserService userService;
/*
    @Test
    public void 로그인테스트() throws BaseException {

        PostLoginReq postLoginReq = new PostLoginReq("temp@naver.com","1234");
        PostUserRes login = userService.createLogin(postLoginReq);

        System.out.println(login.getUserId() + "     " + login.getJwt());

    }

    @Test
    public void 업데이트테스트_로그인되어있는지테스트() throws BaseException {
        UpdateUserReq updateUserReq = new UpdateUserReq();
        updateUserReq.setName("chang1");
        updateUserReq.setEmail("chang1@naver.com");
        updateUserReq.setPassword("1234");

        UpdateUserRes updateUserRes = userService.updateUser(updateUserReq);


        System.out.println("result : " + updateUserRes.getUesrId());
    }
*/
}