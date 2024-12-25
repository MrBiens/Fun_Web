package com.vn.sbit.SpringMVC.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class LabelValue {
    String label;
    Long value;
}
