package com.embarcacao.service;

import com.embarcacao.EmbarcacaoApplication;
import com.embarcacao.exceptions.EquipmentUniqueException;
import com.embarcacao.exceptions.VesselUniqueException;
import com.embarcacao.model.Equipment;
import com.embarcacao.model.Vessel;
import com.embarcacao.repository.EquipmentRepository;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmbarcacaoApplication.class)
public class EquipmentServiceTest {

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private VesselService vesselService;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    Vessel vessel = new Vessel(1, "MV200");
    Vessel vesselTwo = new Vessel(1, "MV2333");
    Equipment equipment = new Equipment(1, "test", "FF3445", "Brazil", true, vessel);
    Equipment equipmentOne = new Equipment(2, "test", "DDC332", "Brazil", true, vessel);
    Equipment equipmentEqualOne = new Equipment(3, "test", "DDC332", "Brazil", true, vessel);

    @Test
    public void save_saveNewEquipment_ok() throws Exception {
        vesselService.save(vessel);
        Equipment equipmentPersist = equipmentService.save(equipment);
        Assert.assertEquals("FF3445", equipmentPersist.getCode());
    }

    @Test
    public void save_saveNewEquipment_failed() throws Exception {
        vesselService.save(vesselTwo);
        equipmentService.save(equipmentOne);
        thrown.expect(EquipmentUniqueException.class);
        equipmentService.save(equipmentEqualOne);
    }

    @Test
    public void getAllByVessel_findListEquipments_ok() throws NotFoundException {
        List<Equipment> equipments = equipmentService.getAllByVessel("MV200");
        Assert.assertEquals(1, equipments.size());
    }

    @Test
    public void getAllByVessel_findListEquipments_failed() throws NotFoundException {
        thrown.expect(NotFoundException.class);
        equipmentService.getAllByVessel("MV6700");
    }

    @Test
    public void inactiveStatusEquipmentByListCodes_ok() throws Exception {
        Equipment equipmentStatus = new Equipment(10, "test", "BBDF2", "Brazil", true, vessel);
        equipmentService.save(equipmentStatus);
        equipmentService.inactiveStatusEquipmentByListCodes(Arrays.asList("BBDF2"));
        Optional<Equipment> equipamentUpdate = equipmentRepository.findByCode("BBDF2");
        Assert.assertEquals(false, equipamentUpdate.get().isStatus());
    }
}
