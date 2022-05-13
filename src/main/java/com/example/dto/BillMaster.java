package com.example.dto;

import com.example.dto.Customer;
import com.example.dto.Staff;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bill_master")
public class BillMaster {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    public Staff staff;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @Column(name = "total_amount")
    public Double totalAmount;

    @Column(name = "date")
    public Date date;

    @OneToMany(mappedBy="billMaster",cascade= {CascadeType.ALL},fetch = FetchType.LAZY)
    List<BillDetails> billDetailsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<BillDetails> getBillDetailsList() {
        return billDetailsList;
    }

    public void setBillDetailsList(List<BillDetails> billDetailsList) {
        this.billDetailsList = billDetailsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillMaster that = (BillMaster) o;
        return Objects.equals(id, that.id) && Objects.equals(staff, that.staff) && Objects.equals(customer, that.customer) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(date, that.date) && Objects.equals(billDetailsList, that.billDetailsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, staff, customer, totalAmount, date, billDetailsList);
    }
}
