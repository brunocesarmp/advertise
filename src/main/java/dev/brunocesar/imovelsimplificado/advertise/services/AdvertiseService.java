package dev.brunocesar.imovelsimplificado.advertise.services;

import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.AdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.AdvertiseResponse;
import dev.brunocesar.imovelsimplificado.advertise.domains.entity.Advertise;
import dev.brunocesar.imovelsimplificado.advertise.domains.repository.AdvertiseRepository;
import dev.brunocesar.imovelsimplificado.advertise.exceptions.AdvertiseNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdvertiseService {

    private final AdvertiseRepository repository;

    public AdvertiseService(AdvertiseRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AdvertiseResponse save(AdvertiseRequest request) {
        var entity = convertToEntity(request);
        repository.save(entity);
        return convertToResponse(entity);
    }

    public AdvertiseResponse get(String uuid) {
        var entity = findEntityByUuid(uuid);
        return convertToResponse(entity);
    }

    @Transactional
    public AdvertiseResponse update(String uuid, AdvertiseRequest request) {
        var entity = findEntityByUuid(uuid);
        updateEntity(entity, request);
        repository.save(entity);
        return convertToResponse(entity);
    }

    private Advertise findEntityByUuid(String uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new AdvertiseNotFoundException(uuid));
    }

    private Advertise convertToEntity(AdvertiseRequest request) {
        var entity = new Advertise();
        updateEntity(entity, request);
        return entity;
    }

    private void updateEntity(Advertise entity, AdvertiseRequest request) {
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setEmail(request.getEmail().toLowerCase());
        entity.setPhone(request.getPhone());
    }

    private AdvertiseResponse convertToResponse(Advertise entity) {
        var response = new AdvertiseResponse();
        response.setUuid(entity.getUuid());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        return response;
    }
}
