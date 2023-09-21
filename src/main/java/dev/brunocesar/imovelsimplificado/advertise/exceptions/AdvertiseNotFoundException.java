package dev.brunocesar.imovelsimplificado.advertise.exceptions;

import org.springframework.http.HttpStatus;

public class AdvertiseNotFoundException extends ApplicationException {

    public AdvertiseNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND.value(), "Anunciante com id " + id + " não encontrado");
    }
}
