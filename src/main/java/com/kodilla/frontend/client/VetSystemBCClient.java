//package com.kodilla.frontend.client;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.kodilla.frontend.domain.Medication;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class VetSystemBCClient<T>{
//    public T entityObject;
//
//    public VetSystemBCClient(T entityObject) {
//        this.entityObject = entityObject;
//    }
//
//    RestTemplate restTemplate = new RestTemplate();
//    private String backendURL = "http://localhost:8089//v1/";
//    Gson gson = new GsonBuilder()
//            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
//            .create();
//
//    public List<T> getAll(T entityObject) {
//
//        String url = backendURL + "medication";
//
//        try {
//            T[] Response = restTemplate.getForObject(url, T[].class);
//            return Arrays.asList(Optional.ofNullable(Response).orElse(new T[0]));
//        } catch (RestClientException e) {
//            return new ArrayList<>();
//        }
//    }
//}
