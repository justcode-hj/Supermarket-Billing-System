package com.example.controller;

import com.example.dto.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/products")
    public String createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@RequestBody Product product,@PathVariable long id) {
        product.setId(id);
        return productService.updateProduct(product);
    }


}
