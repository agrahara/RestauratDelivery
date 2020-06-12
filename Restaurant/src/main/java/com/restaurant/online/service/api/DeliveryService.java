package com.restaurant.online.service.api;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.entity.Order;
import com.restaurant.online.enums.DeliveryExecutiveStatus;

import java.util.List;

public interface DeliveryService {

    List<DeliveryExecutive> findAllOnlineExecutives();

    List<DeliveryExecutive> findAll();

    DeliveryExecutive assignOrder(Order order);

    DeliveryExecutive findById(int executiveId);

    List<DeliveryExecutive> findAllByStatus(DeliveryExecutiveStatus deliveryExecutiveStatus);
}
