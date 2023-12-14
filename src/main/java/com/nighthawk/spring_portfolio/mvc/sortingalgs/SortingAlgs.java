package com.nighthawk.spring_portfolio.mvc.sortingalgs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

class SortingAlgorithm {
    protected int iterations;
    protected int comparisons;
    protected int mergesOrSwaps;
    protected long executionTime;

    public SortingAlgorithm() {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;
    }

    public void resetStatistics() {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;
    }

    public void displayStatistics() {
        System.out.println("Iterations: " + iterations);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Merges/Swaps: " + mergesOrSwaps);
        System.out.println("Execution Time: " + executionTime + " milliseconds");
    }
    
    public Covid[] sort(Covid[] array) {
        // Create a copy of the original array
        Covid[] sortedArray = Arrays.copyOf(array, array.length);

        // Call the overridden sort method
        sorter(sortedArray);

        return sortedArray;
    }

    public void sorter(Covid[] array) {
        // This method should be overridden by each sorting algorithm class
    }
}

class Covid {
    private String country;
    private Integer population;
    private Integer cases;
    private Integer deaths;
    private Integer tests;

    public Covid(String country, Integer population, Integer cases, Integer deaths, Integer tests) {
        this.country = country;
        this.population = population;
        this.cases = cases;
        this.deaths = deaths;
        this.tests = tests;
    }

    public Integer getDeaths() {
        return deaths;
    }

    @Override
    public String toString() {
        return "Covid{" +
                "country:" + country +
                ", population:'" + population + '\'' +
                ", cases:'" + cases + '\'' +
                ", deaths:'" + deaths + '\'' +
                ", tests:'" + tests + '\'' +
                '}';
    }
}

class BubbleSort extends SortingAlgorithm {
    @Override
    public void sorter(Covid[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (array[j].getDeaths().compareTo(array[j + 1].getDeaths()) > 0) {
                    // Swap array[j] and array[j+1]
                    Covid temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    mergesOrSwaps++;
                }
                iterations++;
            }
        }

        executionTime = System.currentTimeMillis() - executionTime;
    }
}

class SelectionSort extends SortingAlgorithm {
    @Override
    public void sorter(Covid[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (array[j].getDeaths().compareTo(array[minIndex].getDeaths()) < 0) {
                    minIndex = j;
                }
                iterations++;
            }

            // Swap array[i] and array[minIndex]
            Covid temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;

            mergesOrSwaps++;
        }

        executionTime = System.currentTimeMillis() - executionTime;
    }
}

class MergeSort extends SortingAlgorithm {
    @Override
    public void sorter(Covid[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        mergeSort(array, 0, array.length - 1);

        executionTime = System.currentTimeMillis() - executionTime;
    }

    private void merge(Covid[] array, int left, int mid, int right) { 
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Covid[] leftArray = new Covid[n1];
        Covid[] rightArray = new Covid[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            comparisons++;
            if (leftArray[i].getDeaths().compareTo(rightArray[j].getDeaths()) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
            iterations++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
            mergesOrSwaps++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
            mergesOrSwaps++;
        }
    } 

    private void mergeSort(Covid[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }
}

class InsertionSort extends SortingAlgorithm {
    @Override
    public void sorter(Covid[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Covid key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].getDeaths().compareTo(key.getDeaths()) > 0) {
                comparisons++;
                array[j + 1] = array[j];
                j = j - 1;
                mergesOrSwaps++;
                iterations++;
            }
            array[j + 1] = key;
            iterations++;
        }

        executionTime = System.currentTimeMillis() - executionTime;
    }
}

public class SortingAlgs {
    public static void main(String[] args) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://covid-193.p.rapidapi.com/statistics"))
                    .header("X-RapidAPI-Key", "1748ee8916mshe4a05c6edb7af0ap1399f4jsn23f82b0ddfa3")
                    .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            // System.out.println(response.body());
            // Parse the response body into an array of Covid objects
            Covid[] unsortedcovids = parseResponse(response.body());
            
