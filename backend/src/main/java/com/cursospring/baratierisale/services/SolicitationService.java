package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.entities.PaymentBoleto;
import com.cursospring.baratierisale.entities.Solicitation;
import com.cursospring.baratierisale.entities.SolicitationItem;
import com.cursospring.baratierisale.entities.enumS.PaymentStatus;
import com.cursospring.baratierisale.repositories.ClientRepository;
import com.cursospring.baratierisale.repositories.PaymentRepository;
import com.cursospring.baratierisale.repositories.SolicitationItemRepository;
import com.cursospring.baratierisale.repositories.SolicitationRepository;
import com.cursospring.baratierisale.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class SolicitationService {

    @Autowired
    private SolicitationRepository repo;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private SolicitationItemRepository solicitationItemRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmailService emailService;


    public Solicitation find(Integer id) {
        Optional<Solicitation> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                " Object not found!Id: " + id + ", Type: " + Solicitation.class.getName()));

    }

    @Transactional
    public Solicitation insert(Solicitation obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setClient(clientService.find(obj.getClient().getId()));
        obj.getPayment().setState(PaymentStatus.PENDING);
        obj.getPayment().setOrder(obj);
        if (obj.getPayment() instanceof PaymentBoleto) {
            PaymentBoleto pagto = (PaymentBoleto) obj.getPayment();
            boletoService.fillPaymentWithPayment(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        paymentRepository.save(obj.getPayment());
        for (SolicitationItem ip : obj.getItems()) {
            ip.setDiscount(0.0);
            ip.setProduct(productService.find(ip.getProduct().getId()));
            ip.setPrice(ip.getProduct().getPrice());
            ip.setSolicitation(obj);
        }
        solicitationItemRepository.saveAll(obj.getItems());
        emailService.sendOrderConfimationEmail(obj);
        return obj;
    }
}
