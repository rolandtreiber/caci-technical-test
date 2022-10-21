package com.caciTechTest.app.repository;

import com.caciTechTest.app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAll();
    Order findById(final UUID id);
    Order findByReference(final String reference);

}
