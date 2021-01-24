package com.cursospring.baratierisale.resources;

import com.cursospring.baratierisale.entities.Client;
import com.cursospring.baratierisale.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {

    @Autowired
    private ClientService services;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Client> find(@PathVariable Integer id) {
        Client obj = services.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
