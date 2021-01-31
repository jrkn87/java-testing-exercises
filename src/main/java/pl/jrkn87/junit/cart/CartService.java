package pl.jrkn87.junit.cart;

import pl.jrkn87.junit.order.OrderStatus;

public class CartService {
    private CartHandler cartHandler;

    public CartService(CartHandler cartHandler) {
        this.cartHandler = cartHandler;
    }

    public Cart processCart(Cart cart) {
        if (cartHandler.canHandleCart(cart)) {
            cartHandler.sendToPrepare(cart);
            cart.getOrders().forEach(order -> order.setOrderStatus(OrderStatus.PREPARING));
        } else {
            cart.getOrders().forEach(order -> order.setOrderStatus(OrderStatus.REJECTING));
        }
        return cart;
    }
}
