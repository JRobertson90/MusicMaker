package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrackImporter {

    public static List<InstrumentTrack> importTracks(List<TrackImportDetails> importDetails) {
        List<InstrumentTrack> tracks = new ArrayList<>();
        for (TrackImportDetails importDetail : importDetails) {
            try {
                InstrumentTrack track = importTrack(importDetail.filePath, importDetail.instrumentNumber);
                tracks.add(track);
            } catch (FileNotFoundException e) {
                System.out.println("Could not find file: " + importDetail.filePath);
            }
        }
        return tracks;
    }

    public static InstrumentTrack importTrack(String filePath, int instrumentNumber) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner fileReader = new Scanner(file);
        List<NoteTrack> noteTracks = new ArrayList<>();
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            int note = Integer.parseInt(line.substring(0, 2));
            String musicline = line.substring(2);
            noteTracks.add(new NoteTrack(note, musicline));
        }
        return new InstrumentTrack(instrumentNumber, noteTracks);
    }
}
