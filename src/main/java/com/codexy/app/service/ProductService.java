package com.codexy.app.service;

import com.codexy.app.entities.Product;
import com.codexy.app.search.ProductPage;
import com.codexy.app.search.ProductSearchCriteria;
import org.springframework.data.domain.Page;

/**
 * Interfaz que define los métodos de las acciones que se podrán realizar a la BD.
 */
public interface ProductService {
    /**
     * Obtiene los registros de la entidad {@link Product} mediante un conjunto de criterios de búsqueda.
     * @param productPage Configuración del paginado.
     * @param productSearchCriteria Configuración de los criterio que se desean aplicar.
     * @return Paginación de los productos
     */
    Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria);

    /**
     * Obtiene el registro de un elemento dado su identificador único.
     * @param productId Identificador único usado para buscarlo en la BD,
     * @return La entidad encontrada.
     */
    Product getProduct(Long productId);
} // end service
