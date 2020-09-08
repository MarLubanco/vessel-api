package com.embarcacao.service;

import com.embarcacao.exceptions.VesselUniqueException;
import com.embarcacao.interfaces.VesselServiceImpl;
import com.embarcacao.model.Equipment;
import com.embarcacao.model.Vessel;
import com.embarcacao.repository.VesselRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VesselService implements VesselServiceImpl {

    @Autowired
    private VesselRepository vesselRepository;
    @Autowired
    private EquipmentService equipmentService;

    /**
     * Save new Vessel
     * @param vessel
     * @return
     * @throws Exception
     */
    @Override
    public Vessel save(Vessel vessel) throws Exception {
        Optional<Vessel> vesselExist = vesselRepository.findByCode(vessel.getCode());
        if(!vesselExist.isPresent()) {
            return vesselRepository.save(vessel);
        } else {
            throw new VesselUniqueException("Error - Existing vessel code");
        }
    }

    /**
     * Find equipment by vessel code
     * @param code
     * @return
     * @throws NotFoundException
     */
    @Override
    public List<Equipment> getEquipmentsByVesselId(String code) throws NotFoundException {
        return equipmentService.getAllByVessel(code);
    }

    /**
     * Find vessel by code
     * @param code
     * @return
     * @throws NotFoundException
     */
    @Override
    public Vessel getByCode(String code) throws NotFoundException {
        return vesselRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Não Existe"));
    }
}
