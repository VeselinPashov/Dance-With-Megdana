package com.Megdana.Dance.with.Megdana.domain.dto.models;

import com.Megdana.Dance.with.Megdana.domain.entities.Dance;

import java.time.LocalDate;
import java.util.List;

public class RehearsalModel {

    private Long id;
    private LocalDate date;

    private int durationInSeconds;

    private String location;

    private List<Dance> dances;


    public RehearsalModel() {
    }

    public Long getId() {
        return id;
    }

    public RehearsalModel setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public RehearsalModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public RehearsalModel setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public RehearsalModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public List<Dance> getDances() {
        return dances;
    }

    public RehearsalModel setDances(List<Dance> dances) {
        this.dances = dances;
        return this;
    }
}
