package com.example.demo.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    JSON_PARSE_ERROR(false,2003,"json 데이터 parse 오류입니다."),

    // user
    PASSWORD_NOT_EQUAL(false, 2011, "비밀번호가 일치하지 않습니다."),
    POST_USER_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),
    LOGIN_USER_NOT_EXISTS_EMAIL(false,2018,"존재하지않는 이메일입니다."),
    LOGIN_USER_NOT_EQUAL_HOST(false,2019,"다른사용자입니다."),

    //video
    INVALID_VIDEO_EXT(false,2030,"유효하지 않은 비디오 확장자"),
    INVALID_IMAGE_EXT(false,2031,"유효하지 않은 썸네일 확장자"),
    VIDEO_UPLOAD_ERROR(false,2032,"비디오 업로드 실패"),
    IMAGE_UPLOAD_ERROR(false,2033,"썸네일 업로드 실패"),

    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;


    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
