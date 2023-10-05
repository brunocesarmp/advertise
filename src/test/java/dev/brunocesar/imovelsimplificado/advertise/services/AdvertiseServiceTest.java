package dev.brunocesar.imovelsimplificado.advertise.services;

import dev.brunocesar.imovelsimplificado.advertise.domains.repository.AdvertiseRepository;
import dev.brunocesar.imovelsimplificado.advertise.exceptions.AdvertiseNotFoundException;
import dev.brunocesar.imovelsimplificado.advertise.exceptions.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static dev.brunocesar.imovelsimplificado.advertise.utils.TestDataCreator.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdvertiseServiceTest {

    @InjectMocks
    private AdvertiseService service;

    @Mock
    private AdvertiseRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnAdvertiseWithSuccess() {
        var id = "mock-id";
        when(repository.findById(id)).thenReturn(Optional.of(buildAdvertise()));

        var result = service.get(id);
        assertNotNull(result);
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenAdvertiseNotFound() {
        var id = "mock-id";
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AdvertiseNotFoundException.class, () -> service.get(id));
    }

    @Test
    public void shouldSaveWithSuccess() {
        var newAdvertiseRequest = buildNewAdvertiseRequest();
        when(repository.findByEmail(newAdvertiseRequest.getEmail())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(buildAdvertise());

        var result = service.save(newAdvertiseRequest);

        verify(repository).findByEmail(newAdvertiseRequest.getEmail());
        verify(repository).save(any());
        assertNotNull(result);
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsAlreadyRegistered() {
        var newAdvertiseRequest = buildNewAdvertiseRequest();
        when(repository.findByEmail(newAdvertiseRequest.getEmail())).thenReturn(Optional.of(buildAdvertise()));

        assertThrows(ApplicationException.class,
                () -> service.save(newAdvertiseRequest));

        verify(repository).findByEmail(newAdvertiseRequest.getEmail());
        verify(repository, never()).save(any());
    }

    @Test
    public void shouldUpdateAdvertiseWithSuccess() {
        var id = "mock-id";
        var email = "mock-email";
        var advertiseRequest = buildAdvertiseRequest();
        advertiseRequest.setEmail(email);
        var advertiseEntity = buildAdvertise();
        advertiseEntity.setEmail(email);
        advertiseEntity.setUuid(id);

        when(repository.findById(id)).thenReturn(Optional.of(advertiseEntity));
        when(repository.findByEmail(email)).thenReturn(Optional.of(advertiseEntity));
        when(repository.save(any())).thenReturn(advertiseEntity);

        var result = service.update(id, advertiseRequest);

        verify(repository).findByEmail(email);
        verify(repository).findById(id);
        verify(repository).save(any());

        assertNotNull(result);
    }

    @Test
    public void shouldUpdateAdvertiseWithEmailAvailableWithSuccess() {
        var id = "mock-id";
        var email = "mock-email";
        var advertiseRequest = buildAdvertiseRequest();
        advertiseRequest.setEmail(email);
        var advertiseEntity = buildAdvertise();
        advertiseEntity.setEmail(email);
        advertiseEntity.setUuid(id);

        when(repository.findById(id)).thenReturn(Optional.of(advertiseEntity));
        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(advertiseEntity);

        var result = service.update(id, advertiseRequest);

        verify(repository).findByEmail(email);
        verify(repository).findById(id);
        verify(repository).save(any());

        assertNotNull(result);
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsAlreadyRegisteredOnUpdate() {
        var id = "mock-id";
        var email = "mock-email";
        var advertiseRequest = buildAdvertiseRequest();
        advertiseRequest.setEmail(email);

        var advertiseEntity = buildAdvertise();
        advertiseEntity.setUuid(id);
        advertiseEntity.setEmail(email);

        var advertiseEntity2 = buildAdvertise();
        advertiseEntity.setUuid(id + "2");

        when(repository.findById(id)).thenReturn(Optional.of(advertiseEntity));
        when(repository.findByEmail(email)).thenReturn(Optional.of(advertiseEntity2));
        when(repository.save(any())).thenReturn(advertiseEntity);

        assertThrows(ApplicationException.class,
                () -> service.update(id, advertiseRequest));

        verify(repository).findByEmail(email);
        verify(repository).findById(id);
        verify(repository, never()).save(any());
    }

}