package pl.jrkn87.junit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testAssertArrayEquals() {
        //given
        int [] ints = {1, 2, 3};
        int [] ints1 = {1, 2, 3};

        //then
        assertArrayEquals(ints, ints1);
    }

    @Test
    void testIfTwoMealListAreTheSame() {
        //given
        Meal meal = new Meal(19, "Pizza");
        Meal meal1 = new Meal(21, "Sandwich");

        //when
        List<Meal> list = Arrays.asList(meal, meal1);
        List<Meal> list1 = Arrays.asList(meal, meal1);

        //then
        assertThat(list, is(list1));
    }

    @Test
    void newlyOrderMealsShouldBeEmpty() {
        //given
        Order order = new Order();

        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {
        //given
        Order order = new Order();
        Meal meal = new Meal(19, "Pizza");

        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        //given
        Order order = new Order();
        Meal meal = new Meal(21, "Pizza");

        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingTheOrder() {
        //given
        Order order = new Order();
        Meal meal = new Meal(19, "Pizza");
        Meal meal1 = new Meal(21, "Sandwich");

        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal1);

        //then
        assertThat(order.getMeals(), contains(meal, meal1));
        assertThat(order.getMeals(), containsInAnyOrder(meal1, meal));
    }
}