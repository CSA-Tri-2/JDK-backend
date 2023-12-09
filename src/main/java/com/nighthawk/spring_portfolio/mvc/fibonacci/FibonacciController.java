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
        return calculateFibonacci(new FibonacciFor(), num);
    }

    @GetMapping("/while/{num}")
    public FibonacciResponses fibonacciWhile(@PathVariable int num) {
        System.out.println("Received request for fibonacciWhile with num: " + num);
        return calculateFibonacci(new FibonacciWhile(), num);
    }

    @GetMapping("/recursion/{num}")
    public FibonacciResponses fibonacciRecursion(@PathVariable int num) {
        System.out.println("Received request for fibonacciRecursion with num: " + num);
        return calculateFibonacci(new FibonacciRecursion(), num);
    }

    @GetMapping("/stream/{num}")
    public FibonacciResponses fibonacciStream(@PathVariable int num) {
        System.out.println("Received request for fibonacciStream with num: " + num);
        return calculateFibonacci(new FibonacciStream(), num);
    }

    private FibonacciResponses calculateFibonacci(FibonacciBase fibonacciAlgorithm, int num) {
        long startTime = System.nanoTime();
        List<Long> fibonacciNumbers = fibonacciAlgorithm.calculateFibonacci(num);
        long endTime = System.nanoTime();
        double timeTaken = (endTime - startTime);

        return new FibonacciResponses(fibonacciAlgorithm.getMethodName(), timeTaken, fibonacciNumbers);
    }
}
