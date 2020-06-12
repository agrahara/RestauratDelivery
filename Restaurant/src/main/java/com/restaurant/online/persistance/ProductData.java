package com.restaurant.online.persistance;


import com.restaurant.online.entity.Product;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ProductData implements InitializingBean {

    private Map<Integer, Product> productMap;

    Integer itemIdKey;

    public ProductData()
    {
        productMap= new ConcurrentHashMap<>();
        itemIdKey=0;
    }

    private Integer getSequencingNmber()
    {
        itemIdKey+=1;
        return itemIdKey;
    }



    public Product findById(int itemId)
    {
        if(productMap.containsKey(itemId))
            return productMap.get(itemId);
        return null;
    }

    public List<Product> findAll()
    {
        return productMap.values().parallelStream().collect(Collectors.toList());
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        Product product=new Product(getSequencingNmber(),"Laddoo");
        productMap.put(product.getItemId(),product);
        product=new Product(getSequencingNmber(),"Noodles");
        productMap.put(product.getItemId(),product);
        product=new Product(getSequencingNmber(),"Manchurian");
        productMap.put(product.getItemId(),product);
        product=new Product(getSequencingNmber(),"Shahi Paneer");
        productMap.put(product.getItemId(),product); //4 Items
    }
}

