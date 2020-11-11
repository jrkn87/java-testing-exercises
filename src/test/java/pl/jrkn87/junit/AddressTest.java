package pl.jrkn87.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AddressTest {
    @ParameterizedTest
    @CsvSource({"Warszawska, 18", "Lubelska, 21/87", "'Poznańska, Koszary', 87/21"})
    void givenAddressesShouldNotBeEmptyAndHaveProperName(String street, String number) {
        assertThat(street, notNullValue());
        assertThat(street, containsString("ska"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void addressesFromFileShouldNotBeEmptyAndHaveProperName(String street, String number) {
        assertThat(street, notNullValue());
        assertThat(street, containsString("ska"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
    }
}