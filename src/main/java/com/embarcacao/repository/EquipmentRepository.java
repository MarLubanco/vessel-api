package com.embarcacao.repository;

import com.embarcacao.model.Equipment;
import com.embarcacao.model.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    Optional<Vessel> findByCode(String code);

    List<Equipment> findByIdIn(List<Integer> idsEquipmente);

    List<Equipment> findByVesselAndStatus(Vessel vesselOwner, boolean status);
}
