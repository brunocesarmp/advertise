package dev.brunocesar.imovelsimplificado.advertise.domains.repository;

import dev.brunocesar.imovelsimplificado.advertise.domains.entity.Advertise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertiseRepository extends JpaRepository<Advertise, String> {

    Optional<Advertise> findByEmail(String email);

}
