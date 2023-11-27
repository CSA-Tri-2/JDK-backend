package com.nighthawk.spring_portfolio.mvc.sortingalgs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DogBreedsApiClient {

    public static void main(String[] args) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://dog-breeds2.p.rapidapi.com/dog_breeds"))
                    .header("X-RapidAPI-Key", "YOUR-RAPIDAPI-KEY")
                    .header("X-RapidAPI-Host", "dog-breeds2.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            
            // Print the response body
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}