package dev.brunocesar.imovelsimplificado.advertise.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    public static final String HOME_MESSAGE = "Advertise API - Imóvel Simplificado Software - Pós Puc Minas - Arquitetura de Software Distribuído";

    @GetMapping
    public String home() {
        return HOME_MESSAGE;
    }
}
