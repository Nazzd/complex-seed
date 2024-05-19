package com.nazzd.complex.seed.demo;

/**
 * @author v_guchen
 * @description TODO
 * @create 2024-05-18 01:20
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class RequestController {

    @RequestMapping(value = "/makeRequest", method = {RequestMethod.GET, RequestMethod.POST})
    public String makeRequest(
            @RequestParam String url,
            @RequestParam(required = false) String method,
            @RequestParam(required = false) Map<String, String> headers,
            @RequestParam(required = false) Map<String, String> parameters,
            @RequestParam(required = false) String requestBody,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(required = false) String contentType
    ) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpResponse response;

            // Determine request method
            if (method != null && method.equalsIgnoreCase("POST")) {
                HttpPost request = new HttpPost(url);

                // Set headers if provided
                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        request.setHeader(entry.getKey(), entry.getValue());
                    }
                }

                // Set parameters if provided
                if (parameters != null && !parameters.isEmpty()) {
                    // This example assumes parameters are form parameters
                    // Modify if parameters are JSON or other types
                    // Example: request.setEntity(new UrlEncodedFormEntity(parameters));
                }

                // Set request body if provided
                if (requestBody != null) {
                    if (contentType != null && !contentType.isEmpty()) {
                        // Set content type if provided
                        if (contentType.equalsIgnoreCase("application/json")) {
                            request.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));
                        } else if (contentType.equalsIgnoreCase("application/x-www-form-urlencoded")) {
                            // This example assumes parameters are form parameters
                            // Modify if parameters are JSON or other types
                            // Example: request.setEntity(new UrlEncodedFormEntity(parameters));
                        } else if (contentType.equalsIgnoreCase("multipart/form-data")) {
                            // You can handle multipart request body here
                        } else {
                            // Unsupported content type
                            return "Unsupported Content-Type";
                        }
                    } else {
                        request.setEntity(new StringEntity(requestBody));
                    }
                }

                // Build multipart request body if file is provided
                if (file != null) {
                    // You can handle file uploads here
                }

                response = httpClient.execute(request);
            } else { // Default to GET request
                String fullUrl = url;
                if (parameters != null && !parameters.isEmpty()) {
                    StringBuilder queryString = new StringBuilder("?");
                    for (Map.Entry<String, String> entry : parameters.entrySet()) {
                        queryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                    }
                    fullUrl += queryString.substring(0, queryString.length() - 1); // Remove the last '&'
                }
                HttpGet request = new HttpGet(fullUrl);

                // Set headers if provided
                if (headers != null) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        request.setHeader(entry.getKey(), entry.getValue());
                    }
                }

                response = httpClient.execute(request);
            }

            HttpEntity entity = response.getEntity();

            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return "Request failed: " + e.getMessage();
        }
    }
}

