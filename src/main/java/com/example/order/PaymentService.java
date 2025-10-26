package src.main.java.com.example.order;

public class PaymentService {
    public boolean charge(Order order) {
        System.out.println("Charging amount: " + order.amount);
        return true;
    }
}