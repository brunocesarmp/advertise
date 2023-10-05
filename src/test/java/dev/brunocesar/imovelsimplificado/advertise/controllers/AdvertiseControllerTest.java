package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.AdvertiseResponse;
import dev.brunocesar.imovelsimplificado.advertise.services.AdvertiseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import static dev.brunocesar.imovelsimplificado.advertise.utils.TestDataCreator.buildAdvertiseRequest;
import static dev.brunocesar.imovelsimplificado.advertise.utils.TestDataCreator.buildNewAdvertiseRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdvertiseControllerTest extends AbstractBaseControllerTest {

    @Mock
    private AdvertiseService service;

    private AdvertiseResponse advertiseResponse;

    @Override
    protected void beforeEach() {
        advertiseResponse = new AdvertiseResponse();
    }

    @Override
    public Object getController() {
        return new AdvertiseController(service);
    }

    @Test
    public void shouldGetAdvertiseWithSuccess() {
        when(service.get(Mockito.any())).thenReturn(advertiseResponse);
        super.webTestClient.get()
                .uri("/advertise/me")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AdvertiseResponse.class);
    }

    @Test
    public void shouldSaveAdvertiseWithSuccess() {
        var newAdvertiseRequest = buildNewAdvertiseRequest();
        when(service.save(newAdvertiseRequest)).thenReturn(advertiseResponse);
        super.webTestClient.post()
                .uri("/advertise")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newAdvertiseRequest)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(AdvertiseResponse.class);
    }

    @Test
    public void shouldReturnBadRequestOnSaveWhenRequestIsInvalid() {
        var newAdvertiseRequest = buildNewAdvertiseRequest();
        newAdvertiseRequest.setFirstName(null);
        super.webTestClient.post()
                .uri("/advertise")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newAdvertiseRequest)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @Test
    public void shouldUpdateAdvertiseWithSuccess() {
        var advertiseRequest = buildAdvertiseRequest();
        when(service.update(any(), any())).thenReturn(advertiseResponse);
        super.webTestClient.put()
                .uri("/advertise/me")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(advertiseRequest)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AdvertiseResponse.class);
    }

    @Test
    public void shouldReturnBadRequestOnUpdateWhenRequestIsInvalid() {
        var advertiseRequest = buildAdvertiseRequest();
        advertiseRequest.setFirstName(null);
        super.webTestClient.put()
                .uri("/advertise/me")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(advertiseRequest)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}