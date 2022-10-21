package com.caciTechTest.app.controller;

import com.caciTechTest.app.dao.OrderDAO;
import com.caciTechTest.app.request.CreateOrderRequest;
import com.caciTechTest.app.request.UpdateOrderRequest;
import com.caciTechTest.app.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderServiceImpl orderService;

    private OrderDAO orderDAO;
    final private String reference = "TESTREF";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orderDAO = new OrderDAO();
        orderDAO.setReference(reference);
        orderDAO.setBricks(16L);
        orderDAO.setFulfilled(false);

    }

    @Test
    void create() {
        CreateOrderRequest request = new CreateOrderRequest();
        when(orderService.create(any(CreateOrderRequest.class))).thenReturn(orderDAO);
        OrderDAO response = orderController.create(request);

        assertEquals(response.getReference(), orderDAO.getReference());
        assertEquals(response.getBricks(), orderDAO.getBricks());
    }

    @Test
    void update() {
        UpdateOrderRequest request = new UpdateOrderRequest();
        when(orderService.update(anyString(), any(UpdateOrderRequest.class))).thenReturn(orderDAO);
        OrderDAO response = orderController.update(reference, request);

        assertEquals(response.getReference(), orderDAO.getReference());
        assertEquals(response.getBricks(), orderDAO.getBricks());
    }

    @Test
    void view() {
        when(orderService.get(anyString())).thenReturn(orderDAO);
        OrderDAO response = orderController.view(reference);

        assertEquals(response.getReference(), orderDAO.getReference());
        assertEquals(response.getBricks(), orderDAO.getBricks());
    }

    @Test
    void list() {
        OrderDAO orderDAO1 = new OrderDAO();
        orderDAO1.setBricks(16L);
        orderDAO1.setReference("TESTREF01");

        OrderDAO orderDAO2 = new OrderDAO();
        orderDAO2.setBricks(23L);
        orderDAO2.setReference("TESTREF02");

        List<OrderDAO> orderDAOList = new ArrayList<>();
        orderDAOList.add(orderDAO1);
        orderDAOList.add(orderDAO2);

        when(orderService.list()).thenReturn(orderDAOList);
        List<OrderDAO> response = orderController.list();

        assertEquals(orderDAOList, response);
    }

    @Test
    void fulfil() {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.setBricks(16L);
        orderDAO.setReference("TESTREF01");
        orderDAO.setFulfilled(true);

        when(orderService.fulfil(anyString())).thenReturn(orderDAO);

        OrderDAO respone = orderController.fulfil("TESTREF01");

        assertEquals(orderDAO.getReference(), respone.getReference());
        assertEquals(orderDAO.getBricks(), respone.getBricks());
    }
}