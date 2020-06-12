package com.restaurant.online.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.restaurant.online.enums.OrderStatus;


public class Order {
    //Using View to get Control of fields exposed control.
    //This entity would be used by customers

    @JsonView(View.Public.class)
    Integer orderId;

    @JsonView(View.Internal.class)
    Integer  executiveId;

    @JsonView(View.Public.class)
    OrderStatus status;

    @JsonView(View.Public.class)
    Integer quantity;

    @JsonView(View.Public.class)
    Integer itemId;

    @JsonView(View.Internal.class)
    Long knowledgeDateTime;

    @JsonView(View.Internal.class)
    Integer etaOfOrder;

    public Order(Integer quantity, Integer itemId) {
        this.quantity = quantity;
        this.itemId = itemId;
        this.minutesLeftForDelivery=30; //Ideally Assuming 30 Mins
        etaOfOrder=minutesLeftForDelivery;
        this.knowledgeDateTime=System.currentTimeMillis();
    }

    public Integer getDeliveryExecutiveId() {
        return executiveId;
    }

    public void setDeliveryExecutiveId(Integer deliveryExecutiveId) {
        this.executiveId = deliveryExecutiveId;
    }

    public Integer getEtaOfOrder() {
        return etaOfOrder;
    }

    public void setEtaOfOrder(Integer etaOfOrder) {
        this.etaOfOrder = etaOfOrder;
    }


    public Integer getMinutesLeftForDelivery() {
        return minutesLeftForDelivery;
    }

    public void setMinutesLeftForDelivery(Integer minutesLeftForDelivery) {
        this.minutesLeftForDelivery = minutesLeftForDelivery;
    }

    public Long getKnowledgeDateTime() {
        return knowledgeDateTime;
    }



    @JsonView(View.Public.class)
    Integer minutesLeftForDelivery;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }


}
