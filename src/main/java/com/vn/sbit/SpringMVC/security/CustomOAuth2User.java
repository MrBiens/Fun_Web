package com.vn.sbit.SpringMVC.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oAuth2User;
    private String clientName;

    public CustomOAuth2User(OAuth2User oAuth2User,String clientName) {
        this.oAuth2User = oAuth2User;
        this.clientName = clientName;

    }


    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getName();
    }
    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }
    public String getUsername() { //principal username
        return oAuth2User.getAttribute("name");
    }
    public String getFirstName() {
        return oAuth2User.getAttribute("given_name"); // "given_name" là trường chứa tên gọi
    }

    // Get last name
    public String getLastName() {
        return oAuth2User.getAttribute("family_name"); // "family_name" là trường chứa họ
    }

    public String getClientName() {
        return clientName;
    }
}
