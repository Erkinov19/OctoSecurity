package com.octoSecurity.OctoSecurity.security;

import com.octoSecurity.OctoSecurity.ProfileEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private boolean enabled;
    private String role;

    private List<GrantedAuthority> authorityList;

    public CustomUserDetails(ProfileEntity profile) {
        this.id = profile.getId();
        username = profile.getUsername();
        password = profile.getPassword();
        enabled = profile.getStatus();
        role = profile.getProfileRole().toString();

        this.authorityList = Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorityList;
    }

    @Override
    public String getPassword() {
        System.out.println("CustomUserDetails: getPassword()");
        return password;
    }

    @Override
    public String getUsername() {
        System.out.println("CustomUserDetails: getUsername()");
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                ", authorityList=" + authorityList +
                '}';
    }

    public int getId() {
        return id;
    }
}

