package com.octoSecurity.OctoSecurity;

import com.octoSecurity.OctoSecurity.dto.AuthDto;
import com.octoSecurity.OctoSecurity.dto.RegisterDto;
import com.octoSecurity.OctoSecurity.dto.UpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        Boolean result = profileService.register(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthDto dto) {
        AuthDto result = profileService.auth(dto);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/admin/getAll")
    public ResponseEntity<?> getAll() {
        List<ProfileEntity> result = profileService.getAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        boolean result = profileService.delete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/client/info")
    public ResponseEntity<?> clientInfo() {
        ProfileEntity result = profileService.clientInfo();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/client")
    public ResponseEntity<?> updateClient(@RequestBody UpdateDto dto) {
        boolean result = profileService.update(dto);
        return ResponseEntity.ok(result);

    }

    @DeleteMapping("/client")
    public ResponseEntity<?> deleteClient() {
        boolean result = profileService.deleteClient();
        return ResponseEntity.ok(result);
    }
}