package com.redhat.resource;

import java.util.List;
import java.util.UUID;

import com.redhat.model.Order;
import com.redhat.service.PlaceOrderService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    PlaceOrderService service;

    @POST
    public Response create(OrderRequest request) {
        Order order = new Order(UUID.randomUUID().toString(), request.customerId(), request.items());
        service.place(order);
        return Response.accepted().build();
    }

    public record OrderRequest(String customerId, List<String> items) {}
}

