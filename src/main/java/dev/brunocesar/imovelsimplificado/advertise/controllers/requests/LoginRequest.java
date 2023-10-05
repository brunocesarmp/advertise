package dev.brunocesar.imovelsimplificado.advertise.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Email deve ser informado")
    private String email;

    @NotBlank(message = "Senha deve ser informada")
    private String password;

}
