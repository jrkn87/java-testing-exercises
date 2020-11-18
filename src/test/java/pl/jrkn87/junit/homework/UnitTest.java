package pl.jrkn87.junit.homework;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    @Test
    void unitShouldNotMoveWithoutFuel() {
        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 200);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.move(5, 6));
    }

    @Test
    void movedUnitShouldBeCorrectCalculate() {
        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 200);

        //when
        Coordinates coordinatesAfterMove = unit.move(4, 6);

        //then
        assertThat(coordinatesAfterMove, equalTo(new Coordinates(4, 6)));
        assertThat(coordinatesAfterMove.getX(), equalTo(4));
        assertThat(coordinatesAfterMove.getY(), equalTo(6));
        assertThat(unit.getFuel(), equalTo(0));
    }

    @Test
    void movedUnitShouldReturnNewCoordinates() {
        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10,10);

        //when
        Coordinates move = unit.move(2, 2);

        //then
        assertThat(move, equalTo(new Coordinates(2, 2)));
    }

    @RepeatedTest(5)
    void tankUpShouldBeIncreaseFuelVariable() {
        //given
        Unit unit = new Unit(new Coordinates(0, 0), 10, 200);

        //when
        unit.move(4,6); //fuel = 0
        unit.tankUp();

        //then
        assertThat(unit.getFuel(), greaterThanOrEqualTo(0));
        assertThat(unit.getFuel(), lessThan(10));
    }

    @Test
    void fuelingShouldNotExceedMaxFuelLimit() {
        //given
        Unit unit = new Unit(new Coordinates(0,0),10,10);

        //when
        unit.tankUp();

        //then
        assertThat(unit.getFuel(), is(10));
    }

    @Test
    void cargoCanNotExceedMaxWeightLimit () {
        //given
        Unit unit = new Unit(new Coordinates(0,0), 10, 10);
        Cargo beerCrate = new Cargo("Beer crate", 12);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(beerCrate));
    }

    @Test
    void loadCargoShouldBeCorrectCalculateCurrentCargoWeight () {
        //given
        Unit unit = new Unit(new Coordinates(0,0), 10, 150);
        Cargo beer = new Cargo("Beer", 12);
        Cargo vodka = new Cargo("Vodka", 18);
        Cargo wine = new Cargo("Wine", 15);

        //when
        unit.loadCargo(beer);
        unit.loadCargo(vodka);
        unit.loadCargo(wine);

        //then
        assertThat(unit.getLoad(), notNullValue());
        assertThat(unit.getLoad(), not(equalTo(0)));
        assertThat(unit.getLoad(), is(45));
    }

    @Test
    void unLoadCargoShouldBeCorrectCalculateCurrentCargoWeight () {
        //given
        Unit unit = new Unit(new Coordinates(0,0), 10, 150);
        Cargo beer = new Cargo("Beer", 12);
        Cargo vodka = new Cargo("Vodka", 18);
        Cargo wine = new Cargo("Wine", 15);

        //when
        unit.loadCargo(beer);
        unit.loadCargo(vodka);
        unit.loadCargo(wine);
        unit.unloadCargo(beer);

        //then
        assertThat(unit.getLoad(), notNullValue());
        assertThat(unit.getLoad(), not(equalTo(0)));
        assertThat(unit.getLoad(), is(33));
    }

    @Test
    void unLoadAllCargoShouldBeCorrectCalculateCurrentCargoWeight () {
        //given
        Unit unit = new Unit(new Coordinates(0,0), 10, 150);
        Cargo beer = new Cargo("Beer", 12);
        Cargo vodka = new Cargo("Vodka", 18);
        Cargo wine = new Cargo("Wine", 15);

        //when
        unit.loadCargo(beer);
        unit.loadCargo(vodka);
        unit.loadCargo(wine);
        unit.unloadAllCargo();

        //then
        assertThat(unit.getLoad(), notNullValue());
        assertThat(unit.getLoad(), is(0));
    }
}