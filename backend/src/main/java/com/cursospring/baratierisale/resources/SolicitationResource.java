package com.cursospring.baratierisale.resources;

import com.cursospring.baratierisale.entities.Solicitation;
import com.cursospring.baratierisale.services.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class SolicitationResource {

    @Autowired
    private SolicitationService services;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Solicitation obj = services.find(id);
        return ResponseEntity.ok().body(obj);
    }
}
