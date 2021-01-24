package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.dto.ClientDTO;
import com.cursospring.baratierisale.dto.ClientNewDTO;
import com.cursospring.baratierisale.entities.Address;
import com.cursospring.baratierisale.entities.City;
import com.cursospring.baratierisale.entities.Client;
import com.cursospring.baratierisale.entities.enumS.ClientType;
import com.cursospring.baratierisale.repositories.AddressRepository;
import com.cursospring.baratierisale.repositories.ClientRepository;
import com.cursospring.baratierisale.services.exceptions.DataIntegrityException;
import com.cursospring.baratierisale.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    @Autowired
    private AddressRepository addressRepository;

    public Client find(Integer id) {
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Client.class.getName()));

    }

    @Transactional
    public Client insert(Client obj) {
        obj.setId(null);
        repo.save(obj);
        addressRepository.saveAll(obj.getAddresses());
        return obj;
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

    public Client fromDto(ClientNewDTO objDto){
       Client cli = new Client(null,objDto.getName(),objDto.getEmail(),objDto.getCpfOuCnpj(),
               ClientType.toEnum(objDto.getType()));
        City city = new City(objDto.getCityId(), null, null);
        Address address = new Address(null,objDto.getPublicPlace(),objDto.getNumber(),objDto.getComplement(),
                objDto.getDistrict(),objDto.getCep(),cli,city);
        cli.getAddresses().add(address);
        cli.getTelephones().add(objDto.getTelephone1());
        if (objDto.getTelephone1()!=null) {
            cli.getTelephones().add(objDto.getTelephone2());
        }
        if (objDto.getTelephone3()!=null) {
            cli.getTelephones().add(objDto.getTelephone3());
        }
        return cli;

    }

    private void updateData(Client newObj,Client obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
