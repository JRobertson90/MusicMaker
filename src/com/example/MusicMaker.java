package com.example;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MusicMaker {

    public static void main(String[] args) {
        MusicMaker maker = new MusicMaker();
        while(true) {
            System.out.print("Please enter the song folder name: ");
            Scanner scanner = new Scanner(System.in);
            String songFile = scanner.nextLine();
    //        String songFile = "songs/jingle-bells";

            Song song = SongImporter.importSong("songs" + File.separator + songFile);
            if (song != null) {
                maker.play(song);
            }

            System.out.print("Would you like to play another song (y or n)? ");
            String response = scanner.nextLine();
            switch (response.toLowerCase()) {
                case "y": case "yes": case "yeah": case "sure": break;
                default: System.exit(0);
            }
        }
    }

    private boolean midiLoaded;
    private Synthesizer synth;

    public MusicMaker() {
        loadSynthesizer();
    }

    private void loadSynthesizer() {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            synth.loadAllInstruments(synth.getDefaultSoundbank());
            midiLoaded = true;
            rest(1000); // Wait for things to load first
        } catch (MidiUnavailableException e) {
            System.out.println("Midi player is not available on this device at the moment.");
            midiLoaded = false;
        }
    }

    public void play(Song song) {
        if(midiLoaded && !song.getTracks().isEmpty()) {
            System.out.println("Playing \"" + song.getTitle() + "\"");
            ExecutorService executor = new ScheduledThreadPoolExecutor(100);
            MidiChannel[] channels = synth.getChannels();
            int currentChannel = 0;
            for (InstrumentTrack track : song.getTracks()) {
                // Set the instrument sound
                channels[currentChannel].programChange(track.getInstrumentNumber());
                for (NoteTrack noteTrack : track.getNoteTracks()) {
                    final MidiChannel channel = channels[currentChannel];
                    executor.submit(() -> {
                        for (char c : noteTrack.getLine().toCharArray()) {
                            switch (c) {
                                case 'X': eighthNote(channel, noteTrack.getNote()); break;
                                case ' ': eighthRest(); break;
                            }
                        }
                    });
                }
                currentChannel++;
            }
            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                System.out.println("Finished playing \"" + song.getTitle() + "\"");
            } catch (InterruptedException e) {
                System.out.println("Song was interrupted");
            }
        }
    }

    public void eighthNote(MidiChannel channel, int... notes) {
        playNotes(channel, 250, notes);
    }

    public void eighthRest() {
        rest(250);
    }

    private void playNotes(MidiChannel channel, int duration, int... notes) {
        for (int note : notes) {
            channel.noteOn(note, 100);
        }
        rest(duration);
        for (int note : notes) {
            channel.noteOff(note);
        }
    }

    private void rest(int duration) {
        try { Thread.sleep(duration); } catch (InterruptedException ignored) {}
    }
}