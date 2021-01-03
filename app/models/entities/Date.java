package models.entities;

import java.io.Serializable;

public class Date implements Serializable,Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    @Override
    public boolean equals(Object obj) { //Check the day, month and the year is equal in both objects
        Date date = (Date) obj;
        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int compareTo(Date date) {
        if(this.getYear() == date.getYear() && this.getMonth() == date.getMonth()) { //If the year and the month are same
            return this.getDay() - date.getDay(); // Get the large number value of day
        } else if (this.getYear() == date.getYear()) { //If the years are same
            return this.getMonth() - date.getMonth(); // Get the large number value of the month
        } else {    // If the month dan the day is same
            return this.year - date.getYear(); // Get the large number value from the year
        }
    }
}

