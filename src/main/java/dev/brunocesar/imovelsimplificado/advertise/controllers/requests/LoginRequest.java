package dev.brunocesar.imovelsimplificado.advertise.controllers.requests;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;

}
