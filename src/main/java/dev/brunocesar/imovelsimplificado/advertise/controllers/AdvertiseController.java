package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.controllers.documentation.IAdvertiseController;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.AdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.NewAdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.AdvertiseResponse;
import dev.brunocesar.imovelsimplificado.advertise.configs.security.AdvertiseUserDetails;
import dev.brunocesar.imovelsimplificado.advertise.services.AdvertiseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        return service.save(request);
    }

    @Override
    @GetMapping("me")
    public AdvertiseResponse get(@AuthenticationPrincipal AdvertiseUserDetails advertiseUserDetails) {
        return service.get(advertiseUserDetails.getUuid());
    }

    @Override
    @PutMapping("me")
    public AdvertiseResponse update(@AuthenticationPrincipal AdvertiseUserDetails advertiseUserDetails,
                                    @RequestBody @Valid AdvertiseRequest request) {
        return service.update(advertiseUserDetails.getUuid(), request);
    }
}
