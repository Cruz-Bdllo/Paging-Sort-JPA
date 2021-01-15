package com.codexy.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "madein")
public class MadeIn {

    /* ~ PROPERTIES
    ======================================= */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "madein_id")
    private Integer madeInId;
    private String name;


    /* ~ CONSTRUCTORS
    ======================================= */
    public MadeIn() {
    }

    public MadeIn(String name) {
        this.name = name;
    }


    /* ~ METHODS
    ======================================= */
    public Integer getMadeInId() {
        return madeInId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
} // end entity
