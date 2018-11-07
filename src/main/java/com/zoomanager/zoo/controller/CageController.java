package com.zoomanager.zoo.controller;


import com.zoomanager.zoo.model.Cage;
import com.zoomanager.zoo.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CageController {

    @Autowired
    private CageService cageService;

    // Get All Cages
    @GetMapping("/cages")
    public ResponseEntity<Object> getAllCages() {
        return new ResponseEntity<>(cageService.getAllCages(), HttpStatus.OK);
    }

    // Create a new Cage
    @PostMapping("/cages")
    public ResponseEntity<Cage> createCage(@Valid @RequestBody Cage cage) {
        cageService.addCage(cage);
        return new ResponseEntity<>(
                cage,
                HttpStatus.CREATED);
    }

    // Get a Single cage
    @GetMapping("/cages/{id}")
    public ResponseEntity<Cage> getCageById(@PathVariable(value = "id") int cageId) {
        return new ResponseEntity<>(
                cageService.getCage(cageId),
                HttpStatus.OK);
    }

    // Update an Cage
    @PutMapping("/cages/{id}")
    public ResponseEntity<String> updateCage(@PathVariable(value = "id") int cageId,
                                             @Valid @RequestBody Cage cage) {
        cageService.updateCage(cage);
        return new ResponseEntity<>(
                "The Cage has been updated.",
                HttpStatus.OK);
    }

    // Delete an cage
    @DeleteMapping("/cages/{id}")
    public ResponseEntity<String> deleteCage(@PathVariable(value = "id") int cageId) {
        cageService.deleteCage(cageId);
        return new ResponseEntity<>(
                "The Cage has been deleted.",
                HttpStatus.OK);
    }

}
