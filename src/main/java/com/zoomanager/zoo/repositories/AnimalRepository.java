package com.zoomanager.zoo.repositories;

import com.zoomanager.zoo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Integer>{
    public List<Animal> findByCageId(int cageId);
}
