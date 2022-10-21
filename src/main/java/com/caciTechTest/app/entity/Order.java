package com.caciTechTest.app.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(	name = "orders")
public class Order {

    @Id
    @Type(type = "uuid-char")
    private final UUID id = UUID.randomUUID();
    private Long bricks;
    private String reference;
    private Boolean fulfilled;

    public Order() {
    }

    public UUID getId() {
        return id;
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
}
