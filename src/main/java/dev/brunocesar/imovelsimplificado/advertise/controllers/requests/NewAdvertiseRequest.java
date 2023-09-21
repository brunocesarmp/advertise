package dev.brunocesar.imovelsimplificado.advertise.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NewAdvertiseRequest extends AdvertiseRequest {

    @NotBlank(message = "Senha deve ser informada")
    @Size(min = 6, max = 20, message = "Senha deve possuir entre {min} e {max} caracteres")
    private String password;

}
