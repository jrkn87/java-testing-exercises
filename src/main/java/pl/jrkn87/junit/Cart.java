package pl.jrkn87.junit;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Order> orders = new ArrayList<>(0);

    void addOrderToCart(Order order) {
        this.orders.add(order);
    }

    void clearCart() {
        orders.clear();
    }

    void simulateLargeOrder() {
        for (int i = 0; i < 1_000; i++) {
            Meal meal = new Meal(i%10, "Meal no " + i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size: " + orders.size());
        clearCart();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
