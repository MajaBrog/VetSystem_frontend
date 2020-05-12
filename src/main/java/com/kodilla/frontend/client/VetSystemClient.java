package com.kodilla.frontend.client;


import com.kodilla.frontend.domain.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VetSystemClient {
    RestTemplate restTemplate = new RestTemplate();
    private String backendURL = "http://localhost:8089//v1/";

    public List<Medication> getMedications() {

        String url = backendURL + "medication";

        try {
            Medication[] Response = restTemplate.getForObject(url, Medication[].class);
            return Arrays.asList(Optional.ofNullable(Response).orElse(new Medication[0]));
        } catch (RestClientException e) {
            return new ArrayList<>();
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

        String json = "{\"medicationName\":\"" + medication.getMedicationName() + "\"," +
                "\"dosePerKg\":\"" + medication.getDosePerKg() + "\"," +
                "\"unit\":\"" + medication.getUnit() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createMedication(Medication medication) {
        String url = backendURL + "medication";

        String json = "{\"medicationName\":\"" + medication.getMedicationName() + "\"," +
                "\"dosePerKg\":\"" + medication.getDosePerKg() + "\"," +
                "\"unit\":\"" + medication.getUnit() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, Medication.class);
    }

    public void deleteMedication(Medication medication) {
        restTemplate.delete(backendURL + "medication/" + medication.getId());
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

        String json = "{\"vaccinationName\":\"" + vaccination.getName() + "\"," +
                "\"dosePerKg\":\"" + vaccination.getDosePerKg() + "\"," +
                "\"unit\":\"" + vaccination.getUnit() + "\"}";

//        ObjectMapper mapper = new ObjectMapper();

//        String jsonString = mapper.writeValueAsString(vaccination);
//
//        System.out.println(jsonString);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createVaccination(Vaccination vaccination) {
        String url = backendURL + "vaccination";

        String json = "{\"vaccinationName\":\"" + vaccination.getName() + "\"," +
                "\"dosePerKg\":\"" + vaccination.getDosePerKg() + "\"," +
                "\"unit\":\"" + vaccination.getUnit() + "\"}";

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

        String json = "{\"legalID\":\"" + client.getLegalID() + "\"," +
                "\"firstName\":\"" + client.getFirstName() + "\"," +
                "\"lastName\":\"" + client.getLastName() + "\"," +
                "\"phoneNumber\":\"" + client.getPhoneNumber() + "\"," +
                "\"address\":{\"street\":\"" + client.getAddress().getStreet() + "\"," +
                "\"houseNumber\":" + client.getAddress().getHouseNumber() + "," +
                "\"homeNumber\":" + client.getAddress().getHomeNumber() + "," +
                "\"city\":\"" + client.getAddress().getCity() + "\"," +
                "\"postcode\":\"" + client.getAddress().getPostcode() + "\"}," +
                "\"email\":\"" + client.getEmail() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createClient(Client client) {
        String url = backendURL + "client";

        String json = "{\"legalID\":\"" + client.getLegalID() + "\"," +
                "\"firstName\":\"" + client.getFirstName() + "\"," +
                "\"lastName\":\"" + client.getLastName() + "\"," +
                "\"phoneNumber\":\"" + client.getPhoneNumber() + "\"," +
                "\"address\":{\"street\":\"" + client.getAddress().getStreet() + "\"," +
                "\"houseNumber\":" + client.getAddress().getHouseNumber() + "," +
                "\"homeNumber\":" + client.getAddress().getHomeNumber() + "," +
                "\"city\":\"" + client.getAddress().getCity() + "\"," +
                "\"postcode\":\"" + client.getAddress().getPostcode() + "\"}," +
                "\"email\":\"" + client.getEmail() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, Client.class);
    }

    public void deleteClient(Client client) {
        restTemplate.delete(backendURL + "client" + client.getId());
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

        String json = "{\"chipId\":\"" + pet.getChipId() + "\"," +
                "\"name\":\"" + pet.getName() + "\"," +
                "\"kind\":\"" + pet.getKind() + "\"," +
                "\"birthDate\":\"" + pet.getBirthDate() + "\"," +
                "\"sterilised\":" + pet.isSterilised() + "," +
                "\"dateOfSterilization\":\"" + pet.getDateOfSterilization() + "\"," +
                "\"aggressive\":" + pet.isAggressive() + "," +
                "\"clientId\":\"" + pet.getClientId() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createPet(Pet pet) {
        String url = backendURL + "pet";

        String json = "{\"chipId\":\"" + pet.getChipId() + "\"," +
                "\"name\":\"" + pet.getName() + "\"," +
                "\"kind\":\"" + pet.getKind() + "\"," +
                "\"birthDate\":\"" + pet.getBirthDate() + "\"," +
                "\"sterilised\":" + pet.isSterilised() + "," +
                "\"dateOfSterilization\":\"" + pet.getDateOfSterilization() + "\"," +
                "\"aggressive\":" + pet.isAggressive() + "," +
                "\"clientId\":\"" + pet.getClientId() + "\"}";

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

        String json = "{\"dateOfVisit\":" + visit.getDateOfVisit() + "," +
                "\"petId\":\"" + visit.getPetId() + "\"," +
                "\"diagnose\":\"" + visit.getDiagnose() + "\"," +
                "\"AdditionalRecommendation\":\"" + visit.getAdditionalRecommendation() + "\"," +
                "\"weight\":" + visit.getWeight() + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createVisit(Visit visit) {
        String url = backendURL + "visit";

        String json = "{\"dateOfVisit\":" + visit.getDateOfVisit() + "," +
                "\"petId\":\"" + visit.getPetId() + "\"," +
                "\"diagnose\":\"" + visit.getDiagnose() + "\"," +
                "\"AdditionalRecommendation\":\"" + visit.getAdditionalRecommendation() + "\"," +
                "\"weight\":" + visit.getWeight() + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, Visit.class);
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

        String json = "{\"name\":\"" + chronicDisease.getName() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

    }

    public void createChronicDisease(ChronicDisease chronicDisease) {
        String url = backendURL + "chronicDisease";

        String json = "{\"name\":\"" + chronicDisease.getName() + "\"}";

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

        String json = "{\"petId\":\"" + chronicDisease_pet.getPetId() + "\"," +
                "\"chronicDiseaseId\":\"" + chronicDisease_pet.getChronicDiseaseId() + "\"," +
                "\"dateOfDiagnosis\":\"" + chronicDisease_pet.getDateOfDiagnosis() + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        restTemplate.postForObject(url, request, ChronicDisease.class);
    }
}
