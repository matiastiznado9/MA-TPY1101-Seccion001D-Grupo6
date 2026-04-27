package com.conectatarot.backend.controller;

import com.conectatarot.backend.dto.LoginRequestDTO;
import com.conectatarot.backend.dto.LoginResponseDTO;
import com.conectatarot.backend.service.AuthService;
import com.conectatarot.backend.service.TokenBlacklistService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(
            AuthService authService,
            TokenBlacklistService tokenBlacklistService
    ) {
        this.authService = authService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO request
    ) {
        LoginResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/perfil")
    public ResponseEntity<String> perfil() {
        return ResponseEntity.ok("Acceso permitido con JWT");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Acceso permitido solo para ADMIN");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.replace("Bearer ", "");
        tokenBlacklistService.blacklistToken(token);

        return ResponseEntity.ok("Sesión cerrada correctamente");
    }
}