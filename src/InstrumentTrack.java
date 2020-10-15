import javax.sound.midi.MidiChannel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class InstrumentTrack extends Track {

    List<NoteTrack> noteTracks = new ArrayList<>();

    InstrumentTrack(MidiChannel channel, int instrumentNumber, String trackFile) throws FileNotFoundException {
        super(channel, instrumentNumber);
        readTrackFile(trackFile);
    }

    private void readTrackFile(String trackFile) throws FileNotFoundException {
        File file = new File(trackFile);
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            int note = Integer.parseInt(line.substring(0, 2));
            String musicline = line.substring(2);
            noteTracks.add(new NoteTrack(channel, instrumentNumber, note, musicline));
        }
    }

    @Override
    public void run() {
        ExecutorService executor = new ScheduledThreadPoolExecutor(noteTracks.size());
        for(NoteTrack noteTrack : noteTracks) {
            executor.submit(noteTrack);
        }
    }
}