package com.restaurant.online.service.impl;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.entity.Order;
import com.restaurant.online.entity.Product;
import com.restaurant.online.enums.OrderStatus;
import com.restaurant.online.exception.NotFoundException;
import com.restaurant.online.persistance.OrderData;
import com.restaurant.online.service.api.OnlineOrderService;
import com.restaurant.online.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    OrderData orderData;

    @Autowired
    OnlineOrderService helperService;


    @Autowired
    public OrderServiceImpl(OrderData orderData)
    {
        this.orderData = orderData;
    }

    @Override
    public Order findById(Integer orderId) {
        return orderData.findById(orderId);
    }

    public void placeOrder(Order order) {
        Product product=helperService.findProductById(order.getItemId());
        if(product==null)
            throw new NotFoundException("Item Not Found with Id: "+order.getItemId());
        order.setOrderId(null);
        order.setStatus(OrderStatus.WAITING);
        orderData.save(order);
    }

    @Override
    public Order updateOrderStatus(Order order) {
        Order existingOrder=orderData.findById(order.getOrderId());
        if(existingOrder.getDeliveryExecutiveId()==null)
            throw new NotFoundException("Order is Currently is unassigned Status ");
        if(order.getStatus()==OrderStatus.DELIVERED && existingOrder.getStatus()!=OrderStatus.DELIVERED)
            helperService.orderDelivered(existingOrder);
        existingOrder.setStatus(order.getStatus());

        orderData.save(existingOrder);
        return existingOrder;
    }

    @Override
    public List<Order> findAllActiveOrder() {
        return orderData.findAllActiveOrder();
    }

    @Override
    public List<DeliveryExecutive> findAllActiveExecutives() {
        return helperService.findAllOnlineExecutives();
    }

    @Override
    public boolean deleteById(int orderId) {
        return orderData.deleteById(orderId);
    }
}
