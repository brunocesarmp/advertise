package dev.brunocesar.imovelsimplificado.advertise.services;

import dev.brunocesar.imovelsimplificado.advertise.configs.security.AdvertiseUserDetails;
import dev.brunocesar.imovelsimplificado.advertise.configs.security.JwtTokenUtil;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.LoginRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.TokenResponse;
import dev.brunocesar.imovelsimplificado.advertise.exceptions.ApplicationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public TokenResponse executeAuthentication(LoginRequest loginRequest) {
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
