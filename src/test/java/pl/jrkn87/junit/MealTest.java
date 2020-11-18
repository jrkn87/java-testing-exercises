package pl.jrkn87.junit;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.jrkn87.junit.extension.IAExceptionHandlerExtension;
import pl.jrkn87.junit.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(37);

        //when
        int discountedPrice = meal.getDiscountedPrice(9);

        //then
        assertEquals(28, discountedPrice);

        /* hamcrest */
        assertThat(discountedPrice, equalTo(28));
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {
        //given
        Meal meal1 = new Meal(19);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);

        /* hamcrest */
        assertThat(meal1, sameInstance(meal2));
    }

    @Test
    void referencesToDifferentObjectShouldNotBeEqual() {
        //given
        Meal meal1 = new Meal(19);
        Meal meal2 = new Meal(19);

        //then
        assertNotSame(meal1, meal2);

        /* hamcrest */
        assertThat(meal1, not(sameInstance(meal2)));
    }

    @Test
    void twoMealsShouldBeEqualsWhenPriceAndNameAreTheSame() {
        //given
        Meal meal1 = new Meal(21, "Pizza");
        Meal meal2 = new Meal(21, "Pizza");

        //then
        assertEquals(meal1, meal2);
        /* harmcrest */
        assertThat(meal1, equalTo(meal2));
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {
        //given
        Meal meal = new Meal(39, "Soup");

        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(40));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 15, 10, 19})
    void priceShouldBeLessThan20(int price) {
        assertThat(price, lessThan(20));
    }

    @ExtendWith(IAExceptionHandlerExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 8})
    void priceShouldBeLessThan10(int price) {
        if (price > 5)
            throw new IllegalArgumentException();
        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void nameAndPriceShouldBeCorrectValue(String name, int price) {
        assertThat(name, notNullValue());
        assertThat(name, stringContainsInOrder("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("stringArguments")
    void cakeNamesShouldBeContainCake(String name) {
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    private static Stream<String> stringArguments() {
        List<String> cakes = Arrays.asList("Chocolate cake", "Pie cake", "Cup cake");
        return cakes.stream();
    }

    @Tag(value = "pizza")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {
        Order order = new Order();
        order.addMealToOrder(new Meal(10, 2, "Hamburger"));
        order.addMealToOrder(new Meal(6, 5, "Fries"));
        order.addMealToOrder(new Meal(20, 2, "Pizza"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>(0);

        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(1).getPrice();
            int quantity = order.getMeals().get(1).getQuantity();

            String nameTest = "Dynamic test no : " + i;
            Executable executable = () -> assertThat(calculatePrice(price, quantity), lessThan(66));
            DynamicTest dynamicTest = DynamicTest.dynamicTest(nameTest, executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;
    }

    private int calculatePrice(int price, int quantity) {
        return price + quantity;
    }
}