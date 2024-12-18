package com.vn.sbit.SpringMVC.security;


import com.vn.sbit.SpringMVC.entity.User;
import com.vn.sbit.SpringMVC.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Oauth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    private final UserService userService;

    @Autowired
    public Oauth2LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String clientName=oAuth2User.getClientName();
        String email = oAuth2User.getEmail();
        System.out.println("OAUTH2 username:"+email);
        System.out.println("OAUTH2 username:"+oAuth2User.getEmail());
        System.out.println("OAUTH2 username:"+clientName);

        User user =userService.getUserByEmail(email);
        if(user !=null){
            System.out.println("User account already exists in database");
        }else{
            System.out.println("Create new user.About to add a new entry in database");
        }
        super.onAuthenticationSuccess(request, response, chain, authentication);
    }
}
