package com.codexy.app.search;

/**
 * Clase que permite obtener de la URL la forma en que el cliente desea buscar elementos
 * mediante los campos brand, madeIn, greaterThan, lessThan.
 */
public class ProductSearchCriteria {

    // Buscar por marca
    private String brand;
    // Buscar por pais de origen
    private String madeIn;
    // Buscar registros con precio mayor a
    private Long greaterThan;
    // Buscar registros con precio menor a
    private Long lessThan;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public Long getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(long greaterThan) {
        this.greaterThan = greaterThan;
    }

    public Long getLessThan() {
        return lessThan;
    }

    public void setLessThan(long lessThan) {
        this.lessThan = lessThan;
    }
} // end class search criteria
