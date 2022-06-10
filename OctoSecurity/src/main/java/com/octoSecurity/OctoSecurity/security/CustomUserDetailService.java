package com.octoSecurity.OctoSecurity.security;

import com.octoSecurity.OctoSecurity.ProfileEntity;
import com.octoSecurity.OctoSecurity.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    public CustomUserDetailService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<ProfileEntity> usersOptional = this.profileRepository.findByUsername(s);
        usersOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        ProfileEntity profile = usersOptional.get();
        System.out.println(profile);

        return new CustomUserDetails(profile);
    }
  

}
