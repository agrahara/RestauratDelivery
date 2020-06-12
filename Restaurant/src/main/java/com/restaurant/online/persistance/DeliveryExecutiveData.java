package com.restaurant.online.persistance;

import com.restaurant.online.entity.DeliveryExecutive;
import com.restaurant.online.enums.DeliveryExecutiveStatus;
import com.restaurant.online.exception.NotFoundException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

@Component
public class DeliveryExecutiveData implements InitializingBean {

    private Map<Integer, DeliveryExecutive> executiveMap;

    private Queue<Integer> portNumbers;

    private Integer executiveId;

    private Integer getSequencingNumber() //Unique Executive id
    {
        executiveId+=1;
        return executiveId;
    }

    public Integer getPortNumberFromPortPool()
    {
        if(portNumbers.size()==0)
            throw new NotFoundException("Port Number Not Available. Please retry");
        return portNumbers.poll();
    }

    public void keepPortAtPortPool(Integer portNumber)
    {
        if(portNumber!=null)
            portNumbers.offer(portNumber);
    }

    public DeliveryExecutiveData() {
        this.executiveMap = new ConcurrentHashMap();
        this.executiveId = 0;
        portNumbers=new PriorityBlockingQueue<>(30);
        initializedPortNumbers(portNumbers);
    }

    private void initializedPortNumbers(Queue<Integer> portNumbers) {
        for(int portNumber=1000;portNumber<1050;portNumber++)
            portNumbers.offer(portNumber);
    }

    public DeliveryExecutive findById(int executiveId)
    {
        if(executiveMap.containsKey(executiveId))
            return executiveMap.get(executiveId);

        return null;
    }

    public DeliveryExecutiveStatus getStatusOfExecutive(int executiveId)
    {
        return executiveMap.get(executiveId).getStatus();
    }

    public List<DeliveryExecutive> findAll()
    {
        return executiveMap.values().parallelStream().collect(Collectors.toList());
    }

    public List<DeliveryExecutive> findAllOnlineExecutives()
    {
        return executiveMap.values().parallelStream()
                .filter(executive -> !executive.getStatus().equals(DeliveryExecutiveStatus.OFFLINE))
            .collect(Collectors.toList());
    }

    public List<DeliveryExecutive> findAllByStatus(DeliveryExecutiveStatus status)
    {
        return executiveMap.values().parallelStream()
                .filter(executive -> executive.getStatus()==status)
                .collect(Collectors.toList());
    }

    public void save(DeliveryExecutive executive)
    {
        if(executive.getExecutiveId()==0)
            executive.setExecutiveId(getSequencingNumber());

        executiveMap.put(executive.getExecutiveId(),executive);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DeliveryExecutive deliveryExecutive
                =new DeliveryExecutive(getSequencingNumber(),"Rajesh","9888888888", DeliveryExecutiveStatus.ACTIVE);
        executiveMap.put(deliveryExecutive.getExecutiveId(),deliveryExecutive);
        deliveryExecutive
                =new DeliveryExecutive(getSequencingNumber(),"Vimal","9888888888", DeliveryExecutiveStatus.ACTIVE);
        executiveMap.put(deliveryExecutive.getExecutiveId(),deliveryExecutive);
        deliveryExecutive
                =new DeliveryExecutive(getSequencingNumber(),"Kishan","9888888888", DeliveryExecutiveStatus.ACTIVE);
        executiveMap.put(deliveryExecutive.getExecutiveId(),deliveryExecutive);
        deliveryExecutive
                =new DeliveryExecutive(getSequencingNumber(),"Preeti","9888888888", DeliveryExecutiveStatus.ACTIVE);
        executiveMap.put(deliveryExecutive.getExecutiveId(),deliveryExecutive);
        deliveryExecutive
                =new DeliveryExecutive(getSequencingNumber(),"Sunil","9888888888", DeliveryExecutiveStatus.ACTIVE);
        executiveMap.put(deliveryExecutive.getExecutiveId(),deliveryExecutive);
        deliveryExecutive
                =new DeliveryExecutive(getSequencingNumber(),"Mohan","9888888888", DeliveryExecutiveStatus.ACTIVE);
        executiveMap.put(deliveryExecutive.getExecutiveId(),deliveryExecutive);
        deliveryExecutive
                =new DeliveryExecutive(getSequencingNumber(),"Mintu","9888888888", DeliveryExecutiveStatus.ACTIVE);
        executiveMap.put(deliveryExecutive.getExecutiveId(),deliveryExecutive);
        deliveryExecutive
                =new DeliveryExecutive(getSequencingNumber(),"Chahal","9888888888", DeliveryExecutiveStatus.ACTIVE);
        executiveMap.put(deliveryExecutive.getExecutiveId(),deliveryExecutive);
    }
}
