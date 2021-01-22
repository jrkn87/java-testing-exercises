package pl.jrkn87.junit.account;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AccountServiceTest {
    /*
        stub ver.
    *
    */
    @Test
    public void shouldShowOnlyActiveAccounts() {
        //given
        AccountStub accountStub = new AccountStub();
        AccountService accountService = new AccountService(accountStub);

        //when
        List<Account> allActiveAccounts = accountService.getAllActiveAccounts();

        //then
        assertThat(allActiveAccounts.size(), equalTo(2));
    }
    /*
        mock ver.
    *
    */
    @Test
    public void shouldShowOnlyActiveAccounts2() {
        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(getData());

        //when
        List<Account> allActiveAccounts = accountService.getAllActiveAccounts();

        //then
        assertThat(allActiveAccounts, hasSize(2));
    }

    @Test
    public void shouldHasEmptyList() {
        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getAllAccounts()).willReturn(Collections.emptyList());

        //when
        List<Account> allActiveAccounts = accountService.getAllActiveAccounts();

        //then
        assertThat(allActiveAccounts, hasSize(0));
    }

    private List<Account> getData() {
        Address address = new Address("Warszawska", "19/42");
        Address address2 = new Address("Poznańska", "21X");

        Account account = new Account();
        Account account2 = new Account(address);
        Account account3 = new Account(address2);

        return Arrays.asList(account, account2, account3);
    }
}