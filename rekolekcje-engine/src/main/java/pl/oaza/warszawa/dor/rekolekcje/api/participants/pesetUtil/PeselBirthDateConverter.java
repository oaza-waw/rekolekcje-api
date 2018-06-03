package pl.oaza.warszawa.dor.rekolekcje.api.participants.pesetUtil;

import java.util.*;

public class PeselBirthDateConverter {

  public static String convertPeselToBirthDate(String pesel) {

    List<Integer> peselAsIntArray = convertPeselToIntList(pesel);

    int year = getBirthYear(peselAsIntArray);
    int month = getBirthMonth(peselAsIntArray);
    int day = getBirthDay(peselAsIntArray);

    return buildBirthDate(day, month, year);
  }

  private static List<Integer> convertPeselToIntList(String pesel) {
    List<Integer> digits = new ArrayList<>();
    for (int i = 0; i < pesel.length(); i++) {
      int digit = Character.getNumericValue(pesel.charAt(i));
      digits.add(digit);
    }
    return digits;
  }


  private static String buildBirthDate(int day, int month, int year) {
    return validateDayOrMonth(day) +
        "." +
        validateDayOrMonth(month) +
        "." +
        year;
  }

  private static String validateDayOrMonth(int dayOrMonth) {
    StringBuilder sb = new StringBuilder();
    if (dayOrMonth < 10) sb.append(0);
    sb.append(dayOrMonth);
    return sb.toString();
  }

  private static int getBirthYear(List<Integer> pesel) {
    int year;
    int month;

    year = 10 * pesel.get(0);
    year += pesel.get(1);
    month = 10 * pesel.get(2);
    month += pesel.get(3);

    if (month > 80 && month < 93) {
      year += 1800;
    } else if (month > 0 && month < 13) {
      year += 1900;
    } else if (month > 20 && month < 33) {
      year += 2000;
    } else if (month > 40 && month < 53) {
      year += 2100;
    } else if (month > 60 && month < 73) {
      year += 2200;
    }

    return year;
  }

  private static int getBirthMonth(List<Integer> pesel) {
    int month;

    month = 10 * pesel.get(2);
    month += pesel.get(3);
    if (month > 80 && month < 93) {
      month -= 80;
    } else if (month > 20 && month < 33) {
      month -= 20;
    } else if (month > 40 && month < 53) {
      month -= 40;
    } else if (month > 60 && month < 73) {
      month -= 60;
    }

    return month;
  }

  private static int getBirthDay(List<Integer> pesel) {
    int day;
    day = 10 * pesel.get(4);
    day += pesel.get(5);

    return day;
  }

}
