package com.caciTechTest.app.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateOrderRequest {

    @NotNull(message = "Field 'bricks' is mandatory")
    @Min(value = 1L, message = "Invalid amount of bricks to order")
    private Long bricks;

    public Long getBricks() {
        return bricks;
    }

    public void setBricks(Long bricks) {
        this.bricks = bricks;
    }
}
