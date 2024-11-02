package com.vn.sbit.SpringMVC.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class CategoryRequest {

    @NotBlank(message = "Category Name not null")
    private String categoryName;

    private Boolean categoryStatus;

    public CategoryRequest() {
    }

    public CategoryRequest(String categoryName, Boolean categoryStatus) {
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(Boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}
