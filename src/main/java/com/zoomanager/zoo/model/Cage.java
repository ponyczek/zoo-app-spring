package com.zoomanager.zoo.model;

import javax.persistence.*;


//@Table(name = "CAGE")
@Entity
public class Cage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "cageId")
    private Integer id;

    private String name;

    private String location;

    public Cage(int id, String name, String location) {
        super();
        this.id = id;
        this.name = name;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
