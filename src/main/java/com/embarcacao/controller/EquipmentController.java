package com.embarcacao.controller;

import com.embarcacao.model.Equipment;
import com.embarcacao.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Equipment save(@RequestBody @Validated Equipment equipment) throws Exception {
        return equipmentService.save(equipment);
    }

    @PutMapping
    public void updateStatusEquipment(@RequestBody @Validated List<String> codes) {
        equipmentService.inactiveStatusEquipmentByListCodes(codes);
    }
}
