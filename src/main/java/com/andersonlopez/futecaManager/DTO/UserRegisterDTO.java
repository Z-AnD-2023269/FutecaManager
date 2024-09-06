package com.andersonlopez.futecaManager.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterDTO {
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
    @NotBlank(message = "El apellido no puede estar vacio")
    private String surname;
    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "El correo no puede estar vacio")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "La password no puede estar vacia")
    private String password;
    
}
