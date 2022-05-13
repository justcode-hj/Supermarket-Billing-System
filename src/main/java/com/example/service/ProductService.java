package com.example.service;

import com.example.dao.ProductDAO;
import com.example.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public String addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    public String updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public Product getProduct(long id) {
        return productDAO.getProduct(id);
    }
}
