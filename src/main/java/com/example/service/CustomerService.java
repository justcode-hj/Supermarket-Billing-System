package com.example.service;

import com.example.dao.CustomerDAO;
import com.example.dto.BillDetails;
import com.example.dto.BillMaster;
import com.example.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    public String addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    public Customer getCustomer(long id) {
        return customerDAO.getCustomer(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public String createBill(long customerid, long staffid, List<Long> productId,List<Long> productQuantity, Date date){


        return customerDAO.createBill(customerid, staffid, productId,productQuantity ,date);
    }
}
