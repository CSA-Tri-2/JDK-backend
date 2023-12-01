package com.nighthawk.spring_portfolio.mvc.fibonacci;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fibonacci")
public class FibonacciController {

    @GetMapping("/for/{num}")
    public FibonacciResponse fibonacciFor(@PathVariable int num) {
        return calculateFibonacci("For Loop", Fibonacci::fibonacciFor, num);
    }

    @GetMapping("/while/{num}")
    public FibonacciResponse fibonacciWhile(@PathVariable int num) {
        return calculateFibonacci("While Loop", Fibonacci::fibonacciWhile, num);
    }

    @GetMapping("/recursion/{num}")
    public FibonacciResponse fibonacciRecursion(@PathVariable int num) {
        return calculateFibonacci("Recursion Loop", Fibonacci::fibonacciRecursion, num);
    }

    @GetMapping("/stream/{num}")
    public FibonacciResponse fibonacciStream(@PathVariable int num) {
        return calculateFibonacci("Stream Loop", Fibonacci::fibonacciStream, num);
    }
}
