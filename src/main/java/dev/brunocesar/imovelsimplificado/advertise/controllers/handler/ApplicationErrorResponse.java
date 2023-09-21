package dev.brunocesar.imovelsimplificado.advertise.controllers.handler;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationErrorResponse {

    private int httpStatus;
    private List<String> errorMessage;

}
