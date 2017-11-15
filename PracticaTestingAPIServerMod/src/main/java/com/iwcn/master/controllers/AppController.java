package com.iwcn.master.controllers;  


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iwcn.master.entities.Product;
import com.iwcn.master.services.ProductDataBase;

@RestController
public class AppController {

    @Autowired
    private ProductDataBase service;

    @RequestMapping(value = "/productList", method=RequestMethod.GET)
    public Iterable<Product> showProducts() {
        return service.getAll();
    }
    
    @RequestMapping(value = "/showProduct", method=RequestMethod.GET)
    public Product showProduct(@RequestParam long index) {
        return service.getProduct(index);
    }

    @RequestMapping(value = "/editProduct", method=RequestMethod.PUT)
    public void editProduct(@RequestBody Product product)
    {
    	this.service.editProduct(product);
    }

    @RequestMapping(value = "/deleteProduct", method=RequestMethod.DELETE)
    public void deleteProduct(@RequestParam long index) {
    	service.deleteProduct(index);
    }
    
    @RequestMapping(value = "/addProduct", method=RequestMethod.POST)
    public void addProduct(@RequestBody Product product) {
    	service.addProduct(product);
    }

}
