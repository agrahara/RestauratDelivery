package com.restaurant.online.controller;

import com.restaurant.online.entity.Product;
import com.restaurant.online.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController { //Menu Catalog Service

    @Autowired
    ProductService productService;

    @GetMapping("/items") //All menu items of Restaurant
    public List<Product> findAll()
    {
        return productService.findAll();
    }
}
