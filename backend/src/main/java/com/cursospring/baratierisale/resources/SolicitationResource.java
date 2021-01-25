package com.cursospring.baratierisale.resources;

import com.cursospring.baratierisale.entities.Solicitation;
import com.cursospring.baratierisale.services.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/order")
public class SolicitationResource {

    @Autowired
    private SolicitationService services;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Solicitation> find(@PathVariable Integer id) {
        Solicitation obj = services.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Solicitation obj) {
        obj = services.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
