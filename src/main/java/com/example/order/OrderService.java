package com.example.order;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.*;
import org.bson.Document;

/**
 * Simple service that processes orders. This is intentionally simple and
 * tightly coupled for the example; consider introducing interfaces and
 * dependency injection for production code.
 */
public class OrderService {

    private final MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    private final List<Order> orders = new ArrayList<>();

    // Tight coupling: directly creating dependencies
    private final OrderRepository repo = new OrderRepository();
    private final PaymentService payment = new PaymentService();
    private final NotificationService notifier = new NotificationService();

    public OrderService() {
        // Connect to local MongoDB server
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("OrderDB");
        collection = database.getCollection("orders");
    }

    /**
     * Save order to MongoDB
     * 
     * @param order the order to process
     */
    public void process(Order order) {
        Document doc = new Document("id", order.getId())
                .append("amount", order.getAmount());
        collection.insertOne(doc);
    }

    /**
     * Get all orders from MongoDB
     * 
     * @return list of all orders
     */
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        FindIterable<Document> docs = collection.find();
        for (Document doc : docs) {
            Order order = new Order(
                    doc.getString("id"),
                    doc.getDouble("amount")
            );
            orders.add(order);
        }
        return orders;
    }

    /**
     * Close connection (optional, called on app shutdown)
     */
    public void close() {
        mongoClient.close();
    }
}
