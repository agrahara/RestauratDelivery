package com.restaurant.online;

import com.restaurant.online.entity.Order;
import com.restaurant.online.enums.OrderStatus;
import com.restaurant.online.service.api.OrderService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class OrderServiceTest extends TestCase {

	@Autowired
	OrderService orderService;

	Order order;

	@Test
	@org.junit.jupiter.api.Order(1)
	void placeOrderTest() {
		Order order=getOrder();
		orderService.placeOrder(order);
		Assert.assertEquals(1,order.getOrderId().intValue());
		Assert.assertEquals(OrderStatus.WAITING,order.getStatus());
		Assert.assertEquals(3,order.getQuantity().intValue());
		Assert.assertEquals(2,order.getItemId().intValue());
		Assert.assertEquals(30,order.getMinutesLeftForDelivery().intValue());
		Assert.assertEquals(orderService.findAllActiveOrder().size(),1);
	}

	@Test
	@org.junit.jupiter.api.Order(2)
	void findOrderStatusTest()
	{
		Order order=orderService.findById(1);
		Assert.assertEquals(OrderStatus.WAITING,order.getStatus());
		Assert.assertEquals(3,order.getQuantity().intValue());
		Assert.assertEquals(2,order.getItemId().intValue());
		Assert.assertEquals(30,order.getMinutesLeftForDelivery().intValue());
		Assert.assertEquals(true,orderService.deleteById(1));

	}

	@Override
	public void tearDown()
	{
		orderService.deleteById(order.getOrderId());
	}



	public Order getOrder()
	{
		return new Order(3,2);
	}



}
