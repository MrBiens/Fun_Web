package com.vn.sbit.SpringMVC.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse <T>{
    int code=1000;
    String message;
    T result = null;  // Đảm bảo result có giá trị mặc định nếu không có dữ liệu trả về
}