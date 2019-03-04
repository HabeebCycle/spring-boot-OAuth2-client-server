package com.habeeb.cycle.clientapplication.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habeeb.cycle.clientapplication.model.Employee;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@RestController
public class EmployeeController {

    @GetMapping("/client/employees")
    public ModelAndView getEmployees(){
        return new ModelAndView("employees");
    }

    @GetMapping("/")
    public String home(){
        return "employees";
    }

    @GetMapping("/user/employees")
    public ModelAndView showEmployees(@RequestParam("code") String code) throws IOException {
        ResponseEntity<String> response = null;
        System.out.println("Authorization Code: "+code);

        RestTemplate restTemplate = new RestTemplate();

        String credentials = "habeebcycle:secret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Accept","application/json");
        headers.add("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> request = new HttpEntity<>(headers);
        String accessTokenUrl = "http://localhost:8080/oauth/token";
        accessTokenUrl += "?code=" + code;
        accessTokenUrl += "&grant_type=authorization_code";
        accessTokenUrl += "&redirect_uri=http://localhost:8090/user/employees";

        response = restTemplate.exchange(accessTokenUrl, HttpMethod.POST, request, String.class);
        System.out.println("Access Token Response: " + response.getBody());

        // Get the Access Token From the recieved JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.getBody());
        String token = node.path("access_token").asText();

        String url = "http://localhost:8080/user/getEmployeesList";

        // Use the access token for authentication
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers1);

        ResponseEntity<Employee[]> employees = restTemplate.exchange(url, HttpMethod.GET, entity, Employee[].class);
        System.out.println(employees);
        Employee[] employeeArray = employees.getBody();

        ModelAndView model = new ModelAndView("showEmployees");
        model.addObject("employees", Arrays.asList(employeeArray));
        return model;
    }
}
