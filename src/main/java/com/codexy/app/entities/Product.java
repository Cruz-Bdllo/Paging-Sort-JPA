package com.codexy.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    /* ~ PROPERTIES
    ======================================= */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToOne
    @JoinColumn(name = "madein_id")
    private MadeIn madein;

    private double price;

    private int stock;


    /* ~ CONSTRUCTORS
    ======================================= */
    public Product() {
    }

    public Product(String name, Brand brand, MadeIn madein, double price, int stock) {
        this.name = name;
        this.brand = brand;
        this.madein = madein;
        this.price = price;
        this.stock = stock;
    }


    /* ~ METHODS
    ======================================= */
    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public MadeIn getMadein() {
        return madein;
    }

    public void setMadein(MadeIn madein) {
        this.madein = madein;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return name;
    }
} // end entity
