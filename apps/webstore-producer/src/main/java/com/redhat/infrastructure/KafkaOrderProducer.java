package com.redhat.infrastructure;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.model.Order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class KafkaOrderProducer {
    
    @Channel("orders")
    Emitter<String> emitter;

    @Inject
    ObjectMapper objectMapper;
    
    public void sendOrder(Order order) {
        try {
            emitter.send(objectMapper.writeValueAsString(order));
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("Ocorreu erro ao serializar o pedido", exception);
        }
    }
}
