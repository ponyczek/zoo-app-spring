package com.zoomanager.zoo.controller;

import com.google.gson.Gson;
import com.zoomanager.zoo.model.Animal;
import com.zoomanager.zoo.model.AnimalClass;
import com.zoomanager.zoo.model.Cage;
import com.zoomanager.zoo.service.AnimalService;
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
public class AnimalCotrollerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CageService cageService;

    @Autowired
    private AnimalService animalService;

    @Test
    public void validAnimalPost() throws Exception {
        // Creating test data
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage2");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        int cageId = cages.get(0)
                .getId();

        Animal animal = new Animal("NameOne", 23, "someurl", AnimalClass.MAMMAL, cageId);
        Gson gson = new Gson();

        mockMvc.perform(post("/api/cages/" + cageId + "/animals").contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(animal)))
                .andExpect(status().isOk());
    }

    @Test
    public void validAnimalGet() throws Exception {
        // Creating test data for cage
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage2");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        int cageId = cages.get(0)
                .getId();
        // Creating test data for animal
        Animal animal = new Animal("NameTwo", 23, "someurl", AnimalClass.MAMMAL, cageId);
        animalService.addAnimal(animal);
        List<Animal> animals = animalService.getAnimalsByCage(cageId);
        int animalId = animals.get(0)
                .getId();

        mockMvc.perform(get("/api/cages/" + cageId + "/animals/" + animalId))
                .andExpect(status().isOk());

    }

    @Test
    public void validGetAnimalsForSelectedCage() throws Exception {
        // Creating test data for cage
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage2");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        int cageId = cages.get(0)
                .getId();

        mockMvc.perform(get("/api/cages/" + cageId + "/animals/"))
                .andExpect(status().isOk());
    }

    @Test
    public void validAnimalUpdate() throws Exception {
        // Creating test data for cage
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage2");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        int cageId = cages.get(0)
                .getId();
        Animal animal = new Animal("NameThree", 23, "someurl", AnimalClass.MAMMAL, cageId);
        animalService.addAnimal(animal);
        List<Animal> animals = animalService.getAnimalsByCage(cageId);
        int animalId = animals.get(0)
                .getId();
        animal.setAnimalClass(AnimalClass.REPTILE);

        Gson gson = new Gson();
        mockMvc.perform(put("/api/cages/" + cageId + "/animals/" + animalId).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(animal)))
                .andExpect(status().isOk());

    }

    @Test
    public void validAnimalDelete() throws Exception {
        // Creating test data for cage
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage2");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        int cageId = cages.get(0)
                .getId();
        Animal animal = new Animal("NameThree", 23, "someurl", AnimalClass.MAMMAL, cageId);
        animalService.addAnimal(animal);
        List<Animal> animals = animalService.getAnimalsByCage(cageId);
        int animalId = animals.get(0)
                .getId();

        mockMvc.perform(delete("/api/cages/" + cageId + "/animals/" + animalId))
                .andExpect(status().isOk());
    }

}
