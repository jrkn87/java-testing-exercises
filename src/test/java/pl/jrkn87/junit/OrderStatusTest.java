package pl.jrkn87.junit;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {
    @ParameterizedTest
    @EnumSource(OrderStatus.class)
    void allOrderStatusShouldBeShorterThan15Chars(OrderStatus orderStatus) {
        MatcherAssert.assertThat(orderStatus.toString().length(), lessThan(15));
    }
}