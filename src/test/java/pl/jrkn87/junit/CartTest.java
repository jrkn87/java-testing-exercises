package pl.jrkn87.junit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for Card")
class CartTest {

    @Disabled
    @DisplayName("Cart is able to process 1000 orders in 100 ms")
    @Test
    void simulateLargeOrder() {
        //given
        Cart cart = new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }

    @Test
    void cartShouldNotBeEmptyAfterAddOrderToCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();

        //when
        cart.addOrderToCart(order);

        //then
        assertThat(cart.getOrders(), anyOf(
                notNullValue(),
                is(not(empty())),
                not(emptyCollectionOf(Order.class)),
                hasSize(0)
        ));
        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                is(not(empty())),
                not(emptyCollectionOf(Order.class)),
                hasSize(1)
        ));
        assertAll("This is a group of assertions for cart",
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders().get(0).getMeals(), empty())
        );
    }
}