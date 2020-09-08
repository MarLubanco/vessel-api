package com.embarcacao.service;


import com.embarcacao.EmbarcacaoApplication;
import com.embarcacao.exceptions.VesselUniqueException;
import com.embarcacao.model.Vessel;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmbarcacaoApplication.class)
public class VesselServiceTest {

    @Autowired
    private VesselService vesselService;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    Vessel vessel = new Vessel(1, "MV200");
    Vessel vesselOne = new Vessel(2, "XXUO30");
    Vessel vesselTwo = new Vessel(3, "XXUO30");
    Vessel vesselFind = new Vessel(4, "VV3244");

    @Test
    public void save_saveNewVessel_ok() throws Exception {
        Vessel vesselPersist = vesselService.save(vessel);
        Assert.assertEquals("MV200", vesselPersist.getCode());
    }

    @Test
    public void save_saveNewVessel_failed() throws Exception {
        vesselService.save(vesselOne);
        thrown.expect(VesselUniqueException.class);
        vesselService.save(vesselTwo);
    }

    @Test
    public void getByCode_findVesselByCode_ok() throws Exception {
        vesselService.save(vesselFind);
        Vessel vessel = vesselService.getByCode("VV3244");
        Assert.assertEquals("VV3244", vessel.getCode());
    }

    @Test
    public void getByCode_findVesselByCode_failed() throws Exception {
        thrown.expect(NotFoundException.class);
        vesselService.getByCode("EEE4345");
    }

}
