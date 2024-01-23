package com.backend_project.demographics.repository;

import com.backend_project.demographics.model.Demographics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemographicsRepository
        extends JpaRepository<Demographics, Long> {

    List<Demographics> findByStateName(String stateName);
}