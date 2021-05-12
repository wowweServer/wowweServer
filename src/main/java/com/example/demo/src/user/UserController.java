package com.example.demo.src.user;



//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private final UserService userService;
    @Autowired
    private final JwtService jwtService;


    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }


    /*

        //Query String
        @ResponseBody
        @GetMapping("") // (GET) 127.0.0.1:9000/app/users
        public BaseResponse<List<GetUserRes>> getUsers(@RequestParam(required = false) String Email) {
            // Get Users
            List<GetUserRes> getUsersRes = userProvider.getUsers(Email);
            return new BaseResponse<>(getUsersRes);
        }


        // Path-variable
        @ResponseBody
        @GetMapping("/{userId}") // (GET) 127.0.0.1:9000/app/users/:userIdx
        public BaseResponse<GetUserRes> getUser(@PathVariable("userId") int userIdx) {
            // Get Users
            GetUserRes getUserRes = userProvider.getUser(userIdx);
            return new BaseResponse<>(getUserRes);
        }
    */
    @ResponseBody
    @JsonProperty("User")
    @PostMapping("/register")
    public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) throws BaseException {
        try {
            System.out.println("aaa");
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

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

    @ResponseBody
    @GetMapping("/kakao/callback")
    public BaseResponse<PostUserRes> kakaoLogin(@RequestParam("code") String code) throws BaseException {

        try {

            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setReadTimeout(5000);
            factory.setConnectTimeout(3000);
            HttpClient httpClient = HttpClientBuilder.create()
                    .setMaxConnTotal(100)
                    .setMaxConnPerRoute(5)
                    .build();
            factory.setHttpClient(httpClient);
            RestTemplate restTemplate = new RestTemplate(factory);
            String url = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=1941893462dfad4f46b2ccc179a60d07&redirect_uri=http://localhost:9000/user/kakao/callback&client_secret=I3e0bBYVMgFpXqIiDkUVVAuRcgJslXy2&code=" + code;


            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            ResponseEntity<String> res = restTemplate.postForEntity(url, parameters, String.class);


            JSONParser jsonParser = new JSONParser();
            JSONObject jsonpObject = (JSONObject) jsonParser.parse(res.getBody());
            String token = jsonpObject.get("access_token").toString();

            String url1 = "https://kapi.kakao.com/v2/user/me";


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            HttpEntity entity = new HttpEntity("parameters", headers);

            ResponseEntity<String> res1 = restTemplate.exchange(url1, HttpMethod.GET, entity, String.class);
            JSONObject jsonpObject1 = (JSONObject) jsonParser.parse(res1.getBody());
            JSONObject account = (JSONObject) jsonpObject1.get("kakao_account");
            JSONObject profile = (JSONObject) account.get("profile");


            KakaoLoginReq kakaoLoginReq = new KakaoLoginReq(account.get("email").toString(), profile.get("nickname").toString());
            PostUserRes postUserRes = userService.createKakaoLogin(kakaoLoginReq);
            return new BaseResponse<>(postUserRes);
        } catch (ParseException e) {
            e.printStackTrace();
            return new BaseResponse<>(new PostUserRes("aa", 1L));
        }
    }

    @ResponseBody
    @PostMapping("/update")
    public BaseResponse<UpdateUserRes> updateUser(@RequestBody UpdateUserReq updateUserReq) throws BaseException {
        UpdateUserRes updateUserRes = userService.updateUser(updateUserReq);

        return new BaseResponse<>(updateUserRes);
    }

}
