package com.backend_project.demographics.configuration;

import com.backend_project.demographics.model.Demographics;
import com.backend_project.demographics.repository.DemographicsRepository;
import com.backend_project.demographics.service.DemographicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DemographicsConfig {

    private final DemographicsService demographicsService;

    @Autowired
    public DemographicsConfig(DemographicsService demographicsService) {
        this.demographicsService = demographicsService;
    }

    @Bean
    public CommandLineRunner commandLineRunner(DemographicsRepository demographicsRepository) {
        return args -> {
            List<Demographics> demographics = demographicsService.getDemographics();
            demographicsRepository.saveAll(demographics);
        };
    }
}
