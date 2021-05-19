package com.example.demo.src.user;



//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHeaders;

import com.example.demo.utils.Http;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import static com.example.demo.config.BaseResponseStatus.JSON_PARSE_ERROR;


@RestController
@RequestMapping("/user")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("${secret.kakao.restApiKey}")
    private String kakaoRestApiKey;
    @Value("${secret.kakao.clientKey}")
    private String kakaoClientKey;
    @Value("${secret.kakao.callbackUrl}")
    private String kakaoCallbackUrl;


    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;


    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    // 회원가입
    @ResponseBody
    @JsonProperty("User")
    @PostMapping("/register")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) throws BaseException {
        try {
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //로그인
    @ResponseBody
    @PostMapping("/login")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostLoginReq postLoginReq) throws BaseException {

        try {
            PostUserRes postUserRes = userService.createLogin(postLoginReq);
            return new BaseResponse<>(postUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //비번찾기
    @ResponseBody
    @PostMapping("/find")
    public BaseResponse<PostFindPasswordRes> findPassword(@RequestBody PostFindPasswordReq postFindPasswordReq) throws BaseException {
        try {

            PostFindPasswordRes postFindPasswordRes = userService.findPassword(postFindPasswordReq);
            return new BaseResponse<>(postFindPasswordRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    //카카오 회원가입/로그인
    @ResponseBody
    @GetMapping(value = "/kakao/callback")
    public BaseResponse<PostUserRes> kakaoLogin(@RequestParam("code") String code) throws BaseException {

        String token = kakaoGetCode(code);
        KakaoLoginReq kakaoLoginReq = kakaoGetInfo(token);
        PostUserRes postUserRes = userService.createKakaoLogin(kakaoLoginReq);
        return new BaseResponse<>(postUserRes);

    }


    @ResponseBody
    @PostMapping("/update")
    public BaseResponse<UpdateUserRes> updateUser(@RequestBody UpdateUserReq updateUserReq) throws BaseException {


        UpdateUserRes updateUserRes = userService.updateUser(updateUserReq);

        return new BaseResponse<>(updateUserRes);
    }

    @GetMapping("/logout")
    public BaseResponse<String> logoutUser() {

        String tempJwt = jwtService.createJwtTrash("askjfnadkgnajdgnaegi");

        return new BaseResponse<>(tempJwt);
    }



    //메서드
    public  String kakaoGetCode(String code) throws BaseException{

        String tokenUrl = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id="
                +kakaoRestApiKey+"&redirect_uri="+kakaoCallbackUrl
                +"&client_secret="+kakaoClientKey+"&code="+code;

        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity tokenEntity = new HttpEntity("parameters", tokenHeaders);

        ResponseEntity<String> tokenRes = Http.getHttp(tokenUrl,tokenEntity);

        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject tokenObject = (JSONObject) jsonParser.parse(tokenRes.getBody());
            return tokenObject.get("access_token").toString();
        }
        catch(Exception ignored){
            throw new BaseException(JSON_PARSE_ERROR);
        }
    }


    public KakaoLoginReq kakaoGetInfo(String token) throws BaseException{

        String infoUrl = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders infoHeaders = new HttpHeaders();
        infoHeaders.setContentType(MediaType.APPLICATION_JSON);
        infoHeaders.set("Authorization", "Bearer " + token);

        HttpEntity infoEntity = new HttpEntity("parameters", infoHeaders);
        JSONParser jsonParser = new JSONParser();
        ResponseEntity<String> infoRes = Http.postHttp(infoUrl,infoEntity);
        try {
            JSONObject profileObject = (JSONObject) jsonParser.parse(infoRes.getBody());
            JSONObject account = (JSONObject) profileObject.get("kakao_account");
            JSONObject profile = (JSONObject) account.get("profile");
            return new KakaoLoginReq(account.get("email").toString(), profile.get("nickname").toString());
        }
        catch(Exception ignored) {
            throw new BaseException(JSON_PARSE_ERROR);
        }

    }
}
