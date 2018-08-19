package vbuirski.com.au.model;

public class Date {

  private Day day;
  private Month month;
  private Year year;

  public Date(Day day, Month month, Year year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public Day getDay() {
    return this.day;
  }

  public Month getMonth() {
    return this.month;
  }

  public Year getYear() {
    return this.year;
  }

}
