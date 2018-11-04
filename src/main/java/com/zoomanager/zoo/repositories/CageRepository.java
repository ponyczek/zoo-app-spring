package com.zoomanager.zoo.repositories;

import com.zoomanager.zoo.model.Cage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CageRepository extends JpaRepository<Cage, Integer> {

}
