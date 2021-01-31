package pl.jrkn87.junit.cart;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import pl.jrkn87.junit.order.Order;
import pl.jrkn87.junit.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Test
    void processCartShouldSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart processCart = cartService.processCart(cart);

        //then
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart);

        InOrder inOrder = Mockito.inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(processCart.getOrders(), hasSize(1));
        assertThat(processCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void processCartShouldNotSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart processCart = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);

        InOrder inOrder = Mockito.inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);

        assertThat(processCart.getOrders(), hasSize(1));
        assertThat(processCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTING));
    }
}