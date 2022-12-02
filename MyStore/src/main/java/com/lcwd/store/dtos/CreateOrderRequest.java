package com.lcwd.store.dtos;

import java.util.Date;

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
public class CreateOrderRequest {

    private int cartId;
    private String orderStatus = "PENDING";
    private String billingAddress;
    private Date orderedDate = new Date();
    private Date deliveredDate = null;
    private String billingPhone;
    private String billingName;
    private String paymentStatus = "UNPAID";

}
