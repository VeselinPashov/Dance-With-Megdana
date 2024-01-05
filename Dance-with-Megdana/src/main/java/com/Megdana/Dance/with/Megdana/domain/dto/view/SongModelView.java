package com.Megdana.Dance.with.Megdana.domain.dto.view;

import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;

import java.util.Date;

public class SongModelView {

    private Long id;

    private String name;

    private String performedBy;

    private Region region;

    private Measure measure;

    private Date lastPlayed;

    public SongModelView() {
    }

    public Long getId() {
        return id;
    }

    public SongModelView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SongModelView setName(String name) {
        this.name = name;
        return this;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public SongModelView setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public SongModelView setRegion(Region region) {
        this.region = region;
        return this;
    }

    public Measure getMeasure() {
        return measure;
    }

    public SongModelView setMeasure(Measure measure) {
        this.measure = measure;
        return this;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public SongModelView setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
        return this;
    }
}
