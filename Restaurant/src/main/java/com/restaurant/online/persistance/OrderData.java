package com.restaurant.online.persistance;

import com.restaurant.online.entity.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class OrderData {

    private Map<Integer, Order> activeOrderMap; //Currently Active Orders

    private Map<Integer, Order> inActiveOrderMap; //Either Delivered or Canceled


    private Integer orderIdKey;

    public OrderData()
    {
        activeOrderMap= new ConcurrentHashMap();
        inActiveOrderMap=new ConcurrentHashMap();
        orderIdKey=0;
    }

    //Scheduled Job to update Left Delivery time of all active Orders
    @Scheduled(fixedDelay = 60000, initialDelay = 10000)
    private void  updateAllActiveOrderTime() {
        activeOrderMap.values().parallelStream()
                .filter(order -> order.getMinutesLeftForDelivery()!=0)
                .forEach( order -> {
                            Integer leftMinutes=order.getEtaOfOrder();
                            leftMinutes-=(int) (System.currentTimeMillis()-order.getKnowledgeDateTime())/(1000*60);
                            if(leftMinutes<=0)
                                leftMinutes=0; //Max leftMinutes can go till Zero
                            order.setMinutesLeftForDelivery(leftMinutes);
                        }
                );
    }

    private Integer getSequencingNumber()
    {
        orderIdKey+=1;
        return orderIdKey;
    }



    public boolean isActiveOrder(Integer orderId)
    {
        return activeOrderMap.containsKey(orderId);
    }

    public void moveOrdertoInActive(Integer orderId)
    {
        if(!activeOrderMap.containsKey(orderId))
            return;

        Order order=activeOrderMap.get(orderId);
        deleteById(orderId);
        inActiveOrderMap.put(order.getOrderId(),order);

    }
    public void save(Order order)
    {
        if(order.getOrderId()==null)
            order.setOrderId(getSequencingNumber());

        activeOrderMap.put(order.getOrderId(),order);
    }

    public Order findById(int orderId)
    {
        if(activeOrderMap.containsKey(orderId)) {
            Order order=activeOrderMap.get(orderId);
            return order;
        }

        if(inActiveOrderMap.containsKey(orderId)) {
            Order order=activeOrderMap.get(orderId);
            return order;
        }

        return null;
    }

    public boolean deleteById(int orderId)
    {
        if(activeOrderMap.containsKey(orderId)) {
            activeOrderMap.remove(orderId);
            return true;
        }

        if(inActiveOrderMap.containsKey(orderId)) {
            activeOrderMap.remove(orderId);
            return true;
        }

        return false;
    }

    public List<Order> findAllActiveOrder()
    {
        return activeOrderMap.values().parallelStream()
                .sorted((o1,o2) -> o1.getOrderId()-o2.getOrderId()).collect(Collectors.toList());
    }

}
