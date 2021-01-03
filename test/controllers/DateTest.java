package controllers;
import models.entities.Date;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateTest {
    int test_day;
    int test_month;
    int test_year;
    Date test_date = new Date(test_day, test_month, test_year);

    @Test
    public void UnitTestDay() {assertEquals(test_day, test_date.getDay());}

    @Test
    public void UnitTestMonth() {assertEquals(test_month, test_date.getMonth());}

    @Test
    public void UnitTestYear() {assertEquals(test_year, test_date.getYear());}

}