package com.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SongImporter {

    public static Song importSong(String songFolder) {
        String songFile = songFolder + File.separator + "song.json";
        SongImportDetails importDetails = readSongFile(songFile);
        return importSong(importDetails);
    }

    public static Song importSong(SongImportDetails details) {
        if (details != null) {
            String title = details.title;
            List<InstrumentTrack> tracks = TrackImporter.importTracks(details.tracks);
            return new Song(title, tracks);
        }
        return null;
    }

    private static SongImportDetails readSongFile(String songFile) {
        try {
            File file = new File(songFile);
            Scanner scanner = new Scanner(file);
            StringBuilder songFileString = new StringBuilder();
            if(scanner.hasNextLine()) {
                songFileString.append(scanner.nextLine());
            }
            while(scanner.hasNextLine()) {
                songFileString.append("\n").append(scanner.nextLine());
            }
            SongImportDetails importDetails = new Gson().fromJson(songFileString.toString(), SongImportDetails.class);
            String basePath = file.getParentFile().getCanonicalPath();
            for(TrackImportDetails track : importDetails.tracks) {
                String relativePath = track.filePath;
                track.filePath = String.format("%s%s%s", basePath, File.separator, relativePath);
            }
            return importDetails;
        } catch (IOException e) {
            System.out.println("Failure to read song file: " + songFile);
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
