package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.AdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.NewAdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.AdvertiseResponse;
import dev.brunocesar.imovelsimplificado.advertise.security.AdvertiseUserDetails;
import dev.brunocesar.imovelsimplificado.advertise.services.AdvertiseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("advertise")
public class AdvertiseController {

    private final AdvertiseService service;

    public AdvertiseController(AdvertiseService service) {
        this.service = service;
    }

    @PostMapping
    public AdvertiseResponse save(@RequestBody @Valid NewAdvertiseRequest request) {
        return service.save(request);
    }

    @GetMapping("me")
    public AdvertiseResponse get(@AuthenticationPrincipal AdvertiseUserDetails advertiseUserDetails) {
        return service.get(advertiseUserDetails.getUuid());
    }

    @PutMapping("me")
    public AdvertiseResponse update(@AuthenticationPrincipal AdvertiseUserDetails advertiseUserDetails,
                                    @RequestBody @Valid AdvertiseRequest request) {
        return service.update(advertiseUserDetails.getUuid(), request);
    }
}
