package com.Megdana.Dance.with.Megdana.domain.dto.binding;


import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SongAddForm {

    @NotNull
    @Size (min = 5, message = "Name of song must be longer than 5 letters")
    private String name;

    @NotNull
    @Size (min = 5, message = "Name of artist must be longer than 5 letters")
    private String performedBy;

    private Region region;

    private Measure measure;

    private int duration; //in seconds

    public SongAddForm() {
    }

    public String getName() {
        return name;
    }

    public SongAddForm setName(String name) {
        this.name = name;
        return this;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public SongAddForm setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public SongAddForm setRegion(Region region) {
        this.region = region;
        return this;
    }

    public Measure getMeasure() {
        return measure;
    }

    public SongAddForm setMeasure(Measure measure) {
        this.measure = measure;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public SongAddForm setDuration(int duration) {
        this.duration = duration;
        return this;
    }
}
