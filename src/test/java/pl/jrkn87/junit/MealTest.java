package pl.jrkn87.junit;

import com.sun.xml.internal.bind.v2.runtime.SwaRefAdapterMarker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
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
}