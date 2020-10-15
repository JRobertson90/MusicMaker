package com.example;

public class NoteTrack {
    private final int note;
    private final String line;

    public NoteTrack(int note, String line) {
        this.note = note;
        this.line = line;
    }

    public int getNote() {
        return note;
    }

    public String getLine() {
        return line;
    }
}