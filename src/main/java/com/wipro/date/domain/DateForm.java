package com.wipro.date.domain;

import com.wipro.date.validation.ValidDateRange;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@ValidDateRange
public class DateForm {

    @NotNull(message = "startDate.empty")
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "endDate.empty")
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DateForm{" +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}