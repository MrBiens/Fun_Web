package com.vn.sbit.SpringMVC.service.Impl;

import com.vn.sbit.SpringMVC.repository.RoleRepository;
import com.vn.sbit.SpringMVC.repository.UserRepository;
import com.vn.sbit.SpringMVC.entity.Role;
import com.vn.sbit.SpringMVC.entity.User;
import com.vn.sbit.SpringMVC.enum_project.RoleEnum;
import com.vn.sbit.SpringMVC.service.UserService;
import com.vn.sbit.SpringMVC.dto.request.RegisterUser;
import com.vn.sbit.SpringMVC.util.VerifyCode;
import jakarta.mail.internet.MimeMessage;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JavaMailSender javaMailSender;

    @NonFinal
    @Value("${EMAILNAME}")
    protected String EMAIL;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.javaMailSender =javaMailSender;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void createUser(RegisterUser registerUser) {
        User user = new User();
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();

        user.setUsername(registerUser.getUsername());
        user.setPassword(bcrypt.encode(registerUser.getPassword()));
        user.setFirstname(registerUser.getFirstname());
        user.setLastname(registerUser.getLastname());
        user.setEmail(registerUser.getEmail());

        Role role_user = roleRepository.findByName(RoleEnum.ROLE_USER.name());
        Set<Role> roles=new HashSet<>();
        roles.add(role_user);

        user.setRoles(roles);
        user.setEnabled(false);

        String verifyCode = VerifyCode.verifyCode();
        user.setVerifyCode(verifyCode);

        userRepository.save(user);

        sendVerificationEmail(user);
    }

    public void sendVerificationEmail(User user) {
        String subject="Please verify your registration";
        String senderName="FunWeb Admin";
        String mailContent="Dear "+user.getFirstname()+user.getLastname();

        mailContent+="<p> Please click the link below to verify your registration</p>";
        mailContent += "<p><a href=\"http://localhost:8080/register/confirm?code=" + user.getVerifyCode() + "\">Verify</a></p>";
        mailContent += "<p>Thank you!<br>FunWeb Admin</p>";
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(EMAIL, senderName);
            helper.setTo(user.getEmail()); helper.setSubject(subject);
            helper.setText(mailContent, true); // true để xác định rằng email có nội dung HTML
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User findByVerifyCode(String verifyCode) {
        return userRepository.findByVerifyCode(verifyCode);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        if (!user.isEnabled()) {
            throw new DisabledException("User account is not enabled");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roleToAuthorities(user.getRoles()));
    }


    private Set<? extends GrantedAuthority> roleToAuthorities(Set<Role> roles){

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        // duyệt (stream) từng roles trong set , mỗi string(vd:role_admin) sẽ trả về 1 SimpleGrantedAuthority mới . Và tập hợp nó thành 1 chuỗi set
    }


}
