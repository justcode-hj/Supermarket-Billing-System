package com.example.dto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bill_details")
public class BillDetails {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_master_id")
    public BillMaster billMaster;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    public Product product;

    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BillMaster getBillMaster() {
        return billMaster;
    }

    public void setBillMaster(BillMaster billMaster) {
        this.billMaster = billMaster;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillDetails that = (BillDetails) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(billMaster, that.billMaster) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billMaster, product, quantity);
    }
}
