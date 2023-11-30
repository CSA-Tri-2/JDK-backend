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
        displayResults("For Loop", endTime - startTime, fibonacciNumbers); //divided by 1e9 to convert to seconds

        //calculation for time taken for while loop
        startTime = System.nanoTime();
        fibonacciNumbers = fibonacciWhile(num);
        endTime = System.nanoTime();
        displayResults("While Loop", endTime - startTime, fibonacciNumbers); //divided by 1e9 to convert to seconds

        //insert recursion loop

        //insert stream loop
        startTime = System.nanoTime();
        fibonacciNumbers = fibonacciStream(num);
        endTime = System.nanoTime();
        displayResults("Stream Loop", endTime - startTime, fibonacciNumbers);
    }
    //generate Fibonacci sequence using for loop
    private static List<Long> fibonacciFor(int num) {
        List<Long> result = new ArrayList<>(); //making list into arraylist with values
        long a = 0, b = 1; //initialize variables
        for (int i = 0; i < num; i++) { //keeps on iterating through the length of "num" declared earlier above in scanner
            result.add(a);
            long temp = a;
            a = b;
            b = temp + b; //continously adding values until intended index of values in list from scanner
        }
        return result;
    }

    // Generate Fibonacci sequence using while loop
    private static List<Long> fibonacciWhile(int num) {
        List<Long> result = new ArrayList<>();
        long a = 0, b = 1; //intialize variables
        int count = 0;
        while (count < num) {
            result.add(a);
            long temp = a;
            a = b;
            b = temp + b; //same idea with for loop
            count++;
        }
        return result;
    }

    //place fibonacci sequence calculation with recursion method here

    //place fibonacci sequence calculation with stream method here
    // private static List<Long> fibonacciStream(int num) {
    //     long a =  0, b = z1; // initalize the variables
        
    //     return result;
    // }

    //displaying results: method name, time taken, and Fibonacci numbers
    private static void displayResults(String method, double timeTaken, List<Long> fibonacciNumbers) {
        System.out.println("Method: " + method);
        System.out.println("Time taken: " + timeTaken + " nanoseconds");
        System.out.println("Fibonacci numbers: " + fibonacciNumbers);
        System.out.println();
    }
}
