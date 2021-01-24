package com.cursospring.baratierisale.resources;

import com.cursospring.baratierisale.dto.ClientDTO;
import com.cursospring.baratierisale.entities.Client;
import com.cursospring.baratierisale.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO objDto, @PathVariable Integer id) {
        Client obj = services.fromDto(objDto);
        obj.setId(id);
        obj = services.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> list = services.findAll();
        List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value ="/page",method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> finpage(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name")String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC")String direction) {
        Page<Client> list = services.findPage(page,linesPerPage,orderBy,direction);
        Page<ClientDTO> listDto = list.map(obj -> new ClientDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
