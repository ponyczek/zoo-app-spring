package com.zoomanager.zoo.service;

import com.zoomanager.zoo.model.Animal;
import com.zoomanager.zoo.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> getAnimalsByCage(int cageId) {
        return animalRepository.findByCageId(cageId);
    }

    public Animal getAnimal(int id) {
        return animalRepository.getOne(id);
    }

    public void addAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public void updateAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    public void deleteAnimal(int id) {
        animalRepository.deleteById(id);
    }

}
