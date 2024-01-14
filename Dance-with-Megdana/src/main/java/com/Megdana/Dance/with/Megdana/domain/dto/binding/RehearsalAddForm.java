package com.Megdana.Dance.with.Megdana.domain.dto.binding;

import com.Megdana.Dance.with.Megdana.domain.entities.Dance;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class RehearsalAddForm {
    private LocalDate date;

    @NotNull
    @Size (min = 5, message = "Location name must be at least 5 letters")
    private String location;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private List<Dance> dances;

    private int durationInSeconds;

    public RehearsalAddForm() {
    }

    public LocalDate getDate() {
        return date;
    }

    public RehearsalAddForm setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public RehearsalAddForm setLocation(String location) {
        this.location = location;
        return this;
    }

    public List<Dance> getDances() {
        return dances;
    }

    public RehearsalAddForm setDances(List<Dance> dances) {
        this.dances = dances;
        return this;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public RehearsalAddForm setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
        return this;
    }
}
