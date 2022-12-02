package com.lcwd.store.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jpa_orders")
public class Order {

    @Id
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

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

}
