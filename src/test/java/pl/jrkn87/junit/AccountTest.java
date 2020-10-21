package pl.jrkn87.junit;

import org.junit.jupiter.api.Test;

<<<<<<< HEAD
=======
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
>>>>>>> 8ad6534... Add assertj lib.
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
<<<<<<< HEAD
=======
        /* hamcrest lib */
        //assertThat(isActive, equalTo(false));
        //assertThat(isActive, is(false));
        /* assertj lib */
        assertThat(isActive).isFalse();
>>>>>>> 8ad6534... Add assertj lib.
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
<<<<<<< HEAD
=======
        /* hamcrest */
        //assertThat(isActive, equalTo(true));
        /* assertj lib */
        assertThat(isActive).isTrue();
>>>>>>> 8ad6534... Add assertj lib.
    }

    @Test
    void newlyCreatedAccountShouldHaveNullAddress() {
        //given
        Account account = new Account();

        //when
        Address givenAddress = account.getAddress();

        //then
        assertNull(givenAddress);
<<<<<<< HEAD
=======
        /* hamcrest */
        //assertThat(givenAddress, nullValue());
        /* assertj lib */
        assertThat(givenAddress).isNull();
>>>>>>> 8ad6534... Add assertj lib.
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
<<<<<<< HEAD
=======
        /* hamcrest */
        //assertThat(givenAddress, notNullValue());
        /* assertj lib */
        assertThat(givenAddress).isNotNull();
>>>>>>> 8ad6534... Add assertj lib.
    }
}