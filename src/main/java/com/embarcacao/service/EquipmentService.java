package com.embarcacao.service;

import com.embarcacao.model.Equipment;
import com.embarcacao.model.Vessel;
import com.embarcacao.repository.EquipmentRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private VesselService vesselService;

    public Equipment save(Equipment equipment) throws Exception {
        Optional<Vessel> vesselExist = equipmentRepository.findByCode(equipment.getCode());
        if(!vesselExist.isPresent()) {
            return equipmentRepository.save(equipment);
        } else {
            throw new Exception("Error - Existing vessel code");
        }
    }

    public void inactiveStatusEquipmentByListId(List<Integer> idsEquipmente) {
        List<Equipment> updateEquipments = equipmentRepository.findByIdIn(idsEquipmente).stream()
                .peek(equipment -> equipment.setStatus(false))
                .collect(Collectors.toList());
        equipmentRepository.saveAll(updateEquipments);
    }

    public List<Equipment> getAllByVessel(String code) throws NotFoundException {
        Vessel vesselOwner = vesselService.getByCode(code);
        return equipmentRepository.findByVesselAndStatus(vesselOwner, true);
    }
}
