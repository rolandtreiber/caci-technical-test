package com.caciTechTest.app.dao;

import com.caciTechTest.app.entity.Order;

public class OrderDAO {

    private Long bricks;
    private String reference;
    private Boolean fulfilled;

    public OrderDAO() {
    }

    public OrderDAO(Order order) {
        this.bricks = order.getBricks();
        this.reference = order.getReference();
        this.fulfilled = order.getFulfilled();
    }

    public OrderDAO(Long bricks, String reference, Boolean fulfilled) {
        this.bricks = bricks;
        this.reference = reference;
        this.fulfilled = fulfilled;
    }

    public Long getBricks() {
        return bricks;
    }

    public void setBricks(Long bricks) {
        this.bricks = bricks;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    @Override
    public String toString() {
        return "OrderDAO{" +
                "bricks=" + bricks +
                ", reference='" + reference + '\'' +
                ", fulfilled=" + fulfilled +
                '}';
    }
}
