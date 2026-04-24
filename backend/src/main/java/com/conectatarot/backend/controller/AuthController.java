package com.conectatarot.backend.controller;

import com.conectatarot.backend.dto.LoginRequestDTO;
import com.conectatarot.backend.dto.LoginResponseDTO;
import com.conectatarot.backend.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/perfil")
public ResponseEntity<String> perfil() {
    return ResponseEntity.ok("Acceso permitido con JWT");
}

}