package com.restaurant.online;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.enums.DeliveryExecutiveStatus;
import com.restaurant.online.service.api.DeliveryService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DeliveryServiceTest {

    @Autowired
    DeliveryService deliveryService;


    DeliveryExecutive deliveryExecutive;


    @Test
    public void validateExecutiveSearchById()
    {
        deliveryExecutive=getDeliveryExecutive();
        Assert.assertEquals(DeliveryExecutiveStatus.ACTIVE,deliveryExecutive.getStatus());
        Assert.assertNull(deliveryExecutive.getOrder());
    }

    @Test
    public void validateAllExecutiveSearch()
    {
        Assert.assertEquals(8,deliveryService.findAll().size());
        Assert.assertEquals(8,deliveryService.findAllByStatus(DeliveryExecutiveStatus.ACTIVE).size());
        Assert.assertEquals(0,deliveryService.findAllByStatus(DeliveryExecutiveStatus.OFFLINE).size());
    }

    public DeliveryExecutive getDeliveryExecutive()
    {
        return deliveryService.findById(3);
    }

}
