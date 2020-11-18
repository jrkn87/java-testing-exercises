package pl.jrkn87.junit.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.jrkn87.junit.order.OrderStatus;

import static org.hamcrest.Matchers.lessThan;

class OrderStatusTest {
    @ParameterizedTest
    @EnumSource(OrderStatus.class)
    void allOrderStatusShouldBeShorterThan15Chars(OrderStatus orderStatus) {
        MatcherAssert.assertThat(orderStatus.toString().length(), lessThan(15));
    }
}