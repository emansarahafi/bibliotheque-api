package com.example.bibliotheque.controller; 
 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
 
@Tag(name = "Test", description = "Endpoints de test")
@RestController 
public class HelloController { 
 
    @Operation(
        summary = "Message de bienvenue",
        description = "Retourne un message de bienvenue simple",
        responses = {
            @ApiResponse(responseCode = "200", description = "Message retourné avec succès")
        }
    )
    @GetMapping("/hello") 
    public String sayHello() { 
        return "Bonjour, Spring Boot !"; 
    } 
}