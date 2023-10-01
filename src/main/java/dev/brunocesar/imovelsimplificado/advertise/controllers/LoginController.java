package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.LoginRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.TokenResponse;
import dev.brunocesar.imovelsimplificado.advertise.exceptions.ApplicationException;
import dev.brunocesar.imovelsimplificado.advertise.security.AdvertiseUserDetails;
import dev.brunocesar.imovelsimplificado.advertise.security.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public LoginController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            var authentication = authenticationManager.authenticate(authenticationToken);

            var tokenJwt = jwtTokenUtil.getToken((AdvertiseUserDetails) authentication.getPrincipal());

            return new TokenResponse(tokenJwt);
        } catch (Exception e) {
            throw new ApplicationException(401, "Usuário e/ou senha incorretos");
        }
    }
}