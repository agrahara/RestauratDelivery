package com.restaurant.online.service.api;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.entity.Order;

import java.util.List;

public interface OrderService {

    public Order findById(Integer orderId);

    public void placeOrder(Order order);

    public Order updateOrderStatus(Order order);

    public List<Order> findAllActiveOrder();

    public List<DeliveryExecutive> findAllActiveExecutives();

    public boolean deleteById(int orderId);
}
