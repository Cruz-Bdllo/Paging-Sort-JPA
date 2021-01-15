package com.codexy.app.service;

import com.codexy.app.entities.Product;
import com.codexy.app.repository.ProductRepository;
import com.codexy.app.repository.ProductRepositoryCriteria;
import com.codexy.app.search.ProductPage;
import com.codexy.app.search.ProductSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase de tipo {@link Service} que implementa los métodos definidos por su interfaz
 */
@Service
public class ProductServiceImp implements ProductService{

    /* ~ PROPERTIES
    ======================================= */
    // Contiene los métodos para consultas predefinidas por CrudRepository
    private final ProductRepository productRepository;
    // Repositorio que contiene métodos para realizar consultas personalizadas.
    private final ProductRepositoryCriteria productRepositoryCriteria;


    /* ~ CONSTRUCTOR
    ======================================= */
    @Autowired
    public ProductServiceImp(ProductRepository productRepository,
                             ProductRepositoryCriteria productRepositoryCriteria) {
        this.productRepository = productRepository;
        this.productRepositoryCriteria = productRepositoryCriteria;
    }


    /* ~ METHODS
    ======================================= */

    /**
     * Implementa el método para obtener todos los registros de la BD los cuales PUEDEN o NO contener
     * los criterios de búsqueda.
     * @param productPage Configuración del paginado.
     * @param productSearchCriteria Configuración de los criterio que se desean aplicar.
     * @return Los resultados de la búsqueda de manera paginada.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        return productRepositoryCriteria.getProductsWithCriteria(productPage, productSearchCriteria);
    }

    /**
     * Implementa el método para obtener un registro dado su identificador único.
     * @param productId Identificador único usado para buscarlo en la BD,
     * @return Resultado de la consulta.
     */
    @Override
    public Product getProduct(Long productId) {
        return null;
    }
} // end class
