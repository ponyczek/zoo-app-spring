package com.zoomanager.zoo.service;

import com.zoomanager.zoo.model.Animal;
import com.zoomanager.zoo.model.AnimalClass;
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
public class AnimalServiceTest {

    private static int animalId = 0;
    private static int cageId = 0;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private CageService cageService;

    @Before
    public void setup() {
        // Creating test data for cage
        Cage cage = new Cage();
        cage.setLocation("Test Location");
        cage.setName("Test Cage");
        cageService.addCage(cage);
        List<Cage> cages = cageService.getAllCages();
        cageId = cages.get(0).getId();
        // Creating test data for animal
        Animal animal = new Animal("Lion", 23, "someurl", AnimalClass.MAMMAL, cageId);
        animalService.addAnimal(animal);
        List<Animal> animals = animalService.getAnimalsByCage(cageId);
        animalId = animals.get(0).getId();
    }

    @Test
    public void getAnimalsByCage() {
        List<Animal> animals = animalService.getAnimalsByCage(cageId);
        assertNotNull(animals);
    }

    @Test
    public void getAnimalTest() {
        Animal animal = animalService.getAnimal(animalId);
        assertNotNull(animal);
    }

    @Test
    public void createAnimalTest() {
        Animal animal = new Animal("Lion", 11, "someurl", AnimalClass.MAMMAL, cageId);
        animalService.addAnimal(animal);
    }

    @Test
    public void updateAnimalTest() {
        Animal animal = new Animal("Lion", 12, "updatedUrl", AnimalClass.MAMMAL, cageId);
        animal.setAge(animalId);
        animalService.updateAnimal(animal);
    }

    @Test
    public void deleteAnimalTest() {
        animalService.deleteAnimal(animalId);
    }

}
