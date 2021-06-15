/**
 * This program receives a number from the user and modifies it according to the user's choice
 * 
 * @author (Gal Ben Artzi)
 */

import java.util.Scanner;

public class Number {
    public static void main(String[] args) {
        final int TEN = 10, NEGATIVE = -1, THOUSANDS = 1000, HUNDREDS = 100,
        UPPER_POSITIVE_LIMIT = 10000, LOWER_POSITIVE_LIMIT = 999,
        LOWER_NEGATIVE_BORDER = -10000, UPPER_NEGATIVE_LIMIT = -999,
        OPPOSITE_NUMBER = 1 , REVERSE_NUMBER = 2, OPPOSITE_AND_REVERSE_NUMBER = 3;
        int newNum = 0;
        System.out.println("Please enter a 4 digit number:");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        if ((num < UPPER_POSITIVE_LIMIT && num > LOWER_POSITIVE_LIMIT) || (num < UPPER_NEGATIVE_LIMIT && 
        num > LOWER_NEGATIVE_BORDER)){           //Checks if the number is in the correct ranges
            System.out.println("1. Reverse sign. \n2. Reverse number. \n3. Reverse sigh" +
                "\nPlease choose an option:");
            int select = input.nextInt();
            if (select == REVERSE_NUMBER || select == OPPOSITE_AND_REVERSE_NUMBER){     //Reverses the numeric order of the number
                int first = (num % TEN) * THOUSANDS;
                int second = ((num / TEN) % TEN) * HUNDREDS;
                int third = ((num / HUNDREDS) % TEN) * TEN;
                int fourth = num / THOUSANDS;
                newNum = first + second + third + fourth;
            }
            switch (select) {
                case OPPOSITE_NUMBER:
                num *= NEGATIVE;       //Makes the number negative
                System.out.println("The result is \n" + num);
                break;
                case REVERSE_NUMBER:
                System.out.println("The result is \n" + newNum);
                break;
                case OPPOSITE_AND_REVERSE_NUMBER:
                newNum *= NEGATIVE;    //Makes the number negative
                System.out.println("The result is \n" + newNum);
                break;
                default:
                System.out.println("Illegal option - you must choose 1, 2 or 3");

            }
        }
        else
            System.out.println("Illegal number - you must enter a 4 digit number");
    }
}
