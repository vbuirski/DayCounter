package vbuirski.com.au.model;

import java.util.HashMap;
import java.util.Map;
import vbuirski.com.au.commons.Constants;

public class Month {

  private static final Map<Integer, Integer> daysMap;

  static {
    daysMap = new HashMap<>();
    daysMap.put(1, 31);
    daysMap.put(2, -1);
    daysMap.put(3, 31);
    daysMap.put(4, 30);
    daysMap.put(5, 31);
    daysMap.put(6, 30);
    daysMap.put(7, 31);
    daysMap.put(8, 31);
    daysMap.put(9, 30);
    daysMap.put(10, 31);
    daysMap.put(11, 30);
    daysMap.put(12, 31);
  }

  private final int month;

  public Month(String month) {
    // Expected format "DD/MM/YYYY"
    this.month = Integer.parseInt(month.substring(3, 5));
  }

  public Month(int month) {
    this.month = month;
  }

  public int get() {
    return month;
  }

  public boolean isValidMonth(int month) {
    return month > 0 && month <= 12;
  }

  public int daysInMonth(Year year) {

    return (daysMap.get(month) < 0) ?
            (year.isLeapYear() ? Constants.DAYS_IN_FEB_FOR_LEAP_YEAR : Constants.DAYS_IN_FEB_NOT_LEAP_YEAR) :
            daysMap.get(month);
  }
}
