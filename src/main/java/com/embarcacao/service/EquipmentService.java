package com.embarcacao.service;

import com.embarcacao.exceptions.EquipmentUniqueException;
import com.embarcacao.interfaces.EquipmentServiceImpl;
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
public class EquipmentService implements EquipmentServiceImpl {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private VesselService vesselService;

    /**
     * Save new equipment
     * @param equipment
     * @return
     * @throws Exception
     */
    @Override
    public Equipment save(Equipment equipment) throws Exception {
        Optional<Equipment> equipmentExist = equipmentRepository.findByCode(equipment.getCode());
        if(!equipmentExist.isPresent()) {
            return equipmentRepository.save(equipment);
        } else {
            throw new EquipmentUniqueException("Error - Existing equipment code");
        }
    }

    /**
     * Inactivates a list of equipment according to its code
     * @param codes
     */
    @Override
    public void inactiveStatusEquipmentByListCodes(List<String> codes) {
        List<Equipment> updateEquipments = equipmentRepository.findByCodeIn(codes).stream()
                .peek(equipment -> equipment.setStatus(false))
                .collect(Collectors.toList());
        equipmentRepository.saveAll(updateEquipments);
    }

    /**
     * Recovers all active equipment in a vessel
     * @param code
     * @return
     * @throws NotFoundException
     */
    @Override
    public List<Equipment> getAllByVessel(String code) throws NotFoundException {
        Vessel vesselOwner = vesselService.getByCode(code);
        return equipmentRepository.findByVesselAndStatus(vesselOwner, true);
    }
}
