package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.entities.PaymentBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void fillPaymentWithPayment(PaymentBoleto pagto, Date instantOrder){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instantOrder);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDueDate(cal.getTime());
    }
}
