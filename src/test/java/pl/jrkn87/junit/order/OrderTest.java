package pl.jrkn87.junit.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.jrkn87.junit.Meal;
import pl.jrkn87.junit.extension.BeforeAfterExtension;
import pl.jrkn87.junit.order.Order;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {
    private Order order;

    @BeforeEach
    void initOrder() {
        System.out.println("BeforeEach");
        order = new Order();
    }

    @AfterEach
    void clearOrder() {
        System.out.println("AfterEach");
        order.clearOrder();
    }

    @Test
    void testAssertArrayEquals() {
        //given
        int [] ints = {1, 2, 3};
        int [] ints1 = {1, 2, 3};

        //then
        assertArrayEquals(ints, ints1);
    }

    @Tag(value = "pizza")
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
        // @BeforeEach Order order = new Order();

        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Tag(value = "pizza")
    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {
        //given
        // @BeforeEach Order order = new Order();
        Meal meal = new Meal(19, "Pizza");

        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));
    }

    @Tag(value = "pizza")
    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        //given
        // @BeforeEach Order order = new Order();
        Meal meal = new Meal(21, "Pizza");

        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Tag(value = "pizza")
    @Test
    void mealsShouldBeInCorrectOrderAfterAddingTheOrder() {
        //given
        // @BeforeEach Order order = new Order();
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