package dev.brunocesar.imovelsimplificado.advertise.controllers.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdvertiseRequest {

    @NotBlank(message = "Primeiro nome deve ser informado")
    @Size(min = 3, max = 100, message = "Primeiro nome deve possuir entre {min} e {max} caracteres")
    private String firstName;

    @NotBlank(message = "Último nome deve ser informado")
    @Size(min = 3, max = 100, message = "Último nome deve possuir entre {min} e {max} caracteres")
    private String lastName;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email deve ser informado")
    @Size(min = 3, max = 100, message = "Email deve possuir entre {min} e {max} caracteres")
    private String email;

    @Pattern(regexp = "\\d{10}|\\d{11}", message = "Telefone celular inválido. Só informar números. Ex.: 11912345314")
    @NotBlank(message = "Telefone de contato deve ser informado")
    @Size(min = 10, max = 11, message = "Telefone de contato deve conter entre {min} e {max} números. Ex.: 11912345314")
    private String phone;

    private String password;

}
