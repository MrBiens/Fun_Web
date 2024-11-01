package com.vn.sbit.SpringMVC.dto.request;


import jakarta.validation.constraints.*;

public class RegisterUser {
    @NotBlank(message = "Username not null")
    @Size(min = 1,message = "Minimum length is 1")
    private String username;

    @NotBlank(message = "Password not null")
    @Size(min = 8,max = 16,message = "Minimum length is 6 and max is 16")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).*$",
            message = "Mật khẩu phải chứa ít nhất 1 chữ số và 1 ký tự đặc biệt")
    private String password;

    @NotBlank(message = "FirstName not null")
    private String firstname;

    @NotBlank(message = "LastName not null")
    private String lastname;

    @NotBlank(message = "Email not null")
    @Email(message = "Must enter email")
    private String email;

    public RegisterUser() {
    }

    public RegisterUser(String username, String password, String firstname, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
