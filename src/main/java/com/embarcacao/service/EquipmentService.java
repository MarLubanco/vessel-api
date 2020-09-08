package com.embarcacao.service;

import com.embarcacao.exceptions.EquipmentUniqueException;
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
        Optional<Equipment> equipmentExist = equipmentRepository.findByCode(equipment.getCode());
        if(!equipmentExist.isPresent()) {
            return equipmentRepository.save(equipment);
        } else {
            throw new EquipmentUniqueException("Error - Existing equipment code");
        }
    }

    public void inactiveStatusEquipmentByListCodes(List<String> codes) {
        List<Equipment> updateEquipments = equipmentRepository.findByCodeIn(codes).stream()
                .peek(equipment -> equipment.setStatus(false))
                .collect(Collectors.toList());
        equipmentRepository.saveAll(updateEquipments);
    }

    public List<Equipment> getAllByVessel(String code) throws NotFoundException {
        Vessel vesselOwner = vesselService.getByCode(code);
        return equipmentRepository.findByVesselAndStatus(vesselOwner, true);
    }
}
