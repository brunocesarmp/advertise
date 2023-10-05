package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.configs.security.AdvertiseUserDetails;
import dev.brunocesar.imovelsimplificado.advertise.controllers.documentation.IAdvertiseController;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.AdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.NewAdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.AdvertiseResponse;
import dev.brunocesar.imovelsimplificado.advertise.services.AdvertiseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("advertise")
public class AdvertiseController implements IAdvertiseController {

    private final AdvertiseService service;

    public AdvertiseController(AdvertiseService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdvertiseResponse save(@RequestBody @Valid NewAdvertiseRequest request) {
        log.info("Salvando novo Anunciante");
        var result = service.save(request);
        log.info("Novo Anunciante salvo. Id: [{}]", result.getUuid());
        return result;
    }

    @Override
    @GetMapping("me")
    public AdvertiseResponse get(@AuthenticationPrincipal AdvertiseUserDetails advertiseUserDetails) {
        var uuid = advertiseUserDetails.getUuid();
        log.info("Buscando Anunciante. Id: [{}]", uuid);
        var result = service.get(uuid);
        log.info("Anunciante Encontrado. Id: [{}]", uuid);
        return result;
    }

    @Override
    @PutMapping("me")
    public AdvertiseResponse update(@AuthenticationPrincipal AdvertiseUserDetails advertiseUserDetails,
                                    @RequestBody @Valid AdvertiseRequest request) {
        var uuid = advertiseUserDetails.getUuid();
        log.info("Atualizando Anunciante. Id: [{}]", uuid);
        var result = service.update(advertiseUserDetails.getUuid(), request);
        log.info("Anunciante atualizado. Id: [{}]", uuid);
        return result;
    }
}
