package com.zoomanager.zoo.service;


import com.zoomanager.zoo.model.Cage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CageServiceTest {

    private static int cageId = 0;
    @Autowired
    private CageService cageService;

    @Before
    public void setup() {
        // Creating test data for cage
        Cage cage = new Cage();
        cage.setLocation("SomeLocation");
        cage.setName("cage2");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        cageId = cages.get(0).getId();
    }

    @Test
    public void getAllCageTest() {
        List<Cage> cages = cageService.getAllCages();
        assertNotNull(cages);
    }

    @Test
    public void getAnimalTest() {
        Cage cage = cageService.getCage(cageId);
        assertNotNull(cage);
    }

    @Test
    public void createAnimalTest() {
        Cage cage = new Cage(cageId, "Cage 1", "test location");
        cageService.addCage(cage);
    }

    @Test
    public void updateAnimalTest() {
        Cage cage = new Cage(cageId, "Cage 2", "new location");
        cageService.updateCage(cage);
    }

    @Test
    public void deleteAnimalTest() {
        cageService.deleteCage(cageId);
    }

}
