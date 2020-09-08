package com.embarcacao.service;

import com.embarcacao.model.Equipment;
import com.embarcacao.model.Vessel;
import com.embarcacao.repository.VesselRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VesselService {

    @Autowired
    private VesselRepository vesselRepository;
    @Autowired
    private EquipmentService equipmentService;

    public Vessel save(Vessel vessel) throws Exception {
        Optional<Vessel> vesselExist = vesselRepository.findByCode(vessel.getCode());
        if(!vesselExist.isPresent()) {
            return vesselRepository.save(vessel);
        } else {
            throw new Exception("Error - Existing vessel code");
        }
    }

    public List<Equipment> getEquipmentsByVesselId(String code) throws NotFoundException {
        return equipmentService.getAllByVessel(code);
    }

    public Vessel getByCode(String code) throws NotFoundException {
        return vesselRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Não Existe"));
    }
}
