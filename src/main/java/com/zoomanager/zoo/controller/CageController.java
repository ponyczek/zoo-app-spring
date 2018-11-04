package com.zoomanager.zoo.controller;


import com.zoomanager.zoo.model.Cage;
import com.zoomanager.zoo.service.CageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CageController {

    @Autowired
    CageService cageService;

    // Get All Cages
    @GetMapping("/cages")
    public List<Cage> getAllCages() {
        return cageService.getAllCages();
    }

    // Create a new Cage
    @PostMapping("/cages")
    public void createCage(@Valid @RequestBody Cage cage) {
        cageService.addCage(cage);
    }

    // Get a Single cage
    @GetMapping("/cages/{id}")
    public Cage getCageById(@PathVariable(value = "id") int cageId) {
        return cageService.getCage(cageId);
    }

    // Update an Cage
    @PutMapping("/cages/{id}")
    public void updateCage(@PathVariable(value = "id") int cageId,
                           @Valid @RequestBody Cage cage) {
        cageService.updateCage(cageId, cage);
    }

    // Delete an cage
    @DeleteMapping("/cages/{id}")
    public void deleteCage(@PathVariable(value = "id") int cageId) {
        cageService.deleteCage(cageId);
    }
}
