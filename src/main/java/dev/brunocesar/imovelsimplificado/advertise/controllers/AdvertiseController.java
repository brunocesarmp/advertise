package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.AdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.AdvertiseResponse;
import dev.brunocesar.imovelsimplificado.advertise.services.AdvertiseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("advertise")
public class AdvertiseController {

    private final AdvertiseService service;

    public AdvertiseController(AdvertiseService service) {
        this.service = service;
    }

    @PostMapping
    public AdvertiseResponse save(@RequestBody @Valid AdvertiseRequest request) {
        return service.save(request);
    }

    @GetMapping("{uuid}")
    public AdvertiseResponse get(@PathVariable @NotNull String uuid) {
        return service.get(uuid);
    }

    @PutMapping("{id}")
    public AdvertiseResponse update(@PathVariable @NotNull String uuid,
                                    @RequestBody @Valid AdvertiseRequest request) {
        return service.update(uuid, request);
    }
}
