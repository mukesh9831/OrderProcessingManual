package com.example.order;

public class NotificationService {
    public void notifyUser(Order order) {
        System.out.println("Notifying user about order " + order.id);
    }
}
