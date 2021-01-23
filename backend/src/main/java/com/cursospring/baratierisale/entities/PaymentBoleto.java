package com.cursospring.baratierisale.entities;

import com.cursospring.baratierisale.entities.enumS.PaymentStatus;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentBoleto extends Payment {
    private static final long serialVersionUID = 1L;

    private Date PaymentDate;
    private Date dueDateueDate;


    public PaymentBoleto(){

    }

    public PaymentBoleto(Date paymentDate, Date dueDateueDate) {
        PaymentDate = paymentDate;
        this.dueDateueDate = dueDateueDate;
    }

    public PaymentBoleto(Integer id, PaymentStatus state, Solicitation order, Date paymentDate, Date dueDateueDate) {
        super(id, state, order);
        PaymentDate = paymentDate;
        this.dueDateueDate = dueDateueDate;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        PaymentDate = paymentDate;
    }

    public Date getDueDateueDate() {
        return dueDateueDate;
    }

    public void setDueDateueDate(Date dueDateueDate) {
        this.dueDateueDate = dueDateueDate;
    }
}
