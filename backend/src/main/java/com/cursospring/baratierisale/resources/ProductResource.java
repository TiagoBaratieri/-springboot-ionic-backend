package com.cursospring.baratierisale.resources;

import com.cursospring.baratierisale.dto.ProductDTO;
import com.cursospring.baratierisale.entities.Product;
import com.cursospring.baratierisale.entities.Solicitation;
import com.cursospring.baratierisale.resources.util.URL;
import com.cursospring.baratierisale.services.ProductService;
import com.cursospring.baratierisale.services.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {

    @Autowired
    private ProductService services;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id) {
        Product obj = services.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> finpage(
            @RequestParam(value = "name",defaultValue = "") String name,
            @RequestParam(value = "categories",defaultValue = "") String categories,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage",defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderBy",defaultValue = "name")String orderBy,
            @RequestParam(value = "direction",defaultValue = "ASC")String direction) {
        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = services.search(nameDecoded,ids,page,linesPerPage,orderBy,direction);
        Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}
