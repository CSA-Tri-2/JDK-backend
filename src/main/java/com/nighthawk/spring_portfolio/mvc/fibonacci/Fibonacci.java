package com.nighthawk.spring_portfolio.mvc.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fibonacci {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  //scanner used for input on how many numbers for fibonacci
        System.out.print("Enter the number of Fibonacci numbers to generate: ");
        int num = scanner.nextInt();
        scanner.close();
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

        //measure time taken for recursion loop
        startTime = System.nanoTime();
        fibonacciNumbers = fibonacciRecursion(num);
        endTime = System.nanoTime();
        displayResults("Recursion Loop", (endTime - startTime), fibonacciNumbers);

        //measure time taken for stream loop
        startTime = System.nanoTime();
        fibonacciNumbers = fibonacciStream(num);
        endTime = System.nanoTime();
        displayResults("Stream Loop", (endTime - startTime), fibonacciNumbers);
    }

    //generate Fibonacci sequence using for loop
    public static List<Long> fibonacciFor(int num) {
        List<Long> result = new ArrayList<>();
        long a = 0, b = 1;
        for (int i = 0; i < num; i++) {
            result.add(a);
            long temp = a;
            a = b;
            b = temp + b;
        }
        return result;
    }

    //generate Fibonacci sequence using while loop
    public static List<Long> fibonacciWhile(int num) {
        List<Long> result = new ArrayList<>();
        long a = 0, b = 1;
        int count = 0;
        while (count < num) {
            result.add(a);
            long temp = a;
            a = b;
            b = temp + b;
            count++;
        }
        return result;
    }

    //generate Fibonacci sequence using recursion loop step 1
    public static List<Long> fibonacciRecursion(int num) {
        List<Long> result = new ArrayList<>();
        fibonacciRecursionStructure(num, 0, 1, result); // split into two functions so int num is the main data type
        return result;
    }

    private static void fibonacciRecursionStructure(int num, long a, long b, List<Long> result) {
        if (num > 0) {
            result.add(a);
            fibonacciRecursionStructure(num - 1, b, a + b, result);
        }
    }

    public static List<Long> fibonacciStream(int num) {
        return Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                .limit(num)
                .mapToLong(f -> f[0])
                .boxed()
                .collect(Collectors.toList());
    }

    //displaying results: method name, time taken, and Fibonacci numbers
    private static void displayResults(String method, double timeTaken, List<Long> fibonacciNumbers) {
        System.out.println("Method: " + method);
        System.out.println("Time taken: " + timeTaken + " nanoseconds");
        System.out.println("Fibonacci numbers: " + fibonacciNumbers);
        System.out.println();
    }
}
