package com.example.order;

public class Main {
    public static void main(String[] args) {
        System.out.println("OrderProcessingManual running on Java " + System.getProperty("java.version"));
        OrderService orderService = new OrderService();
        Order order = new Order("O1001", 250.50);
        orderService.process(order);
    }
}
