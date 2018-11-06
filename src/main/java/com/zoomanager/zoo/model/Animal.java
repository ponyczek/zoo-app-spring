package com.zoomanager.zoo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Animal {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private int age;

    private String imgUrl;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cage cage;

    @Enumerated(EnumType.STRING)
    private AnimalClass animalClass;

    public Animal() {
    }

    public Animal(String name, int age, String imgUrl, AnimalClass animalClass, int cageId) {
        this.name = name;
        this.age = age;
        this.imgUrl = imgUrl;
        this.cage = new Cage(cageId, "", "");
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public AnimalClass getAnimalClass() {
        return animalClass;
    }

    public void setAnimalClass(AnimalClass animalClass) {
        this.animalClass = animalClass;
    }
}
