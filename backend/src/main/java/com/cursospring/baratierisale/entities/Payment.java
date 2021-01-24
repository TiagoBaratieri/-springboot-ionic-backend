package com.cursospring.baratierisale.entities;

import com.cursospring.baratierisale.entities.enumS.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy =InheritanceType.JOINED)
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer state;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "solicitation_id")
    @MapsId
    private Solicitation order;

    public Payment(){

    }

    public Payment(Integer id, PaymentStatus state, Solicitation order) {
        super();
        this.id = id;
        this.state = (state == null) ? null : state.getCod();
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public PaymentStatus getState() {
        return PaymentStatus.toEnum(state);
    }

    public void setState(PaymentStatus state) {
        this.state = state.getCod();
    }

    public Solicitation getOrder() {
        return order;
    }

    public void setOrder(Solicitation order) {
        this.order = order;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id.equals(payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
