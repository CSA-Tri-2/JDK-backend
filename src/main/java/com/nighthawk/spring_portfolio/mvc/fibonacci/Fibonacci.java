package com.nighthawk.spring_portfolio.mvc.fibonacci;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  //scanner used for input on how many numbers for fibonacci
        System.out.print("Enter the number of Fibonacci numbers to generate: ");
        int num = scanner.nextInt();

        List<Long> fibonacciNumbers; //declaring a variable with a list of "long" objects

        //measures time taken for each for loop
        long startTime = System.nanoTime(); //using nanotime system
        fibonacciNumbers = fibonacciFor(num);
        long endTime = System.nanoTime();
        displayResults("For Loop", (endTime - startTime) / 1e9, fibonacciNumbers); //divided by 1e9 to convert to seconds


        

        //insert recursion loop

        //insert stream loop

    }
    //generate Fibonacci sequence using for loop
    private static List<Long> fibonacciFor(int num) {
        List<Long> result = new ArrayList<>(); //making list into arraylist with values
        long a = 0, b = 1;
        for (int i = 0; i < num; i++) { //keeps on iterating through the length of "num" declared earlier above in scanner
            result.add(a);
            long temp = a;
            a = b;
            b = temp + b; //continously adding values until intended index of values in list from scanner
        }
        return result;
    }

    //place fibonacci sequence calculation with recursion method here

    //place fibonacci sequence calculation with stream method here

    // Display results including method name, time taken, and Fibonacci numbers
    private static void displayResults(String method, double timeTaken, List<Long> fibonacciNumbers) {
        System.out.println("Method: " + method);
        System.out.println("Time taken: " + timeTaken + " seconds");
        System.out.println("Fibonacci numbers: " + fibonacciNumbers);
        System.out.println();
    }
}
