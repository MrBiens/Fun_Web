package com.vn.sbit.SpringMVC.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum ErrorCode {
    SALE_INVOICE_DETAIL_NOT_FOUND(1001, "Sale Invoice Detail not found with the given id", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999,"UNCATEGORIZED_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),//500:lỗi tại server - không xác định


    ;

    int code;
    String message;
    HttpStatusCode httpStatusCode;

    ErrorCode(int code , String message,HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode=httpStatusCode;
    }


}

