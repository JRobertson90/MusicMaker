package com.example;

import java.util.List;

public class Song {
    private String title;
    private List<InstrumentTrack> tracks;

    public Song(String title, List<InstrumentTrack> tracks) {
        this.title = title;
        this.tracks = tracks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InstrumentTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<InstrumentTrack> tracks) {
        this.tracks = tracks;
    }
}
