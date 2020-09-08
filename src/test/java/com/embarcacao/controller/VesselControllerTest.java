package com.embarcacao.controller;

import com.embarcacao.model.Vessel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class VesselControllerTest {

    @Autowired
    private MockMvc mvc;

    Vessel vessel = new Vessel(1, "MV11200");
    Vessel vesselOne = new Vessel(2, "XXUO30");
    Vessel vesselTwo = new Vessel(3, "XXUO30");
    Vessel vesselFind = new Vessel(4, "VV3244");

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void save_saveNewVessel_ok() throws Exception {
        this.mvc.perform(post("/vessels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vessel)));
    }

    @Test
    public void getByCode_ok() throws Exception {
        this.mvc.perform(
                get("/vessels/MV11200/equipments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }
}


