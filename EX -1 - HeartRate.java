/**
 * This program receives an age from the user and calculates for him the recommended heart rate range
 * 
 * @author (Gal Ben Artzi)
 */ 

import java.util.Scanner;
public class HeartRate
{
    public static void main(String[]args)
    {
        final int CONST = 220, HIGHEST = 85, LOWEST = 65, PERCENT = 100;

        System.out.println("This program calculates your target heart rate while exercising.");
        System.out.print("Enter your age: ");
        Scanner input = new Scanner(System.in);
        int age = input.nextInt();
        int max = (CONST - age) * HIGHEST / PERCENT;   //Calculates the maximum range
        int min = (CONST - age) * LOWEST / PERCENT;    //Calculates the minimum range
        System.out.print("Your estimated target heart rate zone is " + min + " - " + max + " beats per minute.");
    }
}
