package com.Megdana.Dance.with.Megdana.domain.entities;


import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "Dances")
public class Dance extends BaseEntity{
    @Column (nullable = false)
    private String name;

    @ManyToOne
    private Song song;

    @Enumerated
    private Region region;

    @Enumerated
    private Measure measure;

    @Column
    private LocalDate learnedDate;

    @Column
    private LocalDate lastPlayedDate;

    @Column
    private int duration; //in seconds

    public Dance() {
    }

    public String getName() {
        return name;
    }

    public Dance setName(String name) {
        this.name = name;
        return this;
    }

    public Song getSong() {
        return song;
    }

    public Dance setSong(Song song) {
        this.song = song;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public Dance setRegion(Region region) {
        this.region = region;
        return this;
    }

    public Measure getMeasure() {
        return measure;
    }

    public Dance setMeasure(Measure measure) {
        this.measure = measure;
        return this;
    }

    public LocalDate getLearnedDate() {
        return learnedDate;
    }

    public Dance setLearnedDate(LocalDate learnedDate) {
        this.learnedDate = learnedDate;
        return this;
    }

    public LocalDate getLastPlayedDate() {
        return lastPlayedDate;
    }

    public Dance setLastPlayedDate(LocalDate lastPlayedDate) {
        this.lastPlayedDate = lastPlayedDate;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public Dance setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
