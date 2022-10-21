package com.caciTechTest.app.service;

import com.caciTechTest.app.dao.OrderDAO;
import com.caciTechTest.app.entity.Order;
import com.caciTechTest.app.exception.CannotUpdateFulfilledOrderException;
import com.caciTechTest.app.exception.InvalidOrderReferenceException;
import com.caciTechTest.app.exception.OrderAlreadyFulfilledException;
import com.caciTechTest.app.repository.OrderRepository;
import com.caciTechTest.app.request.CreateOrderRequest;
import com.caciTechTest.app.request.UpdateOrderRequest;
import com.caciTechTest.app.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Utils utils;

    @Override
    public OrderDAO create(CreateOrderRequest request) {
        Order order = new Order();
        order.setReference(utils.generateUniqueOrderReference());
        order.setBricks(request.getBricks());
        order.setFulfilled(false);
        orderRepository.save(order);

        return new OrderDAO(order);
    }

    @Override
    public OrderDAO get(String reference) {
        Order order = orderRepository.findByReference(reference);
        if (order == null) throw new InvalidOrderReferenceException();
        return new OrderDAO(order);
    }

    @Override
    public List<OrderDAO> list() {
        List<OrderDAO> daoList = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();

        orders.forEach(order -> {
            OrderDAO orderDAO = new OrderDAO(order);
            daoList.add(orderDAO);
        });
        return daoList;
    }

    @Override
    public OrderDAO update(String reference, UpdateOrderRequest request) {
        Order order = orderRepository.findByReference(reference);
        if (order == null) throw new InvalidOrderReferenceException();
        if (order.getFulfilled()) {
            throw new CannotUpdateFulfilledOrderException();
        }
        order.setBricks(request.getBricks());
        orderRepository.save(order);
        return new OrderDAO(order);
    }

    @Override
    public OrderDAO fulfil(String reference) {
        Order order = orderRepository.findByReference(reference);
        if (order == null) throw new InvalidOrderReferenceException();
        if (order.getFulfilled()) throw new OrderAlreadyFulfilledException();
        order.setFulfilled(true);
        orderRepository.save(order);
        return new OrderDAO(order);
    }
}
