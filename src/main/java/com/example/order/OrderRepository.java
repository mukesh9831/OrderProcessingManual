package com.example.order;

/**
 * Very small repository stub that would persist orders.
 */
public class OrderRepository {

    public void save(Order order) {
        System.out.println("Persisting order " + order.getId());
    }
}