package com.caciTechTest.app.service;

import com.caciTechTest.app.dao.OrderDAO;
import com.caciTechTest.app.request.CreateOrderRequest;
import com.caciTechTest.app.request.UpdateOrderRequest;

import java.util.List;

public interface OrderService {

    OrderDAO create(CreateOrderRequest request);
    OrderDAO get(String reference);
    List<OrderDAO> list();
    OrderDAO update(String reference, UpdateOrderRequest request);
    OrderDAO fulfil(String reference);

}
