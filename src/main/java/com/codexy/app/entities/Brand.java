package com.codexy.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "brands")
public class Brand {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer brandId;
    private String name;


    /* ~ CONSTRUCTORS
    ======================================= */
    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }


    /* ~ METHODS
    ======================================= */
    public Integer getBrandId() {
        return brandId;
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
} // end method
