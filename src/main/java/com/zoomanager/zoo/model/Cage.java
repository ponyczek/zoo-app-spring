package com.zoomanager.zoo.model;

import javax.persistence.*;


//@Table(name = "CAGE")
@Entity
public class Cage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cageId")
    private Integer id;

    private boolean isOccupied;

    private String location;

    public Cage(int id, boolean isOccupied, String location) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.location = location;
    }

    public Cage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
