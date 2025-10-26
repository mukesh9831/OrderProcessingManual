package com.example.order;

/**
 * Simple immutable order value object.
 */
public class Order {

    private final String id;
    private final double amount;

    public Order(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }
}