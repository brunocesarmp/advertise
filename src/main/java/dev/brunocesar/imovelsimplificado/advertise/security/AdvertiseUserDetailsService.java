package dev.brunocesar.imovelsimplificado.advertise.security;

import dev.brunocesar.imovelsimplificado.advertise.domains.repository.AdvertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertiseUserDetailsService implements UserDetailsService {

    @Autowired
    private AdvertiseRepository advertiseRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var advertiseOpt = advertiseRepository.findByEmail(email);
        if (advertiseOpt.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        var advertise = advertiseOpt.get();
        return new AdvertiseUserDetails(advertise.getUuid(), advertise.getEmail(), advertise.getPassword(), List.of(new SimpleGrantedAuthority("ADMIN")));
    }
}