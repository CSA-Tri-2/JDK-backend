package com.nighthawk.spring_portfolio.mvc.fibonacci;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/fibonacci")
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200"})
public class FibonacciController {

    @GetMapping("/for/{num}")
    public ResponseEntity<FibonacciResponses> fibonacciFor(@PathVariable int num) {
        System.out.println("Received request for fibonacciFor with num: " + num); // confirmation for "for" request
        return calculateFibonacci(new FibonacciFor(), num); // calls the FibonacciFor procedure
    }

    @GetMapping("/while/{num}")
    public ResponseEntity<FibonacciResponses> fibonacciWhile(@PathVariable int num) {
        System.out.println("Received request for fibonacciWhile with num: " + num); // confirmation for "while" request
        return calculateFibonacci(new FibonacciWhile(), num); // calls the FibonacciWhile procedure
    }

    @GetMapping("/recursion/{num}")
    public ResponseEntity<FibonacciResponses> fibonacciRecursion(@PathVariable int num) {
        System.out.println("Received request for fibonacciRecursion with num: " + num); // confirmation for "recursion" request
        return calculateFibonacci(new FibonacciRecursion(), num); // calls the FibonacciRecursion procedure
    }

    @GetMapping("/stream/{num}")
    public ResponseEntity<FibonacciResponses> fibonacciStream(@PathVariable int num) {
        System.out.println("Received request for fibonacciStream with num: " + num); // confirmation for "stream" request
        return calculateFibonacci(new FibonacciStream(), num); // calls the FibonacciStream procedure
    }

    private ResponseEntity<FibonacciResponses> calculateFibonacci(FibonacciBase fibonacciAlgorithm, int num) {
        long startTime = System.nanoTime();
        List<Long> fibonacciNumbers = fibonacciAlgorithm.calculateFibonacci(num);
        long endTime = System.nanoTime();
        double timeTaken = (endTime - startTime);

        // creates the final response showed on the backend
        FibonacciResponses response = new FibonacciResponses(
            fibonacciAlgorithm.getMethodName(), 
            timeTaken, 
            fibonacciNumbers, 
            generateIntermediateSteps(fibonacciAlgorithm, num)
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // shows the steps taken towards the solution and which number was added using a loop to show the steps
    private List<String> generateIntermediateSteps(FibonacciBase fibonacciAlgorithm, int num) {
        List<Long> fibonacciNumbers = fibonacciAlgorithm.calculateFibonacci(num);
        List<String> intermediateSteps = new ArrayList<>();

        for (int i = 0; i < fibonacciNumbers.size(); i++) {
            intermediateSteps.add(" Step " + (i + 1) + ": " + fibonacciNumbers.get(i));
        }

        return intermediateSteps;
    }
}