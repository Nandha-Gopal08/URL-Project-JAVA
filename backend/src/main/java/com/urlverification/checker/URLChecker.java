package com.urlverification.checker;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class URLChecker {

    private final HttpClient client;

    public URLChecker() {
        client = HttpClient.newBuilder()
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    public URLCheckResult checkURL(String url) {

        try {

            long startTime = System.currentTimeMillis();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(java.time.Duration.ofSeconds(15))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            long endTime = System.currentTimeMillis();

            long responseTime = endTime - startTime;

            int statusCode = response.statusCode();

            boolean reachable = statusCode >= 200 && statusCode < 400;

            boolean https = url.startsWith("https://");

            boolean redirected = response.previousResponse().isPresent();

            String redirectInfo = null;

            if (redirected) {
                redirectInfo = response.uri().toString();
            }

            return new URLCheckResult(
                    statusCode,
                    reachable,
                    https,
                    responseTime,
                    redirected,
                    redirectInfo
            );

        } catch (Exception e) {

            return new URLCheckResult(
                    -1,
                    false,
                    url.startsWith("https://"),
                    0,
                    false,
                    null
            );
        }
    }
}