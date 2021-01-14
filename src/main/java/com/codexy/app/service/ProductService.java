package com.codexy.app.service;

import com.codexy.app.entities.Product;
import com.codexy.app.search.ProductPage;
import com.codexy.app.search.ProductSearchCriteria;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getProducts(ProductPage productPage,
                              ProductSearchCriteria searchCriteria);
    Product getProduct(Long productId);
} // end service
