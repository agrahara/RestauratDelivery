package com.restaurant.online;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.entity.Order;
import com.restaurant.online.enums.DeliveryExecutiveStatus;
import com.restaurant.online.enums.OrderStatus;
import com.restaurant.online.service.api.DeliveryService;
import com.restaurant.online.service.api.OnlineOrderService;
import com.restaurant.online.service.api.OrderService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineOrderServiceTest extends TestCase {

    @Autowired
    DeliveryService deliveryService;


    @Autowired
    OrderService orderService;

    @Autowired
    OnlineOrderService helperService;




    Order order;

    DeliveryExecutive deliveryExecutive;


    @Test
    public void assignOrderToExecutiveTest()
    {
        Order order=getNewOrder();
        deliveryExecutive=getDeliveryExecutive();
        orderService.placeOrder(order);
        deliveryExecutive=getDeliveryExecutive();

        Assert.assertEquals(OrderStatus.WAITING,order.getStatus());
        Assert.assertNull(deliveryExecutive.getOrder());
        Assert.assertNull(order.getDeliveryExecutiveId());
        Assert.assertEquals(deliveryExecutive.getStatus(), DeliveryExecutiveStatus.ACTIVE);
        Assert.assertNull(deliveryExecutive.getPortNumber());

        helperService.assignOrder(getAssigneeOrderReuqest(order.getOrderId(),deliveryExecutive.getExecutiveId()));

        Assert.assertEquals(OrderStatus.PREPARING,order.getStatus());
        Assert.assertEquals(deliveryExecutive.getOrder(),order);
        Assert.assertEquals(order.getDeliveryExecutiveId(),deliveryExecutive.getExecutiveId());
        Assert.assertEquals(deliveryExecutive.getStatus(), DeliveryExecutiveStatus.ASSIGNED);
        Assert.assertNotNull(deliveryExecutive.getPortNumber());
    }

    @Override
    public void tearDown()
    {
        order.setStatus(OrderStatus.DELIVERED);
        helperService.orderDelivered(order);
        orderService.deleteById(order.getOrderId());
    }


    public DeliveryExecutive getDeliveryExecutive()
    {
        return deliveryService.findById(3);
    }

    public Order getNewOrder()
    {
        return new Order(3,2);
    }

    public Order getAssigneeOrderReuqest(int orderId,int executiveId)
    {
        Order order=new Order(1,1);
        order.setOrderId(orderId);
        order.setDeliveryExecutiveId(executiveId);
        return order;
    }






}
