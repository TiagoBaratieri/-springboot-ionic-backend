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

    @Autowired
    private SolicitationItemRepository solicitationItem;



    public static void main(String[] args) {
        SpringApplication.run(BaratierisaleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama mesa e banho");
        Category cat4 = new Category(null, "Eletrônicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoração");
        Category cat7 = new Category(null, "Perfumaria");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Inpressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Mesa de escritório", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "Tv true color", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampoo", 90.00);


        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2,p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

        categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));

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

        Payment pagto2 = new PaymentBoleto(null, PaymentStatus.PENDING, ped2, sdf.parse("20/10/2021 00:00"), null);
        ped2.setPayment(pagto2);

        cli1.getOrders().addAll(Arrays.asList(ped1, ped2));

        solicitationRepository.saveAll(Arrays.asList(ped1,ped2));
        paymentRepository.saveAll(Arrays.asList(pagto1,pagto2));

        SolicitationItem ip1 = new SolicitationItem(ped1, p1, 0.00, 1, 2000.00);
        SolicitationItem ip2 = new SolicitationItem(ped1, p3, 0.00, 2, 80.00);
        SolicitationItem ip3 = new SolicitationItem(ped2, p2, 100.00, 1, 800.00);

        ped1.getItems().addAll(Arrays.asList(ip1, ip2));
        ped2.getItems().addAll(Arrays.asList(ip3));

        p1.getItems().addAll(Arrays.asList(ip1));
        p2.getItems().addAll(Arrays.asList(ip3));
        p3.getItems().addAll(Arrays.asList(ip2));

        solicitationItem.saveAll(Arrays.asList(ip1, ip2, ip3));



    }
}

