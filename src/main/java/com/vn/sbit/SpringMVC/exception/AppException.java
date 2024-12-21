package com.vn.sbit.SpringMVC.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;


    public AppException(ErrorCode errorCode) {
        //super ( lấy tham số ở Construct từ lớp cha)
        super(errorCode.getMessage()); //truyền lời nhắn lỗi tới RuntimeException . Message
        this.errorCode = errorCode;
    }

//    public ErrorCode getErrorCode() {
//        return errorCode;
//    }
//
//    public void setErrorCode(ErrorCode errorCode) {
//        this.errorCode = errorCode;
//    }

}
