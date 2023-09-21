package dev.brunocesar.imovelsimplificado.advertise.controllers;

import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.AdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.NewAdvertiseRequest;
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
    public AdvertiseResponse save(@RequestBody @Valid NewAdvertiseRequest request) {
        return service.save(request);
    }

    @GetMapping("{id}")
    public AdvertiseResponse get(@PathVariable @NotNull Long id) {
        return service.get(id);
    }

    @PutMapping("{id}")
    public AdvertiseResponse update(@PathVariable @NotNull Long id,
                                    @RequestBody @Valid AdvertiseRequest request) {
        return service.update(id, request);
    }
}
