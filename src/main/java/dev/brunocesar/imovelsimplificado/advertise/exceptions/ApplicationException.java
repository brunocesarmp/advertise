package dev.brunocesar.imovelsimplificado.advertise.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ApplicationException extends RuntimeException {

    private final int httpStatus;

    private final String message;

    public ApplicationException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
