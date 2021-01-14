package com.codexy.app.search;

// fields that filters
public class ProductSearchCriteria {

    private String productId;
    private String brand;
    private String madein;
    private double moreThan = 0;
    private double lessThan = 0;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadein() {
        return madein;
    }

    public void setMadein(String madein) {
        this.madein = madein;
    }

    public double getMoreThan() {
        return moreThan;
    }

    public void setMoreThan(double moreThan) {
        this.moreThan = moreThan;
    }

    public double getLessThan() {
        return lessThan;
    }

    public void setLessThan(double lessThan) {
        this.lessThan = lessThan;
    }
} // end class search criteria
