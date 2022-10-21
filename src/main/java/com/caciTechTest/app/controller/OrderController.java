package com.caciTechTest.app.controller;

import com.caciTechTest.app.dao.OrderDAO;
import com.caciTechTest.app.request.CreateOrderRequest;
import com.caciTechTest.app.request.UpdateOrderRequest;
import com.caciTechTest.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("")
    public OrderDAO create(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.create(request);
    }

    @PatchMapping("/{reference}")
    public OrderDAO update(@PathVariable String reference,@Valid @RequestBody UpdateOrderRequest request) {
        return orderService.update(reference, request);
    }

    @GetMapping("/{reference}")
    public OrderDAO view(@PathVariable String reference) {
        return orderService.get(reference);
    }

    @GetMapping("")
    public List<OrderDAO> list() {
        return orderService.list();
    }

    @PostMapping("/{reference}/fulfil")
    public OrderDAO fulfil(@PathVariable String reference) {
        return orderService.fulfil(reference);
    }

}
