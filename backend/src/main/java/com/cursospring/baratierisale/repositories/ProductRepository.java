package com.cursospring.baratierisale.repositories;

import com.cursospring.baratierisale.entities.Category;
import com.cursospring.baratierisale.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    @Transactional(readOnly=true)
    Page<Product> findDistinctByNameContainingAndCategoriesIn(String name,List<Category> categories, Pageable pageRequest);


}
