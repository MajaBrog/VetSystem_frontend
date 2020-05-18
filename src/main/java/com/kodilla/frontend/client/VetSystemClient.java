package com.kodilla.frontend.client;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kodilla.frontend.domain.*;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VetSystemClient {
    ClientHttpRequestFactory requestFactory = new
            HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    private String backendURL = "http://localhost:8089//v1/";
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public List<Medication> getMedications() {

        String url = backendURL + "medication";

        try {
            Medication[] Response = restTemplate.getForObject(url, Medication[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Medication[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
    public Medication getMedication(Long id) {

        String url = backendURL + "medication/"+id;

        try {
            return restTemplate.getForObject(url, Medication.class);
        } catch (RestClientException e) {
            return null;
        }
    }
    public List<Medication> filterMedications(String text) {

        String url = backendURL + "medication/filter/" + text;

        try {
            Medication[] Response = restTemplate.getForObject(url, Medication[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Medication[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public void updateMedication(Medication medication) {
        String url = backendURL + "medication";

        String json = gson.toJson(medication);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createMedication(Medication medication) {
        String url = backendURL + "medication";

        String json = gson.toJson(medication);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, Medication.class);
    }

    public void deleteMedication(Medication medication) {
        restTemplate.delete(backendURL + "medication/" + medication.getId());
    }

    public Vaccination getVaccination(Long id) {

        String url = backendURL + "vaccination/"+id;

        try {

            return restTemplate.getForObject(url, Vaccination.class);
        } catch (RestClientException e) {
            return null;
        }
    }

    public List<Vaccination> getVaccinations() {

        String url = backendURL + "vaccination";

        try {
            Vaccination[] Response = restTemplate.getForObject(url, Vaccination[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Vaccination[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public List<Vaccination> filterVaccinations(String text) {

        String url = backendURL + "vaccination/filter/" + text;

        try {
            Vaccination[] Response = restTemplate.getForObject(url, Vaccination[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Vaccination[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public void updateVaccination(Vaccination vaccination) {
        String url = backendURL + "vaccination";

        String json = gson.toJson(vaccination);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createVaccination(Vaccination vaccination) {
        String url = backendURL + "vaccination";

        String json = gson.toJson(vaccination);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, Vaccination.class);
    }

    public void deleteVaccination(Vaccination vaccination) {
        restTemplate.delete(backendURL + "vaccination/" + vaccination.getId());
    }

    public List<Client> getClients() {

        String url = backendURL + "client";

        try {
            Client[] Response = restTemplate.getForObject(url, Client[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Client[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public Client getClient(Long id) {

        String url = backendURL + "client/" + id;

        try {
            return restTemplate.getForObject(url, Client.class);
        } catch (RestClientException e) {
            return null;
        }
    }

    public List<Client> filterClients(String text) {

        String url = backendURL + "client/filter/" + text;

        try {
            Client[] Response = restTemplate.getForObject(url, Client[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Client[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public void updateClient(Client client) {
        String url = backendURL + "client";

        String json = gson.toJson(client);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.put(url,request);

    }

    public void createClient(Client client) {
        String url = backendURL + "client";

        String json = gson.toJson(client);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, Client.class);
    }

    public void deleteClient(Client client) {

        restTemplate.delete(backendURL + "client/" + client.getId());
    }

    public List<Pet> getPets() {

        String url = backendURL + "pet";

        try {
            Pet[] Response = restTemplate.getForObject(url, Pet[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Pet[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public Pet getPet(Long id) {

        String url = backendURL + "pet/" + id;

        try {
            return restTemplate.getForObject(url, Pet.class);
        } catch (RestClientException e) {
            return null;
        }
    }

    public List<Pet> getClientPets(Long clientId) {

        String url = backendURL + "pet" + "/client/" + clientId;

        try {
            Pet[] Response = restTemplate.getForObject(url, Pet[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Pet[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public void updatePet(Pet pet) {
        String url = backendURL + "pet";

        String json = gson.toJson(pet);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createPet(Pet pet) {
        String url = backendURL + "pet";

        String json = gson.toJson(pet);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, Pet.class);
    }

    public void deletePet(Pet pet) {
        restTemplate.delete(backendURL + "pet/" + pet.getId());
    }

    public List<Visit> getVisits() {

        String url = backendURL + "visit";

        try {
            Visit[] Response = restTemplate.getForObject(url, Visit[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Visit[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }
    public List<Visit> filterVisit(String text) {

        String url = backendURL + "visit/filter/"+text;

        try {
            Visit[] Response = restTemplate.getForObject(url, Visit[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Visit[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public List<Visit> getPetVisits(Long petId) {

        String url = backendURL + "visit" + "/pet/" + petId;

        try {
            Visit[] Response = restTemplate.getForObject(url, Visit[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Visit[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public void updateVisit(Visit visit) {
        String url = backendURL + "visit";

        String json = gson.toJson(visit);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public Visit createVisit(Visit visit) {
        String url = backendURL + "visit";

        String json = gson.toJson(visit);
        System.out.println(json);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        return restTemplate.postForObject(url, request, Visit.class);
    }

    public void deleteVisit(Visit visit) {
        restTemplate.delete(backendURL + "visit/" + visit.getId());
    }


    public List<ChronicDisease> getChronicDiseases() {

        String url = backendURL + "chronicDisease";

        try {
            ChronicDisease[] Response = restTemplate.getForObject(url, ChronicDisease[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new ChronicDisease[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public ChronicDisease getChronicDisease(Long id) {

        String url = backendURL + "chronicDisease/" + id;

        return restTemplate.getForObject(url, ChronicDisease.class);
    }

    public List<ChronicDisease_Pet> getPetChronicDiseases(Long petId) {

        String url = backendURL + "chronicDisease/pet/" + petId;

        try {
            ChronicDisease_Pet[] Response = restTemplate.getForObject(url, ChronicDisease_Pet[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new ChronicDisease_Pet[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public List<ChronicDisease> filterChronicDiseases(String text) {

        String url = backendURL + "chronicDisease/filter/" + text;

        try {
            ChronicDisease[] Response = restTemplate.getForObject(url, ChronicDisease[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new ChronicDisease[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public void updateChronicDisease(ChronicDisease chronicDisease) {
        String url = backendURL + "chronicDisease";

        String json = gson.toJson(chronicDisease);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createChronicDisease(ChronicDisease chronicDisease) {
        String url = backendURL + "chronicDisease";

//        String json = "{\"name\":\"" + chronicDisease.getName() + "\"}";
        String json = gson.toJson(chronicDisease);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, ChronicDisease.class);
    }

    public void deleteChronicDisease(ChronicDisease chronicDisease) {
        restTemplate.delete(backendURL + "chronicDisease/" + chronicDisease.getId());
    }

    public void deletePetChronicDisease(ChronicDisease_Pet chronicDisease_pet) {
        restTemplate.delete(backendURL + "chronicDisease/pet" + chronicDisease_pet.getId());
    }

    public void addChronicDisease(ChronicDisease_Pet chronicDisease_pet) {
        String url = backendURL + "chronicDisease/pet";

        String json = gson.toJson(chronicDisease_pet);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, ChronicDisease.class);
    }

    public Visit_Medication getVisit_Medication(Long id) {

        String url = backendURL + "visit_Medication/" + id;

        return restTemplate.getForObject(url, Visit_Medication.class);
    }

    public List<Visit_Medication> getVisit_Medications() {

        String url = backendURL + "visit_Medication";

        try {
            Visit_Medication[] Response = restTemplate.getForObject(url, Visit_Medication[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Visit_Medication[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }

    public List<Visit_Medication> getVisitMedications(Long visitId) {

        String url = backendURL + "visit_Medication/visit/"+ visitId;

        try {
            Visit_Medication[] Response = restTemplate.getForObject(url, Visit_Medication[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Visit_Medication[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
        }
    }




    public void createVisit_Medication(Visit_Medication visit_medication) {
        String url = backendURL + "visit_Medication";

        String json = gson.toJson(visit_medication);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, Visit_Medication.class);
    }

    public void deleteVisit_Medication(Visit_Medication visit_medication) {
        restTemplate.delete(backendURL + "visit_Medication/" + visit_medication.getId());
    }

    public void createVisit_Vaccination(Visit_Vaccination visit_vaccination) {
        String url = backendURL + "visit_Vaccination";

        String json = gson.toJson(visit_vaccination);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, Visit_Vaccination.class);
    }

    public void deleteVisit_Vaccination(Visit_Vaccination visit_vaccination) {
        restTemplate.delete(backendURL + "visit_Vaccination/" + visit_vaccination.getId());
    }


}
