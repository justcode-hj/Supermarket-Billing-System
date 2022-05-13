package com.example.dao;

import com.example.dto.Customer;
import com.example.dto.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    public String addProduct(Product product) {
        Session session = null;
        Transaction transaction = null;
        String message = "";
        try {
            session = sessionFactory.openSession();
            transaction =session.beginTransaction();
            session.save(product);
            transaction.commit();
            message = "Product added successfully";
        }
        catch(Exception e) {
            System.out.println("Exception in addProduct() - ProductDAO " + e.getMessage());
            message = "Product Not Added. Try again later. " ;
            transaction.rollback();
        }
        finally {
            session.close();
        }
        return message;
    }

    public String updateProduct(Product product) {
        Session session = null;
        Transaction transaction = null;
        String message = "";
        try {
            session = sessionFactory.openSession();
            transaction =session.beginTransaction();
            System.out.println("Product id to be updated: " + product.getId());
            Product product1 = (Product) session.get(Product.class, product.getId());
            product1.setPrice(product.getPrice());
            product1.setQuantity(product.getQuantity());
            session.saveOrUpdate(product1);
            transaction.commit();
            message = "Product updated successfully";
        }
        catch(Exception e) {
            System.out.println("Exception in updateProduct() - ProductDAO " + e.getMessage());
            message = "Product Not Updated. Try again later. " ;
            transaction.rollback();
        }
        finally {
            session.close();
        }
        return message;
    }

    public Product getProduct(long id) {
        Session session = null;
        Product product = null;
        try {
            session = sessionFactory.openSession();
            product = (Product) session.get(Product.class, id);
            if (product == null) {
                System.out.println("Product not found");
            }
        }
        catch(Exception e) {
            System.out.println("Exception in getProduct() - ProductDAO " + e.getMessage());
        }
        finally {
            session.close();
        }
        return product;
    }

    public List<Product> getAllProducts() {
        Session session = null;
        List<Product> products = null;
        try {
            session = sessionFactory.openSession();
            products = session.createCriteria(Product.class).list();
        }
        catch(Exception e) {
            System.out.println("Exception in getAllProducts() - CustomerDAO " + e.getMessage());
        }
        finally {
            session.close();
        }
        return products;
    }
}
