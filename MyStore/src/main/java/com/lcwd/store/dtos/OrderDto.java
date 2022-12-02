package com.lcwd.store.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private String orderId;

    // PENDING, DELLIVERED, DISPATCHED
    private String orderStatus;

    // NOTPAID, PAID
    private String paymentStatus;

    private int totalAmount;

    private String billingAddress;

    private Date orderedDate;

    private Date deliveredDate;

    private String billingPhone;

    private String billingName;

    // private UserDto user;

    private List<OrderItemDto> orderItems = new ArrayList<>();

}
