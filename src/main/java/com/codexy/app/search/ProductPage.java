package com.codexy.app.search;

import org.springframework.data.domain.Sort;


/**
 * Clase que permite configurar como se retornaran los resultados mediante paginación.
 */
public class ProductPage {

    /* ~ PROPERTIES
    ======================================= */
    /**
     * Número de la página a la cual se quiere acceder.
     */
    private int pageNumber = 0;
    /**
     * Número de elementos que mostrara cada página
     */
    private int pageSize = 3;

    /**
     * Ordenamiento de forma ASCENDENTE o DESCENDENTE.
     */
    private Sort.Direction directionBy = Sort.Direction.ASC;

    /**
     * Nombre del campo por el cual se ordenaran los registros.
     */
    private String sortBy = "productId";


    /* ~ METHODS
    ======================================= */
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
