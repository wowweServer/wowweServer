package com.example.demo.src.user;



import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.demo.config.BaseResponseStatus.*;


@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final JwtService jwtService;


    @Autowired
    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {

        if(userRepository.findByEmail(postUserReq.getUserEmail()).isPresent()){
            throw new BaseException(POST_USER_EXISTS_EMAIL);
        }


        String pwd;
        try{
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postUserReq.getPassword());
            postUserReq.setPassword(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        User newUser=new User();
        newUser.setEmail(postUserReq.getUserEmail());
        newUser.setName(postUserReq.getUserName());
        newUser.setPassword(pwd);

        User savedUser = userRepository.save(newUser);
        Long  userId=savedUser.getId();

        String jwt = jwtService.createJwt(userId);
        return new PostUserRes(jwt,userId);
    }


    public PostUserRes createLogin(PostLoginReq postLoginReq) throws BaseException {

        Optional<User> users = userRepository.findByEmail(postLoginReq.getUserEmail());
        if(!users.isPresent()){
            throw new BaseException(LOGIN_USER_NOT_EXISTS_EMAIL);
        }

        User user = users.get();

        String pwd;
        try{
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postLoginReq.getPassword());
//            postLoginReq.setPassword(pwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }

        if (!user.getPassword().equals(pwd)) {

            throw new BaseException(PASSWORD_NOT_EQUAL);
        }

        Long userId=user.getId();

        String jwt = jwtService.createJwt(userId);
        return new PostUserRes(jwt,userId);
    }


    public PostFindPasswordRes findPassword(PostFindPasswordReq postFindPasswordReq) throws BaseException {


        Optional<User> users = userRepository.findByEmail(postFindPasswordReq.getUserEmail());
        if(!users.isPresent()){
            throw new BaseException(LOGIN_USER_NOT_EXISTS_EMAIL);
        }
        User user=users.get();

        String pwd;
        try{
            pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(user.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        return new PostFindPasswordRes(pwd);
    }

    public PostUserRes createKakaoLogin(KakaoLoginReq kakaoLoginReq) throws BaseException {

        Optional<User> users = userRepository.findByEmail(kakaoLoginReq.getUserEmail());
        Long id;
        if(users.isPresent()){
            User newUser=new User();
            newUser.setEmail(kakaoLoginReq.getUserEmail());
            newUser.setName(kakaoLoginReq.getUserName());

            User savedUser = userRepository.save(newUser);
            id=savedUser.getId();
        }
        else{
            User user=users.get();
            id=user.getId();
        }

        String jwt = jwtService.createJwt(id);
        return new PostUserRes(jwt,id);
    }


    @Transactional
    public UpdateUserRes updateUser(UpdateUserReq updateUserReq) throws BaseException {

        Long userId = jwtService.getUserId();

        Optional<User> byId = userRepository.findById(userId);

        if (byId.isPresent()) {
            byId.get().updateUser(updateUserReq);
        }

        return new UpdateUserRes(userId);
    }

    @Transactional
    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.get();
    }

    @Transactional
    public Optional<User> findByUserId(Long userId) {
        return userRepository.findById(userId);
    }
}
