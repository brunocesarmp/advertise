package dev.brunocesar.imovelsimplificado.advertise.utils;

import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.AdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.LoginRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.NewAdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.domains.entity.Advertise;

public final class TestDataCreator {

    private TestDataCreator() {
    }

    public static AdvertiseRequest buildAdvertiseRequest() {
        var request = new AdvertiseRequest();
        request.setFirstName("Primeiro Nome");
        request.setLastName("Ultimo Nome");
        request.setEmail("email@test.com");
        request.setPhone("11987654321");
        return request;
    }

    public static NewAdvertiseRequest buildNewAdvertiseRequest() {
        var request = new NewAdvertiseRequest();
        request.setFirstName("Primeiro Nome");
        request.setLastName("Ultimo Nome");
        request.setEmail("email@test.com");
        request.setPhone("11987654321");
        request.setPassword("123456");
        return request;
    }

    public static Advertise buildAdvertise() {
        return new Advertise();
    }

    public static LoginRequest buildLoginRequest() {
        var request = new LoginRequest();
        request.setEmail("email@test.com");
        request.setPassword("123456");
        return request;
    }
}