package com.iwcn.master.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.iwcn.master.entities.Product;

@Service
public class ProductDataBase {
	
	@Autowired
	private com.iwcn.master.repositories.ProductRepository productRepository;

    public void addProduct(Product producto) {
    	this.productRepository.save(producto);
    }
    public void editProduct(Product product) {
    	this.productRepository.save(product);
    }
    public void deleteProduct(Long index) {
    	this.productRepository.delete(index);
    }
    public Iterable<Product> getAll(){
    	return this.productRepository.findAll();
    }
    public Product getProduct(long index) {
    	return this.productRepository.findOne(index);
    }
}
