package com.codexy.app.search;

import org.springframework.data.domain.Sort;

public class ProductPage {

    // PROPERTIES
    private int pageNumber = 0;
    private int pageSize = 3;
    private Sort.Direction directionBy = Sort.Direction.ASC;
    private String sortBy = "productId";


    // METHODS
    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Sort.Direction getDirectionBy() {
        return directionBy;
    }

    public void setDirectionBy(Sort.Direction directionBy) {
        this.directionBy = directionBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
} // end class
