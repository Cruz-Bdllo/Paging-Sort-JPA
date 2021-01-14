package com.codexy.app.controller;

import com.codexy.app.entities.Product;
import com.codexy.app.search.ProductPage;
import com.codexy.app.search.ProductSearchCriteria;
import com.codexy.app.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    // PROPERTIES
    private final ProductService productService;

    // CONSTRUCTOR
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ENDPOINTS
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(ProductPage productPage,
                                                     ProductSearchCriteria searchCriteria) {
        return new ResponseEntity<>(productService.getProducts(productPage, searchCriteria),
                HttpStatus.OK);
    } // end handle

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.getProduct(productId), HttpStatus.OK);
    } // end handle

} // end controller
