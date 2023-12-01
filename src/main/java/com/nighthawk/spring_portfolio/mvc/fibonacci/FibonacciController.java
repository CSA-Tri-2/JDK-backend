package com.nighthawk.spring_portfolio.mvc.fibonacci;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fibonacci")
@CrossOrigin(origins = "http://localhost:4200")
public class FibonacciController {

    @GetMapping("/for/{num}")
    public FibonacciResponses fibonacciFor(@PathVariable int num) {
        System.out.println("Received request for fibonacciFor with num: " + num);
        return calculateFibonacci("For Loop", Fibonacci::fibonacciFor, num);
    }

    @GetMapping("/while/{num}")
    public FibonacciResponses fibonacciWhile(@PathVariable int num) {
        System.out.println("Received request for fibonacciWhile with num: " + num);
        return calculateFibonacci("While Loop", Fibonacci::fibonacciWhile, num);
    }

    @GetMapping("/recursion/{num}")
    public FibonacciResponses fibonacciRecursion(@PathVariable int num) {
        System.out.println("Received request for fibonacciRecursion with num: " + num);
        return calculateFibonacci("Recursion Loop", Fibonacci::fibonacciRecursion, num);
    }

    @GetMapping("/stream/{num}")
    public FibonacciResponses fibonacciStream(@PathVariable int num) {
        System.out.println("Received request for fibonacciStream with num: " + num);
        return calculateFibonacci("Stream Loop", Fibonacci::fibonacciStream, num);
    }

    private FibonacciResponses calculateFibonacci(String method, FibonacciFunction fibonacciFunction, int num) {
        long startTime = System.nanoTime();
        List<Long> fibonacciNumbers = fibonacciFunction.calculate(num);
        long endTime = System.nanoTime();
        double timeTaken = (endTime - startTime);

        return new FibonacciResponses(method, timeTaken, fibonacciNumbers);
    }

    private interface FibonacciFunction {
        List<Long> calculate(int num);
    }
}