            for (Covid covid : unsortedcovids) {
                System.out.println(covid);
            }
            // Create an instance of the sorting algorithm
            SortingAlgorithm bubbleSortingAlgorithm = new BubbleSort();

            // Sort the array of dog breeds
            Covid[] bubbleCovids = bubbleSortingAlgorithm.sort(unsortedcovids);

            // Display sorting statistics
            System.out.println("Bubble");
            bubbleSortingAlgorithm.displayStatistics();

            SortingAlgorithm selectionSortingAlgorithm = new SelectionSort();

            // Sort the array of dog breeds
            Covid[] selectionCovids = selectionSortingAlgorithm.sort(unsortedcovids);

            // Display sorting statistics
            System.out.println("Selection");
            selectionSortingAlgorithm.displayStatistics();

            SortingAlgorithm mergSortingAlgorithm = new MergeSort();

            // Sort the array of dog breeds
            Covid[] mergeCovids = mergSortingAlgorithm.sort(unsortedcovids);

            // Display sorting statistics
            System.out.println("Merge");
            mergSortingAlgorithm.displayStatistics();
            

            SortingAlgorithm insertionSortingAlgorithm = new InsertionSort();

            // Sort the array of dog breeds
            Covid[] insertionCovids = insertionSortingAlgorithm.sort(unsortedcovids);

            // Display sorting statistics
            System.out.println("Insertion");
            insertionSortingAlgorithm.displayStatistics();
            
            // Print the sorted dog breeds
            for (Covid deaths : insertionCovids) {
                System.out.println(deaths);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Covid[] parseResponse(String responseBody) {
        // Parse the response body into an array of Covid objects
        // Example parsing logic: (You may need to adapt this based on the actual response format)
        String[] lines = responseBody.split("continent");

        // for (int i = 0; i < lines.length; i++) {
        //     System.out.println("AAAAAAAAA"+lines[i]);
        // }
        Covid[] covids = new Covid[lines.length];
        for (int i = 1; i < lines.length; i++) {
            // System.out.println("AAAAAAAAA"+lines[i]);
            String[] parts = lines[i].split(",");
            // System.out.println(parts[1]);
            // System.out.println(parts[2]);
            // System.out.println(parts[8]);
            // System.out.println(parts[11]);
            // System.out.println(parts[13]);
            String country = parts[1].replace("\"country\":", "");
            // System.out.println(country);
            String poptemp = parts[2].replace("\"population\":", "");
            // System.out.println(poptemp);
            if(poptemp.equals("null")){
                poptemp = "0";
            }
            Integer population = Integer.parseInt(poptemp);
            String casesTemp = parts[8].replace("\"total\":", "");
            // System.out.println(casesTemp);
            String casesm = casesTemp.substring(0, casesTemp.length()-1);
            if(casesm.equals("null")){
                casesm = "0";
            }
            Integer cases = Integer.parseInt(casesm);
            // System.out.println(cases);
            String deathsTemp = parts[11].replace("\"total\":", "");
            // System.out.println(deathsTemp);
            String deathsm = deathsTemp.substring(0, deathsTemp.length()-1);
            if(deathsm.equals("null")){
                deathsm = "0";
            }
            Integer deaths = Integer.parseInt(deathsm);
            // System.out.println(deaths);
            String testsTemp = parts[13].replace("\"total\":", "");
            // System.out.println(testsTemp);
            String testsm = testsTemp.substring(0, testsTemp.length()-1);
            if(testsm.equals("null")){
                testsm = "0";
            }
            Integer tests = Integer.parseInt(testsm);
            // System.out.println(tests);
            covids[i] = new Covid(country, population, cases, deaths, tests);
        }
        covids = Arrays.copyOfRange(covids, 1, covids.length);
        return covids;
    }
}
//test