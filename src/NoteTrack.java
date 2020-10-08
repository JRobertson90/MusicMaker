import javax.sound.midi.MidiChannel;

public class NoteTrack extends Track {

    private int note;
    private String line;

    NoteTrack(MidiChannel channel, int instrumentNumber, int note, String line) {
        super(channel, instrumentNumber);
        this.note = note;
        this.line = line;
    }

    @Override
    public void run() {
        for (char c : line.toCharArray()) {
            switch (c) {
                case 'X': eighthNote(note); break;
                case ' ': eighthRest(); break;
            }
        }
    }
}