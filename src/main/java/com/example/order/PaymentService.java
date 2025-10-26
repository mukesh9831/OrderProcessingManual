package com.example.order;

/**
 * Tiny payment processor stub.
 */
public class PaymentService {

    public boolean charge(Order order) {
        System.out.println("Charging amount: " + order.getAmount());
        return true;
    }
}