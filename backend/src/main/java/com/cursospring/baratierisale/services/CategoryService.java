package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.dto.CategoryDTO;
import com.cursospring.baratierisale.entities.Category;
import com.cursospring.baratierisale.repositories.CategoryRepository;
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
public class CategoryService {

    @Autowired
    public CategoryRepository repo;

    public Category find(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));

    }

    public Category insert(Category obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Category update(Category obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("It is not possible to exclude a category that does not contain products");
        }
    }

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        return repo.findAll(pageRequest);
    }

    public Category fromDto(CategoryDTO objDto){
        return new Category(objDto.getId(),objDto.getName());

    }
}
