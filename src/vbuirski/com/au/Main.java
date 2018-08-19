package vbuirski.com.au;

import java.io.Console;
import vbuirski.com.au.commons.Constants;
import vbuirski.com.au.model.Date;
import vbuirski.com.au.model.Day;
import vbuirski.com.au.model.Month;
import vbuirski.com.au.model.Year;

public class Main {

  private Date startDate;
  private Date endDate;

  public Integer countDays(String start, String end) {

    validateInput(start, end);

    extractDates(start, end);

    return totalDaysBetweenDates();
  }

  private boolean validateInput(String start, String end) {
    return true;
  }

  private void extractDates(String start, String end) {

    this.startDate = new Date(new Day(start), new Month(start), new Year(start));
    this.endDate = new Date(new Day(end), new Month(end), new Year(end));
  }

  private Integer totalDaysBetweenDates() {

    if (startDate.getYear().get() == endDate.getYear().get() &&
            startDate.getMonth().get() == endDate.getMonth().get()) {

      return daysInSameMonth();
    }

    if (startDate.getYear().get() == endDate.getYear().get()) {
      return daysInSameYear();
    }

    return daysSpanningYears();
  }

  private Integer daysInSameMonth() {
    Day startDay = startDate.getDay();
    Day endDay = endDate.getDay();
    Integer days = endDay.get() - startDay.get();
    return days > 0 ? days - 1 : 0;
  }

  private Integer daysInSameYear() {
    return daysRemainingInFirstMonth() +
            daysSpanningFullMonthsInYear() +
            daysInPartialLastMonth();
  }

  private Integer daysSpanningFullMonthsInYear() {
    Integer days = 0;

    for (int i = startDate.getMonth().get() + 1; i<endDate.getMonth().get(); i++) {
      days += new Month(i).daysInMonth(startDate.getYear());
    }
    return days;
  }

  private int daysSpanningYears() {

    return daysRemainingInFirstMonth() +
            daysInFullMonthsInFirstYear() +
            daysInFullYears() +
            daysInFullMonthsInEndYear() +
            daysInPartialLastMonth();
  }

  private int daysRemainingInFirstMonth() {

    return startDate.getMonth().daysInMonth(startDate.getYear()) - startDate.getDay().get();
  }

  private int daysInFullMonthsInFirstYear() {
    int days = 0;

    for (int i = startDate.getMonth().get(); i < Constants.MONTHS_IN_YEAR; i++) {
      days = days + new Month(i).daysInMonth(startDate.getYear());
    }
    return days;
  }

  private int daysInFullYears() {
    int days = 0;
    for (int year=startDate.getYear().get()+1; year<endDate.getYear().get(); year++) {
      days += new Year(year).daysInYear(year);
    }
    return days;
  }

  private int daysInFullMonthsInEndYear()  {

    int days = 0;
    for (int month=1; month < endDate.getMonth().get(); month++) {
      days = days + new Month(month).daysInMonth(endDate.getYear());
    }
    return days;
  }

  private int daysInPartialLastMonth() {
    return endDate.getDay().get() - 1;
  }

  public static void main(String[] args) {

    Main main = new Main();

    Console c = System.console();
    if (c == null) {
      System.err.println("No console.");
      System.exit(1);
    }

    while (true) {
      String startDate = c.readLine("Enter Start Date <dd/mm/yyyy> | q to quit: ");

      if (startDate.equalsIgnoreCase("q")) {
        System.out.println("bye!");
        System.exit(1);
      }

      String endDate = c.readLine("Enter End Date <dd/mm/yyyy> | q to quit: ");

      if (endDate.equalsIgnoreCase("x")) {
        System.out.println("bye!");
        System.exit(1);
      }

      int days = main.countDays(startDate, endDate);
      System.out.println("Total Number of Days = " + days);
    }
  }
}
