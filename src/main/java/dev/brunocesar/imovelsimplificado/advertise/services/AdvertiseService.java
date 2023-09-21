package dev.brunocesar.imovelsimplificado.advertise.services;

import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.AdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.requests.NewAdvertiseRequest;
import dev.brunocesar.imovelsimplificado.advertise.controllers.responses.AdvertiseResponse;
import dev.brunocesar.imovelsimplificado.advertise.domains.Advertise;
import dev.brunocesar.imovelsimplificado.advertise.domains.AdvertiseRepository;
import dev.brunocesar.imovelsimplificado.advertise.exceptions.AdvertiseNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdvertiseService {

    private final AdvertiseRepository repository;

    public AdvertiseService(AdvertiseRepository repository) {
        this.repository = repository;
    }

    public AdvertiseResponse save(NewAdvertiseRequest request) {
        var entity = convertToEntity(request);
        repository.save(entity);
        return convertToResponse(entity);
    }

    public AdvertiseResponse get(Long id) {
        var entity = findEntityById(id);
        return convertToResponse(entity);
    }

    public AdvertiseResponse update(Long id, AdvertiseRequest request) {
        var entity = findEntityById(id);
        updateEntity(entity, request);
        repository.save(entity);
        return convertToResponse(entity);
    }

    private Advertise findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AdvertiseNotFoundException(id));
    }

    private void updateEntity(Advertise entity, AdvertiseRequest request) {
        entity.setFirstName(request.getFirstName().toUpperCase());
        entity.setLastName(request.getLastName().toUpperCase());
        entity.setEmail(request.getEmail().toLowerCase());
        entity.setPhone(request.getPhone());
    }

    private Advertise convertToEntity(NewAdvertiseRequest request) {
        var entity = new Advertise();
        entity.setFirstName(request.getFirstName().toUpperCase());
        entity.setLastName(request.getLastName().toUpperCase());
        entity.setEmail(request.getEmail().toLowerCase());
        entity.setPhone(request.getPhone());
        return entity;
    }

    private AdvertiseResponse convertToResponse(Advertise entity) {
        var response = new AdvertiseResponse();
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        return response;
    }
}
