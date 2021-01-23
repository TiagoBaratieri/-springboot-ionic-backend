package com.cursospring.baratierisale;

import com.cursospring.baratierisale.entities.*;
import com.cursospring.baratierisale.entities.enumS.ClientType;
import com.cursospring.baratierisale.entities.enumS.PaymentStatus;
import com.cursospring.baratierisale.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class BaratierisaleApplication  implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SolicitationRepository solicitationRepository;

    @Autowired
    private PaymentRepository paymentRepository;



    public static void main(String[] args) {
        SpringApplication.run(BaratierisaleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        categoryRepository.saveAll(Arrays.asList(cat1,cat2));

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Inpressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        productRepository.saveAll(Arrays.asList(p1,p2,p3));

        State est1 = new State(null, "Minas gerais");
        State est2 = new State(null, "São Paulo");

        City c1 = new City(null, "Uberlãdia", est1);
        City c2 = new City(null, "São Paulo", est2);
        City c3 = new City(null, "Campinas", est2);

        est1.getCities().addAll(Arrays.asList(c1));
        est2.getCities().addAll(Arrays.asList(c2, c3));

        stateRepository.saveAll(Arrays.asList(est1,est2));
        cityRepository.saveAll(Arrays.asList(c1,c2,c3));

        Client cli1 = new Client(null, "Tiago Baratieri", "tiagobaratieri97@gmail.com", "062843366965",
                ClientType.PHYSICALPERSON);

        cli1.getTelephones().addAll(Arrays.asList("322152541", "2545256"));

        Address e1 = new Address(null, "Valdecir sordy", "565A", "casa",
                "Jardim Aaurora", "2325545", cli1, c1);
        Address e2 = new Address(null, "Rua Ouro Verde", "233", "São Silvestre", "casa", "85412214", cli1, c2);

        cli1.getAddresses().addAll(Arrays.asList(e1,e2));

        clientRepository.saveAll(Arrays.asList(cli1));
        addressRepository.saveAll(Arrays.asList(e1,e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Solicitation ped1 = new Solicitation(null,sdf.parse("10/01/2021 20:30"),cli1,e1);
        Solicitation ped2 = new Solicitation(null, sdf.parse("20/02/2020 19:35"), cli1, e2);

        Payment pagto1 = new CardPayment(null, PaymentStatus.PAID, ped1, 6);
        ped1.setPayment(pagto1);

        Payment pagto2 = new PaymentBoleto(null, PaymentStatus.PENDING, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPayment(pagto2);

        cli1.getOrders().addAll(Arrays.asList(ped1, ped2));

        solicitationRepository.saveAll(Arrays.asList(ped1,ped2));
        paymentRepository.saveAll(Arrays.asList(pagto1,pagto2));



    }
}

