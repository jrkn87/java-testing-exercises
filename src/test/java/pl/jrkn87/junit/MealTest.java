package pl.jrkn87.junit;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

<<<<<<< HEAD
=======
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
>>>>>>> 8ad6534... Add assertj lib.
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
<<<<<<< HEAD
=======
        /* hamcrest lib */
        //assertThat(discountedPrice, equalTo(28));
        /* assertj lib */
        assertThat(discountedPrice).isEqualTo(28);
>>>>>>> 8ad6534... Add assertj lib.
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {
        //given
        Meal meal1 = new Meal(19);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);
<<<<<<< HEAD
=======
        /* hamcrest */
        //assertThat(meal1, sameInstance(meal2));
        /* assertj lib */
        assertThat(meal1).isSameAs(meal2);
>>>>>>> 8ad6534... Add assertj lib.
    }

    @Test
    void referencesToDifferentObjectShouldNotBeEqual() {
        //given
        Meal meal1 = new Meal(19);
        Meal meal2 = new Meal(19);

        //then
        assertNotSame(meal1, meal2);
<<<<<<< HEAD
=======
        /* hamcrest */
        //assertThat(meal1, not(sameInstance(meal2)));
        /* assertj lib */
        assertThat(meal1).isNotSameAs(meal2);
>>>>>>> 8ad6534... Add assertj lib.
    }

    @Test
    void twoMealsShouldBeEqualsWhenPriceAndNameAreTheSame() {
        //given
        Meal meal1 = new Meal(21, "Pizza");
        Meal meal2 = new Meal(21, "Pizza");

        //then
        assertEquals(meal1, meal2);
        /* assertj lib */
        assertThat(meal1).isEqualTo(meal2);
    }
}