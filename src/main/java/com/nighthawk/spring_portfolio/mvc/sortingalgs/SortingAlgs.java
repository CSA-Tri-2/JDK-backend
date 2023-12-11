package com.nighthawk.spring_portfolio.mvc.sortingalgs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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

    public void sort(DogBreed[] array) {
        // This method should be overridden by each sorting algorithm class
    }
}

class DogBreed {
    private int id;
    private String breed;
    private String origin;
    private String url;
    private String img;

    public DogBreed(int id, String breed, String origin, String url, String img) {
        this.id = id;
        this.breed = breed;
        this.origin = origin;
        this.url = url;
        this.img = img;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return "DogBreed{" +
                "id:" + id +
                ", breed:'" + breed + '\'' +
                ", origin:'" + origin + '\'' +
                ", url:'" + url + '\'' +
                ", img:'" + img + '\'' +
                '}';
    }
}

class BubbleSort extends SortingAlgorithm {
    @Override
    public void sort(DogBreed[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (array[j].getBreed().compareTo(array[j + 1].getBreed()) > 0) {
                    // Swap array[j] and array[j+1]
                    DogBreed temp = array[j];
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
    public void sort(DogBreed[] array) {
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
                if (array[j].getBreed().compareTo(array[minIndex].getBreed()) < 0) {
                    minIndex = j;
                }
                iterations++;
            }

            // Swap array[i] and array[minIndex]
            DogBreed temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;

            mergesOrSwaps++;
        }

        executionTime = System.currentTimeMillis() - executionTime;
    }
}

class MergeSort extends SortingAlgorithm {
    @Override
    public void sort(DogBreed[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        mergeSort(array, 0, array.length - 1);

        executionTime = System.currentTimeMillis() - executionTime;
    }

    private void merge(DogBreed[] array, int left, int mid, int right) { //this is the merge sorting algorithmsfor the dog breed but we suing the covid api now so like but we still ned to twork on it retropectivethis is the merge sorting algorithmsfor the dog breed but we suing the covid api now so like but we still ned to twork on it retropectivethis is the merge sorting algorithmsfor the dog breed but we suing the covid api now so like but we still ned to twork on it retropectivethis is the merge sorting algorithmsfor the dog breed but we suing the covid api now so like but we still ned to twork on it retropectivethis is the merge sorting algorithmsfor the dog breed but we suing the covid api now so like but we still ned to twork on it retropectivethis is the merge sorting algorithmsfor the dog breed but we suing the covid api now so like but we still ned to twork on it retropective
        int n1 = mid - left + 1;
        int n2 = right - mid;

        DogBreed[] leftArray = new DogBreed[n1];
        DogBreed[] rightArray = new DogBreed[n2];

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
            if (leftArray[i].getBreed().compareTo(rightArray[j].getBreed()) <= 0) {
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

    private void mergeSort(DogBreed[] array, int left, int right) {
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
    public void sort(DogBreed[] array) {
        iterations = 0;
        comparisons = 0;
        mergesOrSwaps = 0;
        executionTime = 0;

        executionTime = System.currentTimeMillis();

        int n = array.length;
        for (int i = 1; i < n; ++i) {
            DogBreed key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].getBreed().compareTo(key.getBreed()) > 0) {
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
                    .uri(URI.create("https://dog-breeds2.p.rapidapi.com/dog_breeds"))
                    .header("X-RapidAPI-Key", "48e23c6bf3msh9a6baf3e68d9a4ep14546ajsn1a39e98c4ad5")
                    .header("X-RapidAPI-Host", "dog-breeds2.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response body into an array of DogBreed objects
            DogBreed[] dogBreeds = parseResponse(response.body());

            // Create an instance of the sorting algorithm
            SortingAlgorithm sortingAlgorithm = new BubbleSort();

            // Sort the array of dog breeds
            sortingAlgorithm.sort(dogBreeds);

            // Display sorting statistics
            sortingAlgorithm.displayStatistics();

            // Print the sorted dog breeds
            for (DogBreed breed : dogBreeds) {
                System.out.println(breed);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DogBreed[] parseResponse(String responseBody) {
        // Parse the response body into an array of DogBreed objects
        // Example parsing logic: (You may need to adapt this based on the actual response format)
        String[] lines = responseBody.split("\n");
        DogBreed[] dogBreeds = new DogBreed[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(":");
            int id = Integer.parseInt(parts[1]);
            String breed = parts[3].replace("\"", "");
            String origin = parts[5].replace("\"", "");
            String url = parts[7].replace("\"", "");
            String img = parts[9].replace("\"", "");
            dogBreeds[i] = new DogBreed(id, breed, origin, url, img);
        }
        return dogBreeds;
    }
}
