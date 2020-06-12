package com.restaurant.online.service.impl;

import com.restaurant.online.entity.Product;
import com.restaurant.online.persistance.ProductData;
import com.restaurant.online.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductData productData;

    @Override
    public List<Product> findAll() {
        return productData.findAll();
    }

}
