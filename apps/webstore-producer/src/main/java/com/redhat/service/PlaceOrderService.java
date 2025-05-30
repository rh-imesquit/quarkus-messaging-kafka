package com.redhat.service;

import com.redhat.infrastructure.KafkaOrderProducer;
import com.redhat.model.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlaceOrderService {
    
    @Inject
    KafkaOrderProducer producer;

    public void place(Order order) {
        producer.sendOrder(order);
    }
}
