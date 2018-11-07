package com.zoomanager.zoo.controller;


import com.zoomanager.zoo.model.Animal;
import com.zoomanager.zoo.model.Cage;
import com.zoomanager.zoo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/cages/{cageId}/animals")
    public ResponseEntity<Object> getAnimalsByCage(@PathVariable(value = "cageId") int cageId) {
        return new ResponseEntity<>(animalService.getAnimalsByCage(cageId), HttpStatus.OK);
    }

    @PostMapping("/cages/{cageId}/animals")
    public ResponseEntity<Animal> createAnimal(@PathVariable(value = "cageId") int cageId, @Valid @RequestBody Animal
            animal) {
        //The cage is the parent here.
        animal.setCage(new Cage(cageId, "", ""));
        animalService.addAnimal(animal);
        return new ResponseEntity<>(
                animal,
                HttpStatus.CREATED);
    }

    // Get a Single animal
    @GetMapping("/cages/{cageId}/animals/{animalId}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable(value = "animalId") int animalId) {
        Animal animal = animalService.getAnimal(animalId);
        return new ResponseEntity<>(
                animal,
                HttpStatus.OK);
    }

    // Update an Animal
    @PutMapping("/cages/{cageId}/animals/{animalId}")
    public ResponseEntity<String> updateAnimal(@PathVariable(value = "cageId") int cageId,
                                               @Valid @RequestBody Animal animal) {
        animal.setCage(new Cage(cageId, "", ""));
        animalService.updateAnimal(animal);
        return new ResponseEntity<>(
                "The Animal has been updated.",
                HttpStatus.OK);
    }

    // Delete an animal
    @DeleteMapping("/cages/{cageId}/animals/{animalId}")
    public ResponseEntity<String> deleteAnimal(@PathVariable(value = "animalId") int animalId) {
        animalService.deleteAnimal(animalId);
        return new ResponseEntity<>(
                "The Animal has been deleted.",
                HttpStatus.OK);
    }
}
