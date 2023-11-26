package com.test.javaspringbootcandidatetest.controller;

import com.test.javaspringbootcandidatetest.dao.ProductDao;
import com.test.javaspringbootcandidatetest.domain.Product;
import com.test.javaspringbootcandidatetest.domain.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProducts(@RequestBody ProductRequest request) {
        productDao.saveAll(request.getRecords());
    }

    @GetMapping("all")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productDao.findAll();
        return ResponseEntity.ok(products);
    }
}
