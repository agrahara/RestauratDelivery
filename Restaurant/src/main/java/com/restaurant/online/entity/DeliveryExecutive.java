package com.restaurant.online.entity;

import com.restaurant.online.enums.DeliveryExecutiveStatus;


public class DeliveryExecutive {

    Integer executiveId;

    String name;
//
    String contactNumber;
//
    DeliveryExecutiveStatus status;

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    Integer portNumber;

    Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public DeliveryExecutive(Integer executiveId, String name, String contactNumber, DeliveryExecutiveStatus status) {
        this.executiveId = executiveId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.status = status;
    }

    public Integer getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(Integer executiveId) {
        this.executiveId = executiveId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public DeliveryExecutiveStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryExecutiveStatus status) {
        this.status = status;
    }
}
