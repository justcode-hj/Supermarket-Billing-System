package com.example.dao;

import com.example.dto.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CustomerDAO {

    @Autowired
    SessionFactory sessionFactory;

    public String addCustomer(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        String message = "";
        try {
            session = sessionFactory.openSession();
            transaction =session.beginTransaction();
            session.save(customer);
            transaction.commit();
            message = "Customer added successfully";
        }
        catch(Exception e) {
            System.out.println("Exception in addCustomer() - CustomerDAO " + e.getMessage());
            message = "Failed to Add customer. Try again later. " ;
            transaction.rollback();
        }
        finally {
            session.close();
        }
        return message;
    }

    public Customer getCustomer(long id) {
        Session session = null;
        Customer customer = null;
        try {
            session = sessionFactory.openSession();
            customer = (Customer)session.get(Customer.class, id);
            if (customer == null) {
                System.out.println("Customer not found");
            }
        }
        catch(Exception e) {
            System.out.println("Exception in getCustomer() - CustomerDAO " + e.getMessage());
        }
        finally {
            session.close();
        }
        return customer;
    }

    public List<Customer> getAllCustomers() {
        Session session = null;
        List<Customer> customers = null;
        try {
            session = sessionFactory.openSession();
            customers = session.createCriteria(Customer.class).list();
        }
        catch(Exception e) {
            System.out.println("Exception in getAllCustomers() - CustomerDAO " + e.getMessage());
        }
        finally {
            session.close();
        }
        return customers;
    }

    public String createBill(long customerid, long staffid, List<Long> productId,List<Long> productQuantity, Date date){
        Session session = null;
        Transaction transaction = null;
        String message = "";
        double TotalBill = 0;
        try {
            session = sessionFactory.openSession();
            transaction =session.beginTransaction();
            BillMaster billMaster = new BillMaster();
            List<BillDetails> billDetails = new ArrayList<>();
            billMaster.setCustomer((Customer) session.get(Customer.class, customerid));
            billMaster.setStaff((Staff) session.get(Staff.class, staffid));
            billMaster.setDate(date);
            for(int i = 0; i < productId.size(); i++){
                BillDetails billDetail = new BillDetails();
                Product product = (Product) session.get(Product.class, productId.get(i));
                billDetail.setProduct(product);
                billDetail.setBillMaster(billMaster);
                billDetail.setQuantity(productQuantity.get(i).intValue());
                TotalBill = TotalBill + (product.getPrice() * productQuantity.get(i));
                billDetails.add(billDetail);
            }
            billMaster.setTotalAmount(TotalBill);
            billMaster.setBillDetailsList(billDetails);
            session.save(billMaster);
            transaction.commit();
            message = "Bill added successfully";
        }
        catch(Exception e) {
            System.out.println("Exception in createBill() - CustomerDAO " + e.getMessage());
            message = "Failed to Create bill. Try again later. " ;
            transaction.rollback();
        }
        finally {
            session.close();
        }
        return message;
    }
}
