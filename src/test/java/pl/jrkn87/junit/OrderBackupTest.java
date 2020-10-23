package pl.jrkn87.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrderBackupTest {
    private static OrderBackup orderBackup;

    @BeforeAll
    static void setUp() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void logInit() throws IOException {
        orderBackup.getWriter().append(LocalDate.now() + " : ");
    }

    @Test
    void addingOrderToOrderBackupShouldBeAppendLogToFile() throws IOException {
        //given
        Meal meal = new Meal(7, "Fries");
        Order order = new Order();
        order.addMealToOrder(meal);

        //when
        orderBackup.backupOrder(order);

        //then
        System.err.println("Success order backup!");
    }

    @AfterAll
    static void closeFile() throws IOException {
        orderBackup.closeFile();
    }
}