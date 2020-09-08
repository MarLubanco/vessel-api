package com.embarcacao.interfaces;

import com.embarcacao.model.Equipment;
import com.embarcacao.model.Vessel;
import javassist.NotFoundException;

import java.util.List;

public interface VesselServiceImpl {

    Vessel save(Vessel vessel) throws Exception;

    List<Equipment> getEquipmentsByVesselId(String code) throws NotFoundException;

    Vessel getByCode(String code) throws Exception;
}
