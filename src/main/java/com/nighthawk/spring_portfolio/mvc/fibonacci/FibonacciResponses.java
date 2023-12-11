package com.nighthawk.spring_portfolio.mvc.fibonacci;

import java.util.List;

public class FibonacciResponses {
    private String method;
    private double timeTaken;
    private List<Long> fibonacciNumbers;
    private List<String> intermediateSteps; // New field for intermediate steps

    public FibonacciResponses(String method, double timeTaken, List<Long> fibonacciNumbers, List<String> intermediateSteps) {
        this.method = method;
        this.timeTaken = timeTaken;
        this.fibonacciNumbers = fibonacciNumbers;
        this.intermediateSteps = intermediateSteps;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(double timeTaken) {
        this.timeTaken = timeTaken;
    }

    public List<Long> getFibonacciNumbers() {
        return fibonacciNumbers;
    }

    public void setFibonacciNumbers(List<Long> fibonacciNumbers) {
        this.fibonacciNumbers = fibonacciNumbers;
    }

    public List<String> getIntermediateSteps() {
        return intermediateSteps;
    }

    public void setIntermediateSteps(List<String> intermediateSteps) {
        this.intermediateSteps = intermediateSteps;
    }
}