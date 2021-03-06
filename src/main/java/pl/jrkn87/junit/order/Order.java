package pl.jrkn87.junit.order;

import pl.jrkn87.junit.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private OrderStatus orderStatus;
    private List<Meal> meals = new ArrayList<>(0);

    public void addMealToOrder(Meal meal) {
        this.meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal) {
        this.meals.remove(meal);
    }

    public List<Meal> getMeals() {
        return this.meals;
    }

    public void clearOrder() {
        this.meals.clear();
    }

    public void setOrderStatus(OrderStatus status) {
        this.orderStatus = status;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
