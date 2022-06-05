package com.octoSecurity.OctoSecurity;

import com.octoSecurity.OctoSecurity.ProfileEntity;
import com.octoSecurity.OctoSecurity.ProfileRepository;
import com.octoSecurity.OctoSecurity.dto.AuthDto;
import com.octoSecurity.OctoSecurity.dto.RegisterDto;
import com.octoSecurity.OctoSecurity.dto.UpdateDto;
import com.octoSecurity.OctoSecurity.security.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    private final JwtTokenUtil jwtTokenUtil;

    public ProfileService(ProfileRepository profileRepository, JwtTokenUtil jwtTokenUtil) {
        this.profileRepository = profileRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public boolean register(RegisterDto dto){
        Optional<ProfileEntity> optional = profileRepository.findByUsername(dto.getUsername());
        if (optional.isPresent()){
            throw new IllegalArgumentException("username taken");
        }
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName(dto.getName());
        profileEntity.setAge(dto.getAge());
        profileEntity.setUsername(dto.getUsername());
        profileEntity.setPassword(dto.getPassword());
        profileEntity.setProfileRole(ProfileRole.ROLE_CLIENT);
        profileEntity.setStatus(true);
        profileRepository.save(profileEntity);
        return true;
    }

    public AuthDto auth (AuthDto dto){
        Optional<ProfileEntity> optional = profileRepository.findByUsername(dto.getUsername());
        if (!optional.isPresent()){
            throw new IllegalArgumentException("Error");
        }
        ProfileEntity profileEntity = optional.get();
        String jwtToken = jwtTokenUtil.generateAccessToken(profileEntity.getId(), profileEntity.getUsername());
        dto.setToken(jwtToken);
        return dto;
    }

    public List<ProfileEntity> getAll() {
        return profileRepository.findAll();
    }

    public boolean delete(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (!optional.isPresent()){
            throw new IllegalArgumentException("Error");
        }
        profileRepository.delete(optional.get());
        return true;
    }

    public ProfileEntity clientInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<ProfileEntity> optional = profileRepository.findByUsername(username);
        if (!optional.isPresent()){
            throw new IllegalArgumentException("username not found!");
        }
        return optional.get();
    }

    public boolean update(UpdateDto dto) {
        ProfileEntity profile = clientInfo();
        profile.setName(dto.getName());
        profile.setPassword(dto.getPassword());
        profile.setAge(dto.getAge());
        profileRepository.save(profile);
        return true;
    }

    public boolean deleteClient() {
        profileRepository.delete(clientInfo());
        return true;
    }
}
