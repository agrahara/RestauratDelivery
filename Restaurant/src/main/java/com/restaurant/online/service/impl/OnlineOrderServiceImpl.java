package com.restaurant.online.service.impl;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.entity.Order;
import com.restaurant.online.entity.Product;
import com.restaurant.online.enums.DeliveryExecutiveStatus;
import com.restaurant.online.enums.OrderStatus;
import com.restaurant.online.exception.BadInputParameterException;
import com.restaurant.online.exception.DeliveryExecutiveNotAvailableException;
import com.restaurant.online.exception.NotFoundException;
import com.restaurant.online.persistance.DeliveryExecutiveData;
import com.restaurant.online.persistance.OrderData;
import com.restaurant.online.persistance.ProductData;
import com.restaurant.online.service.api.OnlineOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Service
public class OnlineOrderServiceImpl implements OnlineOrderService {

    @Autowired
    DeliveryExecutiveData deliveryExecutiveData;

    @Autowired
    ProductData productData;

    @Autowired
    OrderData orderData;

    BlockingQueue<Integer> queue=new java.util.concurrent.PriorityBlockingQueue<>(10);

    public Product findProductById(int itemId)
    {
        return productData.findById(itemId);
    }

    @Override
    public List<DeliveryExecutive> findAllOnlineExecutives() {
        return deliveryExecutiveData.findAllOnlineExecutives();
    }

    @Override
    public DeliveryExecutive assignOrder(Order inputOrder) {
        if(inputOrder.getDeliveryExecutiveId()==null || inputOrder.getDeliveryExecutiveId()==null )
            throw new BadInputParameterException("Insufficient Input: Need executiveId and orderId");
        DeliveryExecutive executive=deliveryExecutiveData.findById(inputOrder.getDeliveryExecutiveId());
        Order order=orderData.findById(inputOrder.getOrderId());

        if(order ==null )
            throw new NotFoundException("OrderId Not found ");
        else if(order.getStatus()!=OrderStatus.WAITING) //If assigned order is being retried
            throw new NotFoundException("Order is already Assigned by Executive: "+order.getDeliveryExecutiveId());
        if(executive==null)
            throw new NotFoundException("DeliveryExecutive Not Found");
        if(deliveryExecutiveData.getStatusOfExecutive(executive.getExecutiveId())!= DeliveryExecutiveStatus.ACTIVE) // This is for to validate Executive is open to take order
            throw new DeliveryExecutiveNotAvailableException("Deliver Executive Currently Not Available. CurrentStatus: "+deliveryExecutiveData.getStatusOfExecutive(executive.getExecutiveId()));

        executive.setStatus(DeliveryExecutiveStatus.ASSIGNED);
        executive.setOrder(order);
        executive.setPortNumber(deliveryExecutiveData.getPortNumberFromPortPool());
        //Executive would get port number and this info would be shared by requested admin(restaurant)
        order.setStatus(OrderStatus.PREPARING);
        order.setDeliveryExecutiveId(executive.getExecutiveId());

        return executive;

    }

    @Override
    public DeliveryExecutive orderDelivered(Order inputOrder) {
        if(inputOrder.getDeliveryExecutiveId()==null || inputOrder.getDeliveryExecutiveId()==null )
            throw new BadInputParameterException("Insufficient Input: Need executiveId and orderId");
        DeliveryExecutive executive=deliveryExecutiveData.findById(inputOrder.getDeliveryExecutiveId());
        Order order=orderData.findById(inputOrder.getOrderId());

        if(order ==null )
            throw new NotFoundException("Order Not found ");
        else if(order.getDeliveryExecutiveId()==null)
            throw new NotFoundException("Order is Not assigned by any Executive: "+order.getDeliveryExecutiveId());
        if(executive==null)
            throw new NotFoundException("Delivery Executive Not Found ");

        executive.setStatus(DeliveryExecutiveStatus.ACTIVE);
        executive.setOrder(null);
        deliveryExecutiveData.keepPortAtPortPool(executive.getPortNumber());
        //Now Port number is free. Keeping it in Pool
        executive.setPortNumber(null);
        order.setStatus(OrderStatus.DELIVERED);
        orderData.moveOrdertoInActive(order.getOrderId());

        return executive;

    }



}
