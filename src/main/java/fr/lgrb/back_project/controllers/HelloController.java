package fr.lgrb.back_project.controllers;

import fr.lgrb.back_project.entity.LoginRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping
    public String login() {
        return "coucou";
    }


    /**
     * exemple login post api

     */
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();

        // Vérification des informations de connexion (simulée ici)
        if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            response.put("username", loginRequest.getUsername());
            response.put("password", loginRequest.getPassword());
        } else {
            response.put("error", "Invalid credentials");
        }

        return response;  // Retourne un objet JSON
    }
}
