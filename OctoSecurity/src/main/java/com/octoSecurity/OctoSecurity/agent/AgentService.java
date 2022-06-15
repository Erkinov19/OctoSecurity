package com.octoSecurity.OctoSecurity.agent;

import com.octoSecurity.OctoSecurity.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class AgentService {
    private final AgentRepo agentRepo;

    public AgentService(AgentRepo agentRepo) {
        this.agentRepo = agentRepo;
    }


    public Agent register(Agent agent) {
        Optional<Agent> optional = agentRepo.findByNickName(agent.getNickName());
        if (optional.isPresent()){
            throw new IllegalArgumentException("This nickName already exist");
        }
        agent.setPassword(generateSHA(agent.getNickName(),generateRandom()));
        agent.setIslogged(false);
        agentRepo.save(agent);
        return agent;
    }
    public String generateSHA(String salt, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public  String generateRandom(){
        SecureRandom sr = new SecureRandom();
        byte[] random = new byte[16];
        sr.nextBytes(random);
        return random.toString();
    }

    public Boolean login(LoginAgent login) {
        Optional<Agent> optional = agentRepo.findByNickNameAndPassword(login.getNickName(), login.getPassword());
        if (!optional.isPresent()){
            throw new IllegalArgumentException("Error");
        }
        Agent agent = optional.get();
        boolean islogged = agent.getIslogged();
        if (islogged){
            throw new IllegalArgumentException("Royxatan otbogan");
        }
        agent.setIslogged(true);
        agent.setLoginDate(LocalDateTime.now());
        agentRepo.save(agent);
        return true;
    }

    public Boolean logout() {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
        Long id = Long.valueOf(userDetail.getId());
        Optional<Agent> optional = agentRepo.findById(id);
        if (!optional.isPresent()){
            throw new IllegalArgumentException("Foydalanuvchi topilmadi");
        }
        Agent agent = optional.get();
        agent.setLogoutDate(LocalDateTime.now());
        agent.setIslogged(false);
        agentRepo.save(agent);
        return true;
    }
}
