package dev.brunocesar.imovelsimplificado.advertise.domains.repository;

import dev.brunocesar.imovelsimplificado.advertise.domains.entity.Advertise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiseRepository extends JpaRepository<Advertise, String> {
}
