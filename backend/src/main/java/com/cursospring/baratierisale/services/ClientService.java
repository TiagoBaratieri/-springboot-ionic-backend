package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.entities.Client;
import com.cursospring.baratierisale.repositories.ClientRepository;
import com.cursospring.baratierisale.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    public ClientRepository repo;

    public Client find(Integer id) {
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));

    }
}
