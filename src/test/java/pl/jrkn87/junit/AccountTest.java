package pl.jrkn87.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void newlyCreatedAccountShouldNotBeActive() {
        //given
        Account account = new Account();

        //when
        boolean isActive = account.isActive();

        //then
        assertFalse(isActive);
    }

    @Test
    void activatedAccountShouldHaveActiveFlagSet() {
        //given
        Account account = new Account();
        account.activation();

        //when
        boolean isActive = account.isActive();

        //then
        assertTrue(isActive);
    }

    @Test
    void newlyCreatedAccountShouldHaveNullAddress() {
        //given
        Account account = new Account();

        //when
        Address givenAddress = account.getAddress();

        //then
        assertNull(givenAddress);
    }

    @Test
    void addressShouldNotBeNullAfterBeingSet() {
        //given
        Account account = new Account();
        Address address = new Address("Marsa", "120");
        account.setAddress(address);

        //when
        Address givenAddress = account.getAddress();

        //then
        assertNotNull(givenAddress);
    }
}