package com.rakshitha;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {

    // TODO: put your real Gemini API key here
    private static final String API_KEY = "PUT_YOUR_API_KEY_HERE";

    // Gemini endpoint (text-only)
    private static final String ENDPOINT =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;
    // or you can also use:
    // "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + API_KEY;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== AI Study Notes Generator (Java Console) ===");
        System.out.print("Enter a topic: ");
        String topic = scanner.nextLine();

        String prompt = "Create very simple study notes for the topic: " + topic + "\n\n"
                + "Include:\n"
                + "1. Short summary\n"
                + "2. 5 key points\n"
                + "3. 5 interview questions with answers\n"
                + "Use very simple English.";

        try {
            String aiResponse = callGeminiApi(prompt);
            System.out.println("\n=== Generated Study Notes ===\n");
            System.out.println(aiResponse);
        } catch (Exception e) {
            System.out.println("Error while calling AI API: " + e.getMessage());
        }

        scanner.close();
    }

    private static String callGeminiApi(String prompt) throws Exception {
        // Build request body
        JsonObject textPart = new JsonObject();
        textPart.addProperty("text", prompt);

        JsonObject content = new JsonObject();
        JsonArray partsArray = new JsonArray();
        partsArray.add(textPart);
        content.add("parts", partsArray);

        JsonArray contentsArray = new JsonArray();
        contentsArray.add(content);

        JsonObject requestBody = new JsonObject();
        requestBody.add("contents", contentsArray);

        String bodyString = requestBody.toString();

        // HTTP client
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(bodyString))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("API error: " + response.statusCode() + " - " + response.body());
        }

        // Parse JSON
        JsonObject root = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray candidates = root.getAsJsonArray("candidates");

        if (candidates == null || candidates.size() == 0) {
            throw new RuntimeException("No candidates returned from API");
        }

        JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
        JsonObject contentObj = firstCandidate.getAsJsonObject("content");
        JsonArray parts = contentObj.getAsJsonArray("parts");

        if (parts == null || parts.size() == 0) {
            throw new RuntimeException("No parts returned from API");
        }

        String text = parts.get(0).getAsJsonObject().get("text").getAsString();
        return text;
    }
}
