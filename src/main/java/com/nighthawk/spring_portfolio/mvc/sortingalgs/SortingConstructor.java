package com.nighthawk.spring_portfolio.mvc.sortingalgs;

public class SortingConstructor {
    private String sortType;
    private int iterations;
    private int comparisons;
    private int mergesOrSwaps;
    private long executionTime; 
    private Covid[] unsortedarray;
    private Covid[] sortedarray;

    public SortingConstructor(String sortType, int iterations, int comparisons, int mergesOrSwaps, long executionTime, Covid[] unsortedarray, Covid[] sortedarray) {
        this.sortType = sortType;
        this.iterations = iterations;
        this.comparisons = comparisons;
        this.mergesOrSwaps = mergesOrSwaps;
        this.executionTime = executionTime;
        this.unsortedarray = unsortedarray;
        this.sortedarray = sortedarray;
    }

    public String getSortType() {
        return sortType;
    }
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public int getIterations() {
        return iterations;
    }
    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getComparisons() {
        return comparisons;
    }
    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }

    public int getMergesOrSwaps() {
        return mergesOrSwaps;
    }
    public void setMergesOrSwaps(int mergesOrSwaps) {
        this.mergesOrSwaps = mergesOrSwaps;
    }

    public long getExecutionTime() {
        return executionTime;
    }
    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public Covid[] getUnsortedArray() {
        return unsortedarray;
    }
    public void setUnsortedArray(Covid[] unsortedarray) {
        this.unsortedarray = unsortedarray;
    }

    public Covid[] getSortedArray() {
        return sortedarray;
    }
    public void setSortedArray(Covid[] sortedarray) {
        this.sortedarray = sortedarray;
    }

}