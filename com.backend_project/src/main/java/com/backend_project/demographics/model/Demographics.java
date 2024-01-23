package com.backend_project.demographics.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Demographics {

    @Id
    @SequenceGenerator(
            name = "demo_sequence",
            sequenceName = "demo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "demo_sequence"
    )

    @UuidGenerator
    private UUID id;
    private String stateName;
    private int population;

    public Demographics() {
    }

    public Demographics(String stateName, int population) {
        this.stateName = stateName;
        this.population = population;
    }

    public UUID getId() {
        return id;
    }

    public String getStateName() {
        return stateName;
    }

    public int getPopulation() {
        return population;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Demographics{" +
                "id=" + id +
                ", stateName='" + stateName + '\'' +
                ", population=" + population +
                '}';
    }
}
