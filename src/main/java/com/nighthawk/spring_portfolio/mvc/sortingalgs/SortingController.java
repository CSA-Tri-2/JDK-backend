package main.java.com.nighthawk.spring_portfolio.mvc.sortingalgs;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/sorting")
@CrossOrigin(origins = {"http://localhost:4200", "http://127.0.0.1:4200", "https://csa-tri-2.github.io/JDK-miniproject/sort", "https://csa-tri-2.github.io"})
public class SortingController {
    @GetMapping("/bubble")
    public ResponseEntity<SortingConstructor> bubbleSort() {
        System.out.println("Received request for Bubble Sort"); // Confirmation for Bubble Sort request
        return performSorting(new BubbleSort());
    }

    @GetMapping("/selection")
    public ResponseEntity<SortingConstructor> selectionSort() {
        System.out.println("Received request for Selection Sort"); // Confirmation for Selection Sort request
        return performSorting(new SelectionSort());
    }

    @GetMapping("/merge")
    public ResponseEntity<SortingConstructor> mergeSort() {
        System.out.println("Received request for Merge Sort"); // Confirmation for Merge Sort request
        return performSorting(new MergeSort());
    }

    @GetMapping("/insertion")
    public ResponseEntity<SortingConstructor> insertionSort() {
        System.out.println("Received request for Insertion Sort"); // Confirmation for Insertion Sort request
        return performSorting(new InsertionSort());
    }
    private ResponseEntity<SortingConstructor> performSorting(SortingAlgorithm sortingAlgorithm) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://covid-193.p.rapidapi.com/statistics"))
                    .header("X-RapidAPI-Key", "1748ee8916mshe4a05c6edb7af0ap1399f4jsn23f82b0ddfa3")
                    .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response body into an array of Covid objects
            Covid[] unsortedCovids = parseResponse(response.body());

            // Create an instance of the sorting algorithm
            Covid[] sortedCovids = sortingAlgorithm.sort(unsortedCovids);

            // Display sorting statistics
            System.out.println(sortingAlgorithm.getClass().getSimpleName());
            sortingAlgorithm.displayStatistics();

            // Create a response object
            SortingConstructor responseObj = new SortingConstructor(
                    sortingAlgorithm.getClass().getSimpleName(),
                    sortingAlgorithm.getIterations(),
                    sortingAlgorithm.getComparisons(),
                    sortingAlgorithm.getMergesOrSwaps(),
                    sortingAlgorithm.getExecutionTime(),
                    unsortedCovids,
                    sortedCovids
            );

            return new ResponseEntity<>(responseObj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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