package com.Megdana.Dance.with.Megdana.domain.dto.binding;

import com.Megdana.Dance.with.Megdana.domain.entities.Song;
import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class DanceAddForm {

    @NotNull
    @Size(min = 5, message = "Name of the dance should be at least 5 letters")
    private String name;

    @NotNull
    private Song song;

    @NotNull
    private Region region;

    @NotNull
    private Measure measure;

    private LocalDate learnedDate;

    @NotNull
    private int duration; //in seconds

    public DanceAddForm() {
    }

    public String getName() {
        return name;
    }

    public DanceAddForm setName(String name) {
        this.name = name;
        return this;
    }

    public Song getSong() {
        return song;
    }

    public DanceAddForm setSong(Song song) {
        this.song = song;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public DanceAddForm setRegion(Region region) {
        this.region = region;
        return this;
    }

    public Measure getMeasure() {
        return measure;
    }

    public DanceAddForm setMeasure(Measure measure) {
        this.measure = measure;
        return this;
    }

    public LocalDate getLearnedDate() {
        return learnedDate;
    }

    public DanceAddForm setLearnedDate(LocalDate learnedDate) {
        this.learnedDate = learnedDate;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public DanceAddForm setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
