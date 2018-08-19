package vbuirski.com.au.model;

import vbuirski.com.au.commons.Constants;

public class Year {

  private final int year;

  public Year(int year) {
   this.year = year;
  }

  public Year(String year) {
    // Expected format "DD/MM/YYYY"
    this.year = Integer.parseInt(year.substring(6));
  }

  public int get() {
    return this.year;
  }

  public boolean isValidYear() {
    if (this.year >= 1901 && this.year <= 2999)
      return true;
    return false;
  }

  public boolean isLeapYear() {
    if ((year%4 == 0 && year%100 != 0) || (year%400 == 0))
      return true;
    return false;
  }

  public int daysInYear(int year) {
    return isLeapYear() ? Constants.DAYS_IN_LEAP_YEAR
            : Constants.DAYS_IN_NOT_LEAP_YEAR;
  }
}
