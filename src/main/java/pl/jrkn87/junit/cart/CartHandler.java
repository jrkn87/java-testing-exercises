package pl.jrkn87.junit.cart;

public interface CartHandler {
    boolean canHandleCart(Cart cart);
    void sendToPrepare(Cart cart);
}
