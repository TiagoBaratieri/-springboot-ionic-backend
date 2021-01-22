package com.cursospring.baratierisale;

import com.cursospring.baratierisale.entities.Category;
import com.cursospring.baratierisale.entities.City;
import com.cursospring.baratierisale.entities.Product;
import com.cursospring.baratierisale.entities.State;
import com.cursospring.baratierisale.repositories.CategoryRepository;
import com.cursospring.baratierisale.repositories.CityRepository;
import com.cursospring.baratierisale.repositories.ProductRepository;
import com.cursospring.baratierisale.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BaratierisaleApplication  implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    StateRepository stateRepository;


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

        



    }
}

