package com.example.order;

/**
 * Notification helper that would notify users about order status.
 */
public class NotificationService {

    public void notifyUser(Order order) {
        System.out.println("Notifying user about order " + order.getId());
    }
}
