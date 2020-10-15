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
            String note = line.substring(0, 3);
            int midiValue;
            if (note.matches(".*[A-Ga-g].*")) {
                midiValue = Character.getNumericValue(note.charAt(2) + 1) * 12;

                char letter = note.charAt(0);
                switch (letter) {
                    case 'C':
                        break;
                    case 'D':
                        midiValue += 2;
                        break;
                    case 'E':
                        midiValue += 4;
                        break;
                    case 'F':
                        midiValue += 5;
                        break;
                    case 'G':
                        midiValue += 7;
                        break;
                    case 'A':
                        midiValue += 9;
                        break;
                    case 'B':
                        midiValue += 11;
                        break;
                    default:
                        System.out.print("Not a musical note (character other than A through G): " + letter);
                }

                if (note.charAt(1) == 'F') {
                    midiValue -= 1;
                } else if (note.charAt(1) == 'S') {
                    midiValue += 1;
                }
            } else {
                midiValue = Integer.parseInt(note.trim());
            }

            String musicline = line.substring(3);
            noteTracks.add(new NoteTrack(channel, instrumentNumber, midiValue, musicline));
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