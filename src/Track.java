import javax.sound.midi.MidiChannel;

public class Track implements Runnable {

    protected final MidiChannel channel;
    protected final int instrumentNumber;

    Track(MidiChannel channel, int instrumentNumber) {
        channel.programChange(instrumentNumber);
        this.channel = channel;
        this.instrumentNumber = instrumentNumber;
    }

    @Override public void run() {
        // Does nothing. Subclasses will override this to do something.
    }

    public void sixteenthNote(int... notes) {
        playNotes(125, notes);
    }

    public void eighthNote(int... notes) {
        playNotes(250, notes);
    }

    public void quarterNote(int... notes) {
        playNotes(500, notes);
    }

    public void halfNote(int... notes) {
        playNotes(1000, notes);
    }

    public void wholeNote(int... notes) {
        playNotes(2000, notes);
    }

    public void sixteenthRest() {
        rest(125);
    }

    public void eighthRest() {
        rest(250);
    }

    public void quarterRest() {
        rest(500);
    }

    public void halfRest() {
        rest(1000);
    }

    public void wholeRest() {
        rest(2000);
    }

    private void playNotes(int duration, int... notes) {
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