package com.vn.sbit.SpringMVC.security;

import com.vn.sbit.SpringMVC.enum_project.RoleEnum;
import com.vn.sbit.SpringMVC.service.Impl.CustomerOauth2UserServiceImpl;
import com.vn.sbit.SpringMVC.service.Impl.UserServiceImpl;
import com.vn.sbit.SpringMVC.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    private final CustomerOauth2UserServiceImpl customerOauth2UserService;

    private final Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;

    @Autowired
    public SecurityConfiguration(CustomerOauth2UserServiceImpl customerOauth2UserService, Oauth2LoginSuccessHandler oauth2LoginSuccessHandler) {
        this.customerOauth2UserService = customerOauth2UserService;
        this.oauth2LoginSuccessHandler = oauth2LoginSuccessHandler;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(10); //hash 10 lan
    }


    //authentication with user in memory
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService){ // Inheritance & Polymorphism(because service implement service) & Liskov Substitution Principle
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);//implementation của UserDetailsService để getInfoUser
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;//Đối tượng này sẽ được Spring Security sử dụng để xử lý quá trình xác thực người dùng
    }

    //xác định cách mà các yêu cầu HTTP được bảo vệ - các role nào đc truy cập vào endpoint nào
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer -> {
                    configurer
                            .requestMatchers("/register/**").permitAll() // tat ca user duoc register
                            .requestMatchers("/admin/**").hasAnyAuthority(RoleEnum.ROLE_ADMIN.name(), RoleEnum.ROLE_MANAGER.name())

                            .anyRequest().authenticated();
                } //tất cả request đều phải xác minh
        ).formLogin(
                formlogin->formlogin.loginPage("/proviso/login") //chuyển đến login form
                        .loginProcessingUrl("/authenticateTheUser") // dựa vào daoAuthenticationProvider
                        .permitAll()

        ).oauth2Login(oauth2Login -> oauth2Login.loginPage("/proviso/login")
                .userInfoEndpoint(userInfoEndpointConfig -> {
                    userInfoEndpointConfig.userService(customerOauth2UserService);  // Sử dụng service để xử lý dữ liệu người dùng
                })
                .successHandler(oauth2LoginSuccessHandler)
        ).logout(
                LogoutConfigurer::permitAll
        ).exceptionHandling(exception -> exception.accessDeniedPage("/proviso/show403Page"));

        return httpSecurity.build();
    }




}
