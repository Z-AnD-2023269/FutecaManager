package com.andersonlopez.futecaManager.DTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "La password no puede estar vacia")
    private String password;
    
}
