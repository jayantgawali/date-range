package com.wipro.date.controller;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class DaysCalculatorService {

    public long calculateDays(Date startDate, Date endDate) {
        Calendar calendar = Calendar.getInstance();
        long diffInMillies = Math.abs(startDate.getTime() - endDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return diff;
    }

}
