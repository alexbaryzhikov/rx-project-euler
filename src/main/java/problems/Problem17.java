package problems;

/*
If the numbers 1 to 5 are written out in words: one, two, three, four, five,
then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

If all the numbers from 1 to 1000 (one thousand) inclusive were written out
in words, how many letters would be used?

NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two)
contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use
of "and" when writing out numbers is in compliance with British usage.
*/

import static util.Utils.nats;

public class Problem17 {

    private static String toWords(int x) {
        if (x == 1000) return "one thousand";
        StringBuilder sb = new StringBuilder();
        switch (x / 100) {
            case 1: sb.append("one hundred"); break;
            case 2: sb.append("two hundred"); break;
            case 3: sb.append("three hundred"); break;
            case 4: sb.append("four hundred"); break;
            case 5: sb.append("five hundred"); break;
            case 6: sb.append("six hundred"); break;
            case 7: sb.append("seven hundred"); break;
            case 8: sb.append("eight hundred"); break;
            case 9: sb.append("nine hundred"); break;
        }
        int d = x % 100;
        if (sb.length() > 0 && d > 0) sb.append(" and ");
        if (d < 10) {
            switch (d % 10) {
                case 1: sb.append("one"); break;
                case 2: sb.append("two"); break;
                case 3: sb.append("three"); break;
                case 4: sb.append("four"); break;
                case 5: sb.append("five"); break;
                case 6: sb.append("six"); break;
                case 7: sb.append("seven"); break;
                case 8: sb.append("eight"); break;
                case 9: sb.append("nine"); break;
            }
        } else if (d < 20) {
            switch (d) {
                case 10: sb.append("ten"); break;
                case 11: sb.append("eleven"); break;
                case 12: sb.append("twelve"); break;
                case 13: sb.append("thirteen"); break;
                case 14: sb.append("fourteen"); break;
                case 15: sb.append("fifteen"); break;
                case 16: sb.append("sixteen"); break;
                case 17: sb.append("seventeen"); break;
                case 18: sb.append("eighteen"); break;
                case 19: sb.append("nineteen"); break;
            }
        } else {
            switch (d / 10) {
                case 2: sb.append("twenty"); break;
                case 3: sb.append("thirty"); break;
                case 4: sb.append("forty"); break;
                case 5: sb.append("fifty"); break;
                case 6: sb.append("sixty"); break;
                case 7: sb.append("seventy"); break;
                case 8: sb.append("eighty"); break;
                case 9: sb.append("ninety"); break;
            }
            if (d % 10 > 0) sb.append(" ");
            switch (d % 10) {
                case 1: sb.append("one"); break;
                case 2: sb.append("two"); break;
                case 3: sb.append("three"); break;
                case 4: sb.append("four"); break;
                case 5: sb.append("five"); break;
                case 6: sb.append("six"); break;
                case 7: sb.append("seven"); break;
                case 8: sb.append("eight"); break;
                case 9: sb.append("nine"); break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        nats(1, 1001)
                .map(Long::intValue)
                .map(Problem17::toWords)
                .map(it -> it.replace(" ", "").length())
                .reduce(0, Integer::sum)
                .blockingSubscribe(System.out::println);
    }
}
