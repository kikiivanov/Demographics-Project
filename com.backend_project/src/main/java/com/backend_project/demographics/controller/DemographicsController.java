package com.backend_project.demographics.controller;

import com.backend_project.demographics.service.DemographicsService;
import com.backend_project.demographics.model.Demographics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/demographics")
public class DemographicsController {

    private final DemographicsService demoService;

    @Autowired
    public DemographicsController(DemographicsService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/get")
    public List<Demographics> getDemographics() throws IOException, URISyntaxException, InterruptedException {
        demoService.scheduledUpdate();
        return demoService.getDemographics();
    }

    @GetMapping("/filter")
    public List<Demographics> getDemographicsByStateName(@RequestParam(name = "stateName") String stateName) throws IOException, URISyntaxException, InterruptedException {
        demoService.scheduledUpdate();
        return demoService.getDemographicsByStateName(stateName);
    }
}
