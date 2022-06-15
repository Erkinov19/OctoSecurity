package com.octoSecurity.OctoSecurity.agent;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
public class AgentController {
    private final AgentService agentService;


    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/reg")
    public ResponseEntity<?> reg(@RequestBody Agent agent){
        Agent result = agentService.register(agent);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginAgent agent){
        Boolean result = agentService.login(agent);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        Boolean result = agentService.logout();
        return ResponseEntity.ok(result);
    }

}
