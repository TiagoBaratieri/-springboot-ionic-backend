package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.entities.Solicitation;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendOrderConfimationEmail(Solicitation obj);

    void sendEmail(SimpleMailMessage msg);
}
