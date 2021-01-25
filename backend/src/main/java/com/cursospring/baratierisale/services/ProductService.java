package com.cursospring.baratierisale.services;

import com.cursospring.baratierisale.entities.Category;
import com.cursospring.baratierisale.entities.Product;
import com.cursospring.baratierisale.entities.Solicitation;
import com.cursospring.baratierisale.repositories.CategoryRepository;
import com.cursospring.baratierisale.repositories.ProductRepository;
import com.cursospring.baratierisale.repositories.SolicitationRepository;
import com.cursospring.baratierisale.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository repo;

    @Autowired
    CategoryRepository categoryRepository;

    public Product find(Integer id) {
        Optional<Product> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Product.class.getName()));

    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        List<Category> categories = categoryRepository.findAllById(ids);
        return repo.findDistinctByNameContainingAndCategoriesIn(name,categories,pageRequest );

    }
}
