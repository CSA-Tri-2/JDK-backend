package com.nighthawk.spring_portfolio.mvc.fibonacci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class FibonacciBase {
    // Method to calculate Fibonacci numbers and measure time
    public List<Long> calculateFibonacci(int num) {
        long startTime = System.nanoTime();
        List<Long> fibonacciNumbers = performFibonacciCalculation(num);
        long endTime = System.nanoTime();
        displayResults(getMethodName(), endTime - startTime, fibonacciNumbers);
        return fibonacciNumbers;
    }

    // Abstract method to be implemented by subclasses
    protected abstract List<Long> performFibonacciCalculation(int num);

    // Abstract method to get the method name
    protected abstract String getMethodName();

    // Display results method
    private static void displayResults(String method, double timeTaken, List<Long> fibonacciNumbers) {
        System.out.println("Method: " + method);
        System.out.println("Time taken: " + timeTaken / 1e6 + " milliseconds"); // convert to milliseconds
        System.out.println("Fibonacci numbers: " + fibonacciNumbers);
        System.out.println();
    }
}

class FibonacciFor extends FibonacciBase {
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

class FibonacciWhile extends FibonacciBase {
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

class FibonacciRecursion extends FibonacciBase {
    @Override
    protected List<Long> performFibonacciCalculation(int num) {
        List<Long> result = new ArrayList<>();
        fibonacciRecursionStructure(num, 0, 1, result);
        return result;
    }

    private static void fibonacciRecursionStructure(int num, long a, long b, List<Long> result) {
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

class FibonacciStream extends FibonacciBase {
    @Override
    protected List<Long> performFibonacciCalculation(int num) {
        return java.util.stream.Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
                .limit(num)
                .mapToLong(f -> f[0])
                .boxed()
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    protected String getMethodName() {
        return "Stream Loop";
    }
}

public class Fibonacci {
    public static void main(String[] args) {
        int num = getUserInput();

        FibonacciBase fibonacciFor = new FibonacciFor();
        fibonacciFor.calculateFibonacci(num);

        FibonacciBase fibonacciWhile = new FibonacciWhile();
        fibonacciWhile.calculateFibonacci(num);

        FibonacciBase fibonacciRecursion = new FibonacciRecursion();
        fibonacciRecursion.calculateFibonacci(num);

        FibonacciBase fibonacciStream = new FibonacciStream();
        fibonacciStream.calculateFibonacci(num);
    }

    private static int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of Fibonacci numbers to generate: ");
        int num;
        while (true) {
            try {
                num = scanner.nextInt();
                if (num > 0) {
                    break;
                } else {
                    System.out.print("Please enter a positive integer: ");
                }
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a positive integer: ");
                scanner.next(); // consume invalid input
            }
        }
        scanner.close();
        return num;
    }
}
