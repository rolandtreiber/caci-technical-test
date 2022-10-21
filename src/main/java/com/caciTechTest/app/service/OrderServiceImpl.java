package com.caciTechTest.app.service;

import com.caciTechTest.app.dao.OrderDAO;
import com.caciTechTest.app.request.CreateOrderRequest;
import com.caciTechTest.app.request.UpdateOrderRequest;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDAO create(CreateOrderRequest request) {
        return null;
    }

    @Override
    public OrderDAO get(String reference) {
        return null;
    }

    @Override
    public List<OrderDAO> list() {
        return null;
    }

    @Override
    public OrderDAO update(String reference, UpdateOrderRequest request) {
        return null;
    }

    @Override
    public OrderDAO fulfil(String reference) {
        return null;
    }
}
