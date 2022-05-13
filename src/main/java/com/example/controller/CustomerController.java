package com.example.controller;

import com.example.dto.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable long id) {
        return customerService.getCustomer(id);
    }

    @PostMapping("/customer")
    public String createCustomer(Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customer/bill/{customerid}/{staffid}")
    public String createBill(@PathVariable long customerid,@PathVariable long staffid, @RequestParam List<Long> productId,@RequestParam List<Long> productQuantity, @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date date) {
        return customerService.createBill(customerid, staffid, productId,productQuantity, date);
    }
}
