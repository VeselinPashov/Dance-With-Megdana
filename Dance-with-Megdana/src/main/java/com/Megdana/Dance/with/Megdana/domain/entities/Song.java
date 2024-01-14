package com.Megdana.Dance.with.Megdana.domain.entities;

import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "Songs")
public class Song extends BaseEntity{

    @Column (nullable = false)
    private String name;

    @Column
    private String performedBy;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated (EnumType.STRING)
    private Measure measure;

    @Column
    private LocalDate lastPlayed;

    @Column
    private int duration; //in seconds

    public Song() {
    }

    public String getName() {
        return name;
    }

    public Song setName(String name) {
        this.name = name;
        return this;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public Song setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public Song setRegion(Region region) {
        this.region = region;
        return this;
    }

    public Measure getMeasure() {
        return measure;
    }

    public Song setMeasure(Measure measure) {
        this.measure = measure;
        return this;
    }

    public LocalDate getLastPlayed() {
        return lastPlayed;
    }

    public Song setLastPlayed(LocalDate lastPlayed) {
        this.lastPlayed = lastPlayed;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Song setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
