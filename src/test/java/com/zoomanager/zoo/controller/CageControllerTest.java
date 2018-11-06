package com.zoomanager.zoo.controller;

import com.google.gson.Gson;
import com.zoomanager.zoo.model.Cage;
import com.zoomanager.zoo.service.CageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CageService cageService;

    @Test
    public void validCagePost() throws Exception {
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage1");

        Gson gson = new Gson();

        mockMvc.perform(post("/api/cages").contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(cage)))
                .andExpect(status().isOk());
    }

    @Test
    public void validCageGet() throws Exception {
        // Creating test data
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage2");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        int id = cages.get(0)
                .getId();

        mockMvc.perform(get("/api/cages/" + id).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void validGetAllCages() throws Exception {

        mockMvc.perform(get("/api/cages").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void validCageEdit() throws Exception {
        // Creating test data
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage2");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        int id = cages.get(0)
                .getId();

        cage.setLocation("newLocation");

        Gson gson = new Gson();

        mockMvc.perform(put("/api/cages/" + id).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(cage)))
                .andExpect(status().isOk());
    }

    @Test
    public void validCageDelete() throws Exception {
        // Creating test data
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage2");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        int id = cages.get(0)
                .getId();

        mockMvc.perform(delete("/api/cages/" + id))
                .andExpect(status().isOk());
    }

}
