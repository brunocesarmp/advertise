package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.configs.handler.ControllerExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(RestDocumentationExtension.class)
abstract class AbstractBaseControllerTest {

    protected WebTestClient webTestClient;

    public abstract Object getController();

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient
                .bindToController(getController())
                .controllerAdvice(ControllerExceptionHandler.class)
                .configureClient()
                .build();
        beforeEach();
    }

    protected void beforeEach() {
    }
}
