package com.example.order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class OrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderService(); // tight coupling

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        double amount = Double.parseDouble(request.getParameter("amount"));

        Order order = new Order(id, amount);
        orderService.process(order);

        response.getWriter().write("Order processed: " + id);
    }
}