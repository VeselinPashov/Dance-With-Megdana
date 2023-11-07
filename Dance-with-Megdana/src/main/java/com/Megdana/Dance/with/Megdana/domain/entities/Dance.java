package com.Megdana.Dance.with.Megdana.domain.entities;


import com.Megdana.Dance.with.Megdana.domain.enums.Measure;
import com.Megdana.Dance.with.Megdana.domain.enums.Region;
import jakarta.persistence.*;

import java.util.Date;

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
    private Date learnedDate;

    @Column
    private Date lastPlayedDate;

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

    public Date getLearnedDate() {
        return learnedDate;
    }

    public Dance setLearnedDate(Date learnedDate) {
        this.learnedDate = learnedDate;
        return this;
    }

    public Date getLastPlayedDate() {
        return lastPlayedDate;
    }

    public Dance setLastPlayedDate(Date lastPlayedDate) {
        this.lastPlayedDate = lastPlayedDate;
        return this;
    }
}
