package com.embarcacao.controller;

import com.embarcacao.model.Equipment;
import com.embarcacao.model.Vessel;
import com.embarcacao.service.VesselService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vessels")
public class VesselController {

    @Autowired
    private VesselService vesselService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Vessel save(@RequestBody @Validated Vessel vessel) throws Exception {
        return vesselService.save(vessel);
    }

    @GetMapping("{code}/equipments")
    public List<Equipment> getEquipmentsByVesselId(@PathVariable @Validated String code) throws NotFoundException {
        return vesselService.getEquipmentsByVesselId(code);
    }
}
