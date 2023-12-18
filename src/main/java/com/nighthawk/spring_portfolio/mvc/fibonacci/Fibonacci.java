package com.nighthawk.spring_portfolio.mvc.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class FibonacciBase {//abstract class providing common functionality for Fibonacci implementations
    public List<Long> calculateFibonacci(int num) {//method to calculate Fibonacci numbers and measure time
        long startTime = System.nanoTime();
        List<Long> fibonacciNumbers = performFibonacciCalculation(num);
        long endTime = System.nanoTime();
        displayResults(getMethodName(), endTime - startTime, fibonacciNumbers);
        return fibonacciNumbers;
    }

    protected abstract List<Long> performFibonacciCalculation(int num); //abstract method to be implemented by subclasses

    protected abstract String getMethodName(); //abstract method to get the method name

    private static void displayResults(String method, double timeTaken, List<Long> fibonacciNumbers) {//displaying results method
        System.out.println("Method: " + method);
        System.out.println("Time taken: " + timeTaken / 1e6 + " milliseconds"); //convert to milliseconds
        System.out.println("Fibonacci numbers: " + fibonacciNumbers);
                for (int i = 0; i < fibonacciNumbers.size(); i++) {
            System.out.println("Step " + (i + 1) + ": " + fibonacciNumbers.get(i));
        }

        System.out.println();
    }
}

class FibonacciFor extends FibonacciBase {//implementation of FibonacciBase using a for loop
    @Override
    protected List<Long> performFibonacciCalculation(int num) {
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

    @Override
    protected String getMethodName() {
        return "For Loop";
    }
}

class FibonacciWhile extends FibonacciBase {//implementation of FibonacciBase class using a while loop
    @Override
    protected List<Long> performFibonacciCalculation(int num) {
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

    @Override
    protected String getMethodName() {
        return "While Loop";
    }
}

class FibonacciRecursion extends FibonacciBase {//implementation of FibonacciBase using recursion
    @Override
    protected List<Long> performFibonacciCalculation(int num) {
        List<Long> result = new ArrayList<>();
        fibonacciRecursionStructure(num, 0, 1, result);
        return result;
    }

    private static void fibonacciRecursionStructure(int num, long a, long b, List<Long> result) {//helper method for recursive loop calculation
        if (num > 0) {
            result.add(a);
            fibonacciRecursionStructure(num - 1, b, a + b, result);
        }
    }

    @Override
    protected String getMethodName() {
        return "Recursion Loop";
    }
}

class FibonacciStream extends FibonacciBase {//implementation of FibonacciBase using Java Streams
    @Override
    protected List<Long> performFibonacciCalculation(int num) {
        return Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                .limit(num)
                .mapToLong(f -> f[0])
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    protected String getMethodName() {
        return "Stream Loop";
    }
}

public class Fibonacci {//fibonacci class to show the different loops
    public static void main(String[] args) {
        int num = getUserInput(); // user's frontend input 

        FibonacciBase fibonacciFor = new FibonacciFor();//instantiate and calculate Fibonacci using For Loop
        fibonacciFor.calculateFibonacci(num);

        FibonacciBase fibonacciWhile = new FibonacciWhile();//same for while loop
        fibonacciWhile.calculateFibonacci(num);

        FibonacciBase fibonacciRecursion = new FibonacciRecursion();//same for recursion loop
        fibonacciRecursion.calculateFibonacci(num);

        FibonacciBase fibonacciStream = new FibonacciStream();//same for stream loop
        fibonacciStream.calculateFibonacci(num);
    }

    private static int getUserInput() {
        Scanner scanner = new Scanner(System.in); //scanner for number fo Fibonacci numbers user wants to input
        System.out.print("Enter the number of Fibonacci numbers to generate: ");
        int num;
        while (true) {
            try {
                num = scanner.nextInt();
                if (num > 0) {
                    break;
                } else {
                    System.out.print("Please enter a positive integer: "); //asking only for positive integer
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a positive integer: "); //will catch invalid input such as negative number or letter
                scanner.next(); //consume invalid input
            }
        }
        scanner.close();
        return num;
    }
}
