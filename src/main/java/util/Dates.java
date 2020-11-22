package util;

import io.reactivex.rxjava3.core.Observable;

import java.util.Iterator;
import static util.Dates.Weekday.*;
import static util.Dates.Month.*;

public class Dates {

    public static Observable<Date> dates() {
        return Observable.generate(DateIterator::new, (dateIterator, dateEmitter) -> {
            dateEmitter.onNext(dateIterator.next());
        });
    }

    public static class DateIterator implements Iterator<Date> {
        private Date date = new Date(MON, 1, JAN, 1900);

        @Override
        public Date next() {
            Weekday weekday = date.weekday.next();
            int day = date.day == date.month.days(date.year) ? 1 : date.day + 1;
            Month month = day == 1 ? date.month.next() : date.month;
            int year = month == JAN && day == 1 ? date.year + 1 : date.year;
            Date result = date;
            date = new Date(weekday, day, month, year);
            return result;
        }

        @Override
        public boolean hasNext() {
            return true;
        }
    }

    public static class Date {
        public Weekday weekday;
        public int day;
        public Month month;
        public int year;

        public Date(Weekday weekday, int day, Month month, int year) {
            this.weekday = weekday;
            this.day = day;
            this.month = month;
            this.year = year;
        }

        @Override
        public String toString() {
            return weekday + " " + day + " " + month + " " + year;
        }
    }

    public enum Weekday {
        SUN, MON, TUE, WED, THU, FRI, SAT;

        public Weekday next() {
            return values()[(ordinal() + 1) % 7];
        }
    }

    public enum Month {
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;

        public Month next() {
            return values()[(ordinal() + 1) % 12];
        }

        public int days(int year) {
            switch (this) {
                case SEP:
                case APR:
                case JUN:
                case NOV:
                    return 30;
                case FEB:
                    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0) ? 29 : 28;
                default:
                    return 31;
            }
        }
    }
}
