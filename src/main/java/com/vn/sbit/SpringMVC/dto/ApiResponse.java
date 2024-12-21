package com.vn.sbit.SpringMVC.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor //constructor không tham số
@AllArgsConstructor // constructor có tham số
@Builder
// bắt buộc phải có AllArgsConstuctor ( builder có thể dùng tham số tùy chọn mà không phải tạo constructor tương ứng)
@FieldDefaults(level = AccessLevel.PRIVATE) // thay cho Access Modifier - private,(tùy theo level) ;
public class ApiResponse <T>{
    int code=1000;
    String message;
    T result = null;  // Đảm bảo result có giá trị mặc định nếu không có dữ liệu trả về
}