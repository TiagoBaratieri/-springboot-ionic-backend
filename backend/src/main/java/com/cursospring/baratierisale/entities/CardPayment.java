package com.cursospring.baratierisale.entities;

import com.cursospring.baratierisale.entities.enumS.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;


@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment {
    private static final long serialVersionUID = 1L;

    private Integer numberInstallments;

    public CardPayment(){

    }

    public CardPayment(Integer id, PaymentStatus state, Solicitation order, Integer numberInstallments) {
        super(id, state,order);
        this.numberInstallments = numberInstallments;
    }

    public Integer getNumberInstallments() {
        return numberInstallments;
    }

    public void setNumberInstallments(Integer numberInstallments) {
        this.numberInstallments = numberInstallments;
    }
}
