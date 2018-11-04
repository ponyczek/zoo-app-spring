package com.zoomanager.zoo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

//@Table(name = "ANIMAL")
@Entity
public class Animal {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "animalId", nullable = false)
    private Integer id;

    private String name;

    private int age;

    @ManyToOne
    private Cage cage;

    @Enumerated(EnumType.STRING)
    private AnimalClass animalClass;

    public Animal() {
    }

    public Animal(String name, int age, AnimalClass animalClass, int cageId) {
        this.name = name;
        this.age = age;
        this.cage = new Cage(cageId, true,"");
        this.animalClass = animalClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }

    public AnimalClass getAnimalClass() {
        return animalClass;
    }

    public void setAnimalClass(AnimalClass animalClass) {
        this.animalClass = animalClass;
    }
}
