package com.zoomanager.zoo.service;

import com.zoomanager.zoo.model.Animal;
import com.zoomanager.zoo.model.Cage;
import com.zoomanager.zoo.repositories.AnimalRepository;
import com.zoomanager.zoo.repositories.CageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CageService {
    @Autowired
    private CageRepository cageRepository;

    public List<Cage> getAllCages(){
        List<Cage> cages = new ArrayList<>();
        cageRepository.findAll().forEach(cages::add);
        return cages;
    }

    public Cage getCage(int id){
        return cageRepository.getOne(id);
    }

    public void addCage(Cage cage){
        cageRepository.save(cage);
    }

    public void updateCage(int id, Cage cage){
        cageRepository.save(cage);
    }

    public void deleteCage(int id){
        cageRepository.deleteById(id);
    }

}
