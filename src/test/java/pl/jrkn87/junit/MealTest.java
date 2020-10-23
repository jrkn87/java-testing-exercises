package pl.jrkn87.junit;

import org.junit.jupiter.api.Test;
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
}