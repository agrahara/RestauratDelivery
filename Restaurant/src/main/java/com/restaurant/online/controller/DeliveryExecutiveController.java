package com.restaurant.online.controller;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.entity.Order;
import com.restaurant.online.exception.NotFoundException;
import com.restaurant.online.service.api.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeliveryExecutiveController {

    @Autowired
    DeliveryService deliveryService;

    @GetMapping("/executives/{executiveId}")
    public DeliveryExecutive findById(@PathVariable int executiveId)
    {
        if(deliveryService.findById(executiveId)==null)
            throw new NotFoundException("Executive Not Found with orderId: "+executiveId);
        return deliveryService.findById(executiveId);
    }

    @PostMapping("/executives")
    public DeliveryExecutive assignOrder(@RequestBody Order order)
    {
        return deliveryService.assignOrder(order);
    }



}
