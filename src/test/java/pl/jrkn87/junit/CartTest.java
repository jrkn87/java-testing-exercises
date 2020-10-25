package pl.jrkn87.junit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cart test")
class CartTest {

    @Disabled
    @DisplayName("Simulate large order")
    @Test
    void simulateLargeOrder() {
        //given
        Cart cart = new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(100), cart::simulateLargeOrder);
    }
}