package com.embarcacao.controller;

import com.embarcacao.model.Equipment;
import com.embarcacao.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public Equipment save(@RequestBody Equipment equipment) throws Exception {
        return equipmentService.save(equipment);
    }

    @PutMapping
    public void updateStatusEquipment(@RequestBody List<String> codes) {
        equipmentService.inactiveStatusEquipmentByListCodes(codes);
    }
}
