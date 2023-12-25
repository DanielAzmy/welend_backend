package com.welend.welend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welend.welend.DTO.BaseRequest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

public final class Utility {
    public static Gson gson;
    private RestTemplate restTemplate;
    public static Logger log = Logger.getLogger(Utility.class.getName());

    static {
        gson = new GsonBuilder().create();
    }


    public String getRequest(BaseRequest request) {
        try {
            log.info("Enter GetRequest function...");
            String url = request.getURL();
            request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> myRequest = new HttpEntity<>(request.getHeaders());
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, myRequest, String.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String postRequest(BaseRequest input) {
        try {
            log.info("Enter PostRequest function...");
            String url = input.getURL();
            if (input.getHeaders() != null && !input.getHeaders().isEmpty()) {
                input.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            } else {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                input.setHeaders(headers);
            }
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, input.getBody(), String.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
