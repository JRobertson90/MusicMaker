package com.example;

import java.util.List;

public class InstrumentTrack {

    private final int instrumentNumber;
    private final List<NoteTrack> noteTracks;

    public InstrumentTrack(int instrumentNumber, List<NoteTrack> noteTracks) {
        this.instrumentNumber = instrumentNumber;
        this.noteTracks = noteTracks;
    }

    public int getInstrumentNumber() {
        return instrumentNumber;
    }

    public List<NoteTrack> getNoteTracks() {
        return noteTracks;
    }
}