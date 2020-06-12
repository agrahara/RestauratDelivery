package com.restaurant.online.service.impl;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.entity.Order;
import com.restaurant.online.enums.DeliveryExecutiveStatus;
import com.restaurant.online.persistance.DeliveryExecutiveData;
import com.restaurant.online.service.api.DeliveryService;
import com.restaurant.online.service.api.OnlineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    DeliveryExecutiveData deliveryExecutiveData;

    @Autowired
    OnlineOrderService helperService;

    @Override
    public List<DeliveryExecutive> findAllOnlineExecutives() {
        return deliveryExecutiveData.findAllOnlineExecutives();
    }

    @Override
    public List<DeliveryExecutive> findAll() {
        return deliveryExecutiveData.findAll();
    }

    @Override
    public DeliveryExecutive assignOrder(Order order) {
        return helperService.assignOrder(order);
    }

    @Override
    public DeliveryExecutive findById(int executiveId) {

        DeliveryExecutive deliveryExecutive=deliveryExecutiveData.findById(executiveId);
        return deliveryExecutive;
    }

    @Override
    public List<DeliveryExecutive> findAllByStatus(DeliveryExecutiveStatus deliveryExecutiveStatus) {
        return deliveryExecutiveData.findAllByStatus(deliveryExecutiveStatus);
    }
}
