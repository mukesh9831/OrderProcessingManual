package com.example.order;

public class OrderService {

    // Tight coupling: directly creating dependencies
    private final OrderRepository repo = new OrderRepository();
    private final PaymentService payment = new PaymentService();
    private final NotificationService notifier = new NotificationService();

    public void process(Order order) {
        repo.save(order);
        boolean paid = payment.charge(order);
        if (paid) notifier.notifyUser(order);
    }
}
