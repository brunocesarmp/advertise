package dev.brunocesar.imovelsimplificado.advertise.services;

import dev.brunocesar.imovelsimplificado.advertise.configs.security.AdvertiseUserDetails;
import dev.brunocesar.imovelsimplificado.advertise.configs.security.JwtTokenUtil;
import dev.brunocesar.imovelsimplificado.advertise.exceptions.ApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;

import static dev.brunocesar.imovelsimplificado.advertise.utils.TestDataCreator.buildLoginRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    @InjectMocks
    private AuthService service;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldDoLoginWithSuccess() {
        var loginRequest = buildLoginRequest();
        var authentication = new TestingAuthenticationToken(new AdvertiseUserDetails(), "credentials");
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtTokenUtil.getToken(any())).thenReturn("token");

        var result = service.executeAuthentication(loginRequest);

        verify(authenticationManager).authenticate(any());
        verify(jwtTokenUtil).getToken(any());

        assertNotNull(result);
    }

    @Test
    public void shouldReturnUnauthorizedWhenCredentialsIsInvalid() {
        var loginRequest = buildLoginRequest();
        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException("erro"));

        var exception = assertThrows(ApplicationException.class,
                () -> service.executeAuthentication(loginRequest));

        assertEquals(401, exception.getHttpStatus());
        assertEquals(ApplicationException.class, exception.getClass());
    }

}