package com.Megdana.Dance.with.Megdana.domain.dto.models;

import com.Megdana.Dance.with.Megdana.domain.entities.Song;
import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;

import java.time.LocalDate;
import java.util.Date;

public class DanceModel {
    private String name;

    private Song song;

    private Region region;

    private Measure measure;

    private Date learnedDate;

    private LocalDate lastPlayedDate;

    public DanceModel() {
    }

    public String getName() {
        return name;
    }

    public DanceModel setName(String name) {
        this.name = name;
        return this;
    }

    public Song getSong() {
        return song;
    }

    public DanceModel setSong(Song song) {
        this.song = song;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public DanceModel setRegion(Region region) {
        this.region = region;
        return this;
    }

    public Measure getMeasure() {
        return measure;
    }

    public DanceModel setMeasure(Measure measure) {
        this.measure = measure;
        return this;
    }

    public Date getLearnedDate() {
        return learnedDate;
    }

    public DanceModel setLearnedDate(Date learnedDate) {
        this.learnedDate = learnedDate;
        return this;
    }

    public LocalDate getLastPlayedDate() {
        return lastPlayedDate;
    }

    public DanceModel setLastPlayedDate(LocalDate lastPlayedDate) {
        this.lastPlayedDate = lastPlayedDate;
        return this;
    }
}
