package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.controllers.documentation.IAuthController;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.LoginRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.TokenResponse;
import dev.brunocesar.imovelsimplificado.advertise.services.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class AuthController implements IAuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        log.info("Efetuando login. Username: [{}]", loginRequest.getEmail());
        var result = service.executeAuthentication(loginRequest);
        log.info("Autenticacao feita com sucesso. Username: [{}]", loginRequest.getEmail());
        return result;
    }
}