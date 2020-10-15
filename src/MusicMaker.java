import javax.sound.midi.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

class MusicMaker {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = new ScheduledThreadPoolExecutor(2);
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();
        synth.loadAllInstruments(synth.getDefaultSoundbank());
        MidiChannel[] channels = synth.getChannels();
        Thread.sleep(1000); // Give it time to load everything

        InstrumentTrack bass = new InstrumentTrack(channels[1], 1, "tracks/bass.txt");
        InstrumentTrack melody = new InstrumentTrack(channels[0], 9, "tracks/melody.txt");

        executor.submit(melody);
        executor.submit(bass);
    }

}