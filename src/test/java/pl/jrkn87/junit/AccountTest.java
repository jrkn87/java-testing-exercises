package pl.jrkn87.junit;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {
    @Test
    void newlyCreatedAccountShouldNotBeActive() {
        //given
        Account account = new Account();

        //when
        boolean isActive = account.isActive();

        //then
        assertFalse(isActive);

        /* hamcrest */
        assertThat(isActive, equalTo(false));
        assertThat(isActive, is(false));
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

        /* hamcrest */
        assertThat(isActive, equalTo(true));
    }

    @Test
    void newlyCreatedAccountShouldHaveNullAddress() {
        //given
        Account account = new Account();

        //when
        Address givenAddress = account.getAddress();

        //then
        assertNull(givenAddress);

        /* hamcrest */
        assertThat(givenAddress, nullValue());

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

        /* hamcrest */
        assertThat(givenAddress, notNullValue());
    }

    @RepeatedTest(5)
    void newlyAccountWithSetAddressShouldBeActive() {
        //given
        Address address = new Address("Marsa", "21");

        //when
        Account account = new Account(address);

        //then
        assertAll(
                () -> assumingThat(account.getAddress() != null, () -> assertTrue(account.isActive())),
                () -> assertThat(account.getAddress(), notNullValue())
        );

    }
}