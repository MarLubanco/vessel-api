package com.embarcacao.repository;

import com.embarcacao.model.Equipment;
import com.embarcacao.model.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    Optional<Equipment> findByCode(String code);

    List<Equipment> findByCodeIn(List<String> codes);

    List<Equipment> findByVesselAndStatus(Vessel vesselOwner, boolean status);
}
