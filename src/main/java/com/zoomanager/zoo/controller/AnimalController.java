package com.zoomanager.zoo.controller;


import com.zoomanager.zoo.model.Animal;
import com.zoomanager.zoo.model.Cage;
import com.zoomanager.zoo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/cages/{cageId}/animals")
    public List<Animal> getAllAnimals(@PathVariable(value = "cageId") int cageId) {
        return animalService.getAllAnimals(cageId);
    }

    @PostMapping("/cages/{cageId}/animals")
    public void createAnimal(@PathVariable(value = "cageId") int cageId, @Valid @RequestBody Animal animal) {
        //The cage is the parent here.
        animal.setCage(new Cage(cageId, "", ""));
        animalService.addAnimal(animal);
    }

    // Get a Single animal
    @GetMapping("/cages/{cageId}/animals/{animalId}")
    public Animal getAnimalById(@PathVariable(value = "animalId") int animalId) {
        return animalService.getAnimal(animalId);
    }

    // Update an Animal
    @PutMapping("/cages/{cageId}/animals/{animalId}")
    public void updateAnimal( @PathVariable(value = "cageId") int cageId,
                             @Valid @RequestBody Animal animal) {
        animal.setCage(new Cage(cageId, "", ""));
        animalService.updateAnimal(animal);
    }

    // Delete an animal
    @DeleteMapping("/cages/{cageId}/animals/{animalId}")
    public void deleteAnimal(@PathVariable(value = "animalId") int animalId) {
        animalService.deleteAnimal(animalId);
    }
}
