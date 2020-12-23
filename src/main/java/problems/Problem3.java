package problems;

/*
The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143?
*/

import static util.Utils.factors;

public class Problem3 {

    public static void main(String[] args) {
        factors(600851475143L)
                .lastElement()
                .blockingSubscribe(System.out::println);
    }
}
