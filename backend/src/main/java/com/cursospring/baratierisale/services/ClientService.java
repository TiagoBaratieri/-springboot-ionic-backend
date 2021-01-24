package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.dto.ClientDTO;
import com.cursospring.baratierisale.entities.Client;
import com.cursospring.baratierisale.repositories.ClientRepository;
import com.cursospring.baratierisale.services.exceptions.DataIntegrityException;
import com.cursospring.baratierisale.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Client insert(Client obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Client update(Client obj) {
        Client newObj = find(obj.getId());
        updateData(newObj,obj);
        return repo.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("It is not possible to exclude a category that does not contain products");
        }
    }

    public List<Client> findAll(){
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }

    public Client fromDto(ClientDTO objDto){
       return new Client(objDto.getId(),objDto.getName(),objDto.getEmail(),null,null);

    }

    private void updateData(Client newObj,Client obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
