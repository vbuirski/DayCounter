package vbuirski.com.au.model;

public class Day {

  private int day;

  public Day (String day) {
    // Expected format "DD/MM/YYYY"
    this.day = Integer.parseInt(day.substring(0, 2));
  }

  public int get() {
    return day;
  }
}
