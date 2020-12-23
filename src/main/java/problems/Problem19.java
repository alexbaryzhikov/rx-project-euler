package problems;

import static com.alexb.iterators.date.Month.JAN;
import static com.alexb.iterators.date.Weekday.SUN;
import static util.Utils.days;

/*
You are given the following information, but you may
prefer to do some research for yourself.

* 1 Jan 1900 was a Monday.
* Thirty days has September,
  April, June and November.
  All the rest have thirty-one,
  Saving February alone,
  Which has twenty-eight, rain or shine.
  And on leap years, twenty-nine.
* A leap year occurs on any year evenly divisible by 4,
  but not on a century unless it is divisible by 400.

How many Sundays fell on the first of the month during
the twentieth century (1 Jan 1901 to 31 Dec 2000)?
*/

public class Problem19 {

    public static void main(String[] args) {
        days()
                .skipWhile(it -> !(it.monthday == 1 && it.month == JAN && it.year == 1901))
                .takeUntil(it -> it.monthday == 1 && it.month == JAN && it.year == 2001)
                .filter(it -> it.weekday == SUN && it.monthday == 1)
                .count()
                .blockingSubscribe(System.out::println);
    }
}
