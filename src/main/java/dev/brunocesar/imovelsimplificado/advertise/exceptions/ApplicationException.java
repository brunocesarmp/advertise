package dev.brunocesar.imovelsimplificado.advertise.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ApplicationException extends RuntimeException {

    private int httpStatus;

    private String message;

    public ApplicationException(int httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
