package com.restaurant.online.service.api;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.entity.Order;
import com.restaurant.online.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OnlineOrderService {

    public Product findProductById(int itemId);

    public List<DeliveryExecutive> findAllOnlineExecutives();

    public DeliveryExecutive assignOrder(Order order);

    public DeliveryExecutive orderDelivered(Order inputOrder);
}
