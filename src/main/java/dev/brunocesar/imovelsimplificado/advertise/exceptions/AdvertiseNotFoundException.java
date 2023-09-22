package dev.brunocesar.imovelsimplificado.advertise.exceptions;

import org.springframework.http.HttpStatus;

public class AdvertiseNotFoundException extends ApplicationException {

    public AdvertiseNotFoundException(String uuid) {
        super(HttpStatus.NOT_FOUND.value(), "Anunciante com uuid [" + uuid + "] não encontrado");
    }
}
