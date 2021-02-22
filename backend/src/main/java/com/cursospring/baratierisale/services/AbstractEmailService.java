package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.entities.Solicitation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class  AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfimationEmail(Solicitation obj){
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Solicitation obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Order confirmed! code: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }


}
