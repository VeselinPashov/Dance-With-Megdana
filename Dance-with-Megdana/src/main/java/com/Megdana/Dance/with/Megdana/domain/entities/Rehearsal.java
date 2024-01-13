package com.Megdana.Dance.with.Megdana.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table (name = "rehearsals")
public class Rehearsal extends  BaseEntity{

    @Column
    private LocalDate date;

    @Column
    private Long durationInSeconds;

    @Column
    private String location;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private List<Dance> dances;

    public Rehearsal() {
    }

    public LocalDate getDate() {
        return date;
    }

    public Rehearsal setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Long getDurationInSeconds() {
        return durationInSeconds;
    }

    public Rehearsal setDurationInSeconds(Long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Rehearsal setLocation(String location) {
        this.location = location;
        return this;
    }

    public List<Dance> getDances() {
        return dances;
    }

    public Rehearsal setDances(List<Dance> dances) {
        this.dances = dances;
        return this;
    }
}
