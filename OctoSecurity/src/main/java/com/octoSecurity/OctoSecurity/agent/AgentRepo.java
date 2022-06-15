package com.octoSecurity.OctoSecurity.agent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AgentRepo extends JpaRepository<Agent, Long> {
    Optional<Agent> findByNickName(String s);
    Optional<Agent> findByNickNameAndPassword(String nickName, String password);
}
