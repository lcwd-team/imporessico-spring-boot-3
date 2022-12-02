package com.lcwd.store.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.lcwd.store.dtos.CreateOrderRequest;
import com.lcwd.store.dtos.OrderDto;
import com.lcwd.store.entities.Cart;
import com.lcwd.store.entities.CartItem;
import com.lcwd.store.entities.Order;
import com.lcwd.store.entities.OrderItem;
import com.lcwd.store.entities.User;
import com.lcwd.store.excetions.BadRequestException;
import com.lcwd.store.excetions.ResourceNotFountException;
import com.lcwd.store.respository.CartRepository;
import com.lcwd.store.respository.OrderRepository;
import com.lcwd.store.respository.UserRepository;
import com.lcwd.store.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public OrderDto createOrder(CreateOrderRequest orderRequest, String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFountException("User not found !!"));

        int cartId = orderRequest.getCartId();
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFountException("Cart not found !!"));
        List<CartItem> cartItems = cart.getItems();
        if (cartItems.size() <= 0) {
            throw new BadRequestException("Invalid Number of Items in cart");
        }

        // creating order
        Order order = Order.builder()
                .billingName(orderRequest.getBillingName())
                .billingPhone(orderRequest.getBillingPhone())
                .billingAddress(orderRequest.getBillingAddress())
                .deliveredDate(orderRequest.getDeliveredDate())
                .orderedDate(orderRequest.getOrderedDate())
                .paymentStatus(orderRequest.getPaymentStatus())
                .orderStatus(orderRequest.getOrderStatus())
                .orderId(UUID.randomUUID().toString())
                .user(user)
                .build();

        AtomicReference<Integer> orderTotalAmount = new AtomicReference<>(0);
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem orderItem = OrderItem.builder()
                    .quantity(cartItem.getQuantity())
                    .product(cartItem.getProduct())
                    .totalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice())
                    .order(order)
                    .build();

            orderTotalAmount.set(orderTotalAmount.get() + orderItem.getTotalPrice());

            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);
        order.setTotalAmount(orderTotalAmount.get());
        Order savedOrder = orderRepository.save(order);

        // cart items blank

        cart.getItems().clear();

        cartRepository.save(cart);

        return mapper.map(savedOrder, OrderDto.class);

    }

    @Override
    public void deleteOrder(String orderId) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<OrderDto> getOrdersOfUser(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrderDto> getOrders() {
        // TODO Auto-generated method stub
        return null;
    }

}
