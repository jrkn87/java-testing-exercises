package pl.jrkn87.junit.homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {
    @Test
    void newlyCoordinatesObjectShouldHaveXAndYBeetwen0And100() {
        //given
        Coordinates coordinates = new Coordinates(100, 0);

        //when
        //then
        assertThat(coordinates.getX(), greaterThanOrEqualTo(0));
        assertThat(coordinates.getX(), lessThanOrEqualTo(100));
        assertThat(coordinates.getY(), greaterThanOrEqualTo(0));
        assertThat(coordinates.getY(), lessThanOrEqualTo(100));

    }

    @Test
    void newlyCoordinatesObjectShouldThrowExceptionWithXOrYLessThan0() {
        //given
        Executable executable = () -> new Coordinates(-2, 0);
        Executable executable2 = () -> new Coordinates(-2, -50);
        Executable executable3 = () -> new Coordinates(5, -50);

        //when
        //then
        assertThrows(IllegalArgumentException.class, executable);
        assertThrows(IllegalArgumentException.class, executable2);
        assertThrows(IllegalArgumentException.class, executable3);
    }

    @Test
    void newlyCoordinatesObjectShouldThrowExceptionWithXOrYGreaterThan100() {
        //given
        Executable executable = () -> new Coordinates(101, 0);
        Executable executable2 = () -> new Coordinates(120, 50);
        Executable executable3 = () -> new Coordinates(5, 150);

        //when
        //then
        assertThrows(IllegalArgumentException.class, executable);
        assertThrows(IllegalArgumentException.class, executable2);
        assertThrows(IllegalArgumentException.class, executable3);
    }

    @Test
    void copyShouldReturnNewObject() {
        //given
        Coordinates coordinates = new Coordinates(10, 8);

        //when
        Coordinates copy = Coordinates.copy(coordinates, 0, 0);

        //then
        assertThat(copy, not(sameInstance(coordinates)));
        assertThat(copy, equalTo(coordinates));
    }

    @Test
    void copyShouldReturnAddCoordinates() {
        //given
        Coordinates coordinates = new Coordinates(0, 0);

        //when
        Coordinates copyCoordinates = Coordinates.copy(coordinates, 5, 10);
        Coordinates copyCoordinates2 = Coordinates.copy(copyCoordinates, 3, 1);

        //then
        assertThat(copyCoordinates.getX(), equalTo(5));
        assertThat(copyCoordinates2.getX(), equalTo(8));
        assertThat(copyCoordinates.getY(), equalTo(10));
        assertThat(copyCoordinates2.getY(), equalTo(11));
    }
}