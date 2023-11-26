package com.test.javaspringbootcandidatetest.dao;

import com.test.javaspringbootcandidatetest.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {}
