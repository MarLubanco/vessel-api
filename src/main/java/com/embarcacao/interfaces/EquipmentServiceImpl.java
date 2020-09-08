package com.embarcacao.interfaces;

import com.embarcacao.model.Equipment;
import javassist.NotFoundException;

import java.util.List;

public interface EquipmentServiceImpl {

    Equipment save(Equipment equipment) throws Exception;

    void inactiveStatusEquipmentByListCodes(List<String> codes);

    List<Equipment> getAllByVessel(String code) throws NotFoundException;
}
