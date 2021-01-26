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


    public static void main(String[] args) {
        SpringApplication.run(BaratierisaleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {



    }
}

