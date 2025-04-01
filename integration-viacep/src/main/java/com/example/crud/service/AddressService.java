package com.example.crud.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

    private final RestTemplate restTemplate;

    public AddressService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCityByCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        try {
            JsonNode response = restTemplate.getForObject(url, JsonNode.class);
            if (response != null && response.has("localidade")) {
                return response.get("localidade").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

