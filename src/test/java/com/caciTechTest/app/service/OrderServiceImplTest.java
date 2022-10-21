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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    Utils utils;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        Order order = new Order();
        order.setBricks(16L);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(utils.generateUniqueOrderReference()).thenReturn("TESTREF01");

        CreateOrderRequest request = new CreateOrderRequest();
        request.setBricks(16L);
        OrderDAO orderDAO = orderService.create(request);

        assertNotNull(orderDAO);
        assertEquals(orderDAO.getReference(), "TESTREF01");
    }

    @Test
    void get() {
        Order order = new Order();
        order.setBricks(16L);
        order.setReference("TESTREF01");
        when(orderRepository.findByReference(anyString())).thenReturn(order);

        OrderDAO orderDAO = orderService.get("TESTREF01");

        assertEquals(orderDAO.getReference(), "TESTREF01");
        assertEquals(orderDAO.getBricks(), 16L);
    }

    @Test
    void list() {
        Order order1 = new Order();
        order1.setBricks(16L);
        order1.setReference("TESTREF01");

        Order order2 = new Order();
        order1.setBricks(23L);
        order1.setReference("TESTREF02");

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDAO> orderDAOlist = orderService.list();

        assertEquals(orderDAOlist.get(0).toString(), new OrderDAO(order1).toString());
        assertEquals(orderDAOlist.get(1).toString(), new OrderDAO(order2).toString());
    }

    @Test
    void update() {
        Order orderBefore = new Order();
        orderBefore.setBricks(16L);
        orderBefore.setReference("TESTREF01");
        orderBefore.setFulfilled(false);

        Order orderAfter = new Order();
        orderAfter.setBricks(23L);
        orderAfter.setReference("TESTREF01");
        orderBefore.setFulfilled(false);
        when(orderRepository.findByReference(anyString())).thenReturn(orderBefore);
        when(orderRepository.save(any(Order.class))).thenReturn(orderAfter);

        UpdateOrderRequest request = new UpdateOrderRequest();
        request.setBricks(23L);
        OrderDAO orderDAO = orderService.update("TESTREF01", request);

        assertEquals(orderDAO.getReference(), "TESTREF01");
        assertEquals(orderDAO.getBricks(), 23L);
    }

    @Test
    void fulfil() {
        Order orderBefore = new Order();
        orderBefore.setBricks(16L);
        orderBefore.setReference("TESTREF01");
        orderBefore.setFulfilled(false);

        Order orderAfter = new Order();
        orderAfter.setBricks(16L);
        orderAfter.setReference("TESTREF01");
        orderBefore.setFulfilled(false);
        when(orderRepository.findByReference(anyString())).thenReturn(orderBefore);
        when(orderRepository.save(any(Order.class))).thenReturn(orderAfter);

        OrderDAO orderDAO = orderService.fulfil("TESTREF01");

        assertEquals(orderDAO.getReference(), "TESTREF01");
        assertEquals(orderDAO.getFulfilled(), true);
    }

    @Test
    void fulfilAlreadyFulfilledThrowsException() {
        Order order = new Order();
        order.setBricks(16L);
        order.setReference("TESTREF01");
        order.setFulfilled(true);

        when(orderRepository.findByReference(anyString())).thenReturn(order);

        assertThrows(OrderAlreadyFulfilledException.class,
                () -> {
                    orderService.fulfil("TESTREF01");
                });
    }

    @Test
    void updatingFulfilledOrderThrowsException() {
        Order order = new Order();
        order.setBricks(16L);
        order.setReference("TESTREF01");
        order.setFulfilled(true);

        when(orderRepository.findByReference(anyString())).thenReturn(order);

        UpdateOrderRequest request = new UpdateOrderRequest();
        request.setBricks(23L);

        assertThrows(CannotUpdateFulfilledOrderException.class,
                () -> {
                    orderService.update("TESTREF01", request);
                });
    }

    @Test
    void invalidOrderIdThrowsExceptionWhenRetrieving() {

        when(orderRepository.findByReference(anyString())).thenReturn(null);

        assertThrows(InvalidOrderReferenceException.class,
                () -> {
                    orderService.get("TESTREF01");
                });
    }

    @Test
    void invalidOrderIdThrowsExceptionWhenUpdating() {

        when(orderRepository.findByReference(anyString())).thenReturn(null);

        UpdateOrderRequest request = new UpdateOrderRequest();
        request.setBricks(23L);

        assertThrows(InvalidOrderReferenceException.class,
                () -> {
                    orderService.update("TESTREF01", request);
                });
    }

}