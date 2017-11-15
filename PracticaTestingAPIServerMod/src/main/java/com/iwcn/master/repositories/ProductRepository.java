package com.iwcn.master.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iwcn.master.entities.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {}