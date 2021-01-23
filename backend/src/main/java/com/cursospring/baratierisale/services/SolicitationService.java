package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.entities.Solicitation;
import com.cursospring.baratierisale.repositories.SolicitationRepository;
import com.cursospring.baratierisale.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SolicitationService {

    @Autowired
    public SolicitationRepository repo;

    public Solicitation find(Integer id) {
        Optional<Solicitation> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Solicitation.class.getName()));

    }
}
