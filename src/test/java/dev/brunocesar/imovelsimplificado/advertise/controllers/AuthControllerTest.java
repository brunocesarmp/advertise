package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.TokenResponse;
import dev.brunocesar.imovelsimplificado.advertise.exceptions.ApplicationException;
import dev.brunocesar.imovelsimplificado.advertise.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import static dev.brunocesar.imovelsimplificado.advertise.utils.TestDataCreator.buildLoginRequest;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest extends AbstractBaseControllerTest {

    @Mock
    private AuthService service;

    @Override
    public Object getController() {
        return new AuthController(service);
    }

    @Test
    public void shouldReturnBadRequestOnLoginWhenRequestIsInvalid() {
        var loginRequest = buildLoginRequest();
        loginRequest.setEmail(null);
        super.webTestClient.post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginRequest)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    public void shouldDoLoginWithSuccess() {
        var loginRequest = buildLoginRequest();
        when(service.executeAuthentication(loginRequest)).thenReturn(new TokenResponse());
        super.webTestClient.post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginRequest)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(TokenResponse.class);
    }

    @Test
    public void shouldReturnUnauthorizedWhenCredentialsIsInvalid() {
        var loginRequest = buildLoginRequest();
        when(service.executeAuthentication(loginRequest)).thenThrow(new ApplicationException(401, "Usuário inválido"));
        super.webTestClient.post()
                .uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loginRequest)
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }
}