package com.embarcacao.repository;

import com.embarcacao.model.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VesselRepository extends JpaRepository<Vessel, Integer> {

    Optional<Vessel> findByCode(String code);
}
