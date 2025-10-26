package com.example.order;

public class OrderRepository {
    public void save(Order order) {
        System.out.println("Persisting order " + order.id);
    }
}