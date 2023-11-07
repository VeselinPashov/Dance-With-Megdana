package com.Megdana.Dance.with.Megdana.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;

@Entity
@Table (name = "rehearsals")
public class Rehearsal extends  BaseEntity{

    @Column
    private Date date;

    @Column
    private Long durationInSeconds;

    @Column
    private String location;

    @OneToMany
    private List<Dance> dance;

    public Rehearsal() {
    }

    public Date getDate() {
        return date;
    }

    public Rehearsal setDate(Date date) {
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

    public List<Dance> getDance() {
        return dance;
    }

    public Rehearsal setDance(List<Dance> dance) {
        this.dance = dance;
        return this;
    }
}
