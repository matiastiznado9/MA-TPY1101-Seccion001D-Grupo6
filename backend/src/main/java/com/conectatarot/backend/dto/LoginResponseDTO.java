package com.conectatarot.backend.dto;

public class LoginResponseDTO {

    private Integer idUsuario;
    private String nombre;
    private String email;
    private String rol;
    private Boolean activo;
    private String token;

    public LoginResponseDTO(
            Integer idUsuario,
            String nombre,
            String email,
            String rol,
            Boolean activo,
            String token
    ) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.activo = activo;
        this.token = token;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getRol() {
        return rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public String getToken() {
        return token;
    }
}