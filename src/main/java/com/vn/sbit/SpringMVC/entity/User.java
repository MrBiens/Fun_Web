package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username",unique = true,columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci",length = 20)
    private String username;

    @Column(name = "password",length = 256)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email",unique = true,columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci",length = 20)
    @Email
    private String email;

    //image attribute trong csdl chua 65535 byte(kieu dl to lon) -hoac luu duong dan thu muc
    @Lob
    @Column(name = "avatar")
    private Blob avatar;

    @Column(name = "verifycode",length = 10,updatable = false)
    private String verifyCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {
    }

    public User(Long id, String username, String password, boolean enabled, String firstname, String lastname, String email, Blob avatar, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.avatar = avatar;
        this.roles = roles;
    }

    public User(String username, String password, boolean enabled, String firstname, String lastname, String email, Blob avatar) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.avatar = avatar;
    }


}
