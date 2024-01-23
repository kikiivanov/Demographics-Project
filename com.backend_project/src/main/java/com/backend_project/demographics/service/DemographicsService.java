package com.backend_project.demographics.service;

import com.backend_project.demographics.model.Demographics;
import com.backend_project.demographics.repository.DemographicsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.net.http.HttpClient;

@Service
public class DemographicsService {

    private final DemographicsRepository demographicsRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<Demographics> getDemographics() {
        return demographicsRepository.findAll();
    }

    @Autowired
    public DemographicsService(DemographicsRepository demographicsRepository) {
        this.demographicsRepository = demographicsRepository;
    }

    public void deleteOldData() {
        demographicsRepository.deleteAll();
    }

    @PostConstruct
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void scheduledUpdate() {
        deleteOldData();
        processDemographicData();
    }

    public void processDemographicData() {
        String apiUrl = "https://services.arcgis.com/P3ePLMYs2RVChkJx/ArcGIS/rest/services/USA_Counties/FeatureServer/0/";
        String queryUrl = apiUrl + "query?where=1%3D1&outFields=POPULATION%2C+STATE_NAME&returnGeometry=false&f=json";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(queryUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode root = objectMapper.readTree(response.body());
            JsonNode features = root.get("features");

            if (features != null && features.isArray()) {
                for (JsonNode feature : features) {
                    JsonNode attributes = feature.get("attributes");
                    if (attributes != null) {
                        String stateName = attributes.get("STATE_NAME").asText();
                        int population = attributes.get("POPULATION").asInt();

                        Demographics demographics = new Demographics();
                        demographics.setStateName(stateName);
                        demographics.setPopulation(population);
                        demographicsRepository.save(demographics);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.getMessage();
        } catch (URISyntaxException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public List<Demographics> getDemographicsByStateName(String stateName) {
        return demographicsRepository.findByStateName(stateName);
    }
}
