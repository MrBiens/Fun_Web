//package com.vn.sbit.SpringMVC.exception;
//
//import com.vn.sbit.SpringMVC.dto.ApiResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    //ResponseEntity:một lớp trong Spring Framework được sử dụng để biểu diễn toàn bộ phản hồi HTTP
//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ApiResponse> handlingRuntimeException(AppException ex) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
//        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
//
//    @ExceptionHandler(value = AppException.class)
//    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
//        ApiResponse apiResponse = new ApiResponse();
//        //AppException co construct ErrorCode
//        ErrorCode errorCode = exception.getErrorCode();
//        //code cua user_existed
//        apiResponse.setCode(errorCode.getCode());
//        //loi nhan error
//        apiResponse.setMessage((errorCode.getMessage()));
//
//        return ResponseEntity
//                .status(errorCode.getHttpStatusCode())
//                .body(apiResponse);
//    }
//
//
//
//}
