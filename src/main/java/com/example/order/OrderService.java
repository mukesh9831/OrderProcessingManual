package com.example.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple service that processes orders. This is intentionally simple and
 * tightly coupled for the example; consider introducing interfaces and
 * dependency injection for production code.
 */
public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    // Tight coupling: directly creating dependencies
    private final OrderRepository repo = new OrderRepository();
    private final PaymentService payment = new PaymentService();
    private final NotificationService notifier = new NotificationService();

    public void process(Order order) {
        repo.save(order);
        boolean paid = payment.charge(order);
        if (paid) {
            notifier.notifyUser(order);
        }
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return Collections.unmodifiableList(orders);
    }
}
