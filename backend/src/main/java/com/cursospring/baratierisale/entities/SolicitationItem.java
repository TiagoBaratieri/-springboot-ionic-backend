package com.cursospring.baratierisale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class SolicitationItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private SolicitationItemPK id = new SolicitationItemPK();

    private Double discount;
    private Integer amount;
    private Double price;

    public SolicitationItem(){

    }

    public SolicitationItem(Solicitation order,Product product, Double discount, Integer amount, Double price) {
        id.setSolicitation(order);
        id.setProduct(product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    public double getTotalSub(){
        return (price - discount) * amount;
    }

    @JsonIgnore
    public Solicitation getSolicitation(){
        return id.getSolicitation();
    }

    public void setSolicitation(Solicitation order){
        id.setSolicitation(order);
    }

    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    public SolicitationItemPK getId() {
        return id;
    }

    public void setId(SolicitationItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitationItem that = (SolicitationItem) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
