package com.vn.sbit.SpringMVC.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999,"UNCATEGORIZED_EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR),//500:lỗi tại server - không xác định
    QUANTITY_NOT_POSITIVE_NUMBER(9001, "Quantity must be a positive number", HttpStatus.BAD_REQUEST),
    PRICE_NOT_POSITIVE_NUMBER(9002, "Price must be a positive number", HttpStatus.BAD_REQUEST),

    SALE_INVOICE_DETAIL_NOT_FOUND(1001, "Sale Invoice Detail not found with the given id", HttpStatus.BAD_REQUEST),

    SALE_INVOICE_NOT_FOUND(2001, "Sale Invoice not found with the given id", HttpStatus.BAD_REQUEST),

    PRODUCT_NOT_FOUND(3001,"Product not found with the given id",HttpStatus.BAD_REQUEST),


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

