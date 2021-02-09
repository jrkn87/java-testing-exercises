package pl.jrkn87.junit.cart;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import pl.jrkn87.junit.order.Order;
import pl.jrkn87.junit.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;
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

    @Test
    void processCartShouldNotSendToPrepareWithArgumentMatchers() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        //when
        cartService.processCart(cart);

        //then
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));
    }

    @Test
    void canHandlerCartShouldReturnMultipleValues() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        given(cartHandler.canHandleCart(cart)).willReturn(true, false, false, true);

        //when
        //then
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
    }

    @Test
    void processCartShouldSendToPrepareWithLambdas() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(argThat(c -> c.getOrders().size() > 0))).willReturn(true);

        //when
        Cart processCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(processCart.getOrders(), hasSize(1));
        assertThat(processCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void canHandleCartShouldThrowException() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> cartService.processCart(cart));
    }

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        ArgumentCaptor<Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class);
        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture()); //BDD
        assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));
    }

    @Test
    void shouldDoNothingWhenProcessCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        doNothing().when(cartHandler).sendToPrepare(cart);
        willDoNothing().given(cartHandler).sendToPrepare(cart); //BDD
        willDoNothing().willThrow(IllegalStateException.class).given(cartHandler).sendToPrepare(cart); //BDD

        //when
        cartService.processCart(cart);

        //then
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart); //BDD
    }

    @Test
    void shouldAnswerWhenProcessCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);
        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        // 1.
        doAnswer(invocationOnMock -> {
            Cart argumetCart = invocationOnMock.getArgument(0);
            argumetCart.clearCart();
            return true;
        }).when(cartHandler).canHandleCart(cart);
        // 2.
        when(cartHandler.canHandleCart(cart)).then(i -> {
            Cart argumetCart = i.getArgument(0);
            argumetCart.clearCart();
            return true;
        });
        // 3. BDD
        willAnswer(invocationOnMock -> {
            Cart argumetCart = invocationOnMock.getArgument(0);
            argumetCart.clearCart();
            return true;
        }).given(cartHandler).canHandleCart(cart);
        // 4. BDD
        given(cartHandler.canHandleCart(cart)).will(invocationOnMock -> {
            Cart argumetCart = invocationOnMock.getArgument(0);
            argumetCart.clearCart();
            return true;
        });

        //when
        cartService.processCart(cart);

        //then
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart); //BDD
    }
}