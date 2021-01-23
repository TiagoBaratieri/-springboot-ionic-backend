package com.cursospring.baratierisale.entities;

import com.cursospring.baratierisale.entities.enumS.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentBoleto extends Payment {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date PaymentDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;


    public PaymentBoleto() {

    }

    public PaymentBoleto(Date paymentDate, Date dueDate) {
        PaymentDate = paymentDate;
        this.dueDate = dueDate;
    }

    public PaymentBoleto(Integer id, PaymentStatus state, Solicitation order, Date paymentDate, Date dueDate) {
        super(id, state, order);
        PaymentDate = paymentDate;
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        PaymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}

