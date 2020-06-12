package com.restaurant.online.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.entity.Order;
import com.restaurant.online.entity.View;
import com.restaurant.online.exception.NotFoundException;
import com.restaurant.online.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController { //Restaurant Service Controller

    @Autowired
    OrderService orderService; //Injection

    @GetMapping("/orders/{orderId}")
    @JsonView(View.Public.class) //Don't want to expose executive id
    public Order findById(@PathVariable int orderId)
    {
        if(orderService.findById(orderId)==null)
            throw new NotFoundException("Order Not Found with orderId: "+orderId);
        return orderService.findById(orderId);
    }

    @PostMapping("/orders")
    @JsonView(View.Public.class)
    public Order placeOrder(@RequestBody Order order)
    {
        order.setOrderId(0);
        orderService.placeOrder(order);
        return order;
    }

    @GetMapping("/orders")
    public List<Order> findAllActiveOrder()
    {
        return orderService.findAllActiveOrder();
    }

    @PutMapping("/orders")
    public Order updateOrderStatus(@RequestBody Order order) //This would be used by Delivery Executive
    {
        if(orderService.findById(order.getOrderId())==null)
            throw new NotFoundException("Order Not Found with orderId: "+order.getOrderId());
        return orderService.updateOrderStatus(order);
    }

    @GetMapping("/orders/online") //All Active Executives
    public List<DeliveryExecutive> getAllActiveExecutives()
    {
        return orderService.findAllActiveExecutives();
    }

}
