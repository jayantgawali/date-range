package com.wipro.date.controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class DaysCalculatorServiceTest {

    public static final int DAYS = 5;

    @Test
    public void testCalculation() {
        Calendar cal = Calendar.getInstance();
        Date startDate = cal.getTime();

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, DAYS);
        Date endDate = cal.getTime();

        DaysCalculatorService daysCalculatorService= new DaysCalculatorService();
        long days = daysCalculatorService.calculateDays(startDate, endDate);
        Assert.assertEquals(days, DAYS);
    }
}
