package pl.jrkn87.junit.account;

import java.util.Arrays;
import java.util.List;

public class AccountStub implements AccountRepository {

    @Override
    public List<Account> getAllAccounts() {
        Address address = new Address("Warszawska", "19/42");
        Address address2 = new Address("Poznańska", "21X");

        Account account = new Account();
        Account account2 = new Account(address);
        Account account3 = new Account(address2);

        return Arrays.asList(account, account2, account3);
    }
}
