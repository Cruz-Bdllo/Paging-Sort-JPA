package com.codexy.app.service;

import com.codexy.app.entities.Product;
import com.codexy.app.repository.ProductRepository;
import com.codexy.app.search.ProductPage;
import com.codexy.app.search.ProductSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ProductServiceImp implements ProductService{

    // PROPERTIES
    private final ProductRepository productRepository;

    // CONSTRUCTOR
    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // METHODS
    @Override
    @Transactional(readOnly = true)
    public Page<Product> getProducts(ProductPage productPage, ProductSearchCriteria searchCriteria) {
        Sort sort = Sort.by(productPage.getDirectionBy(), productPage.getSortBy());
        Pageable page = PageRequest.of(productPage.getPageNumber(), productPage.getPageSize(), sort);

        // Logic that filter results
        // if(Objects.nonNull(searchCriteria.getBrand()) && Objects.nonNull(searchCriteria.getMadein()))

        return productRepository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("The product with id "
                 + productId + " not exists"));
    } // end method
} // end class
