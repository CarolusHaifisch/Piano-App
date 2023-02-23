package model;

public class Note {
    /** Requires JFugue to work. Class to represent a note, to be stored in a list of notes (IE. A piece),
     * to simplify handling data, which will then be converted into music with JFugue.
     */
    private String name;
    private int duration;
    private int octave;


    // REQUIRES: noteOctave >= 0 && noteOctave <= 10
    // EFFECTS: Constructs a note with given name, duration, and octave value
    // Sharp notes are denoted with '#', flats denoted with 'b'
    public Note(String noteName, int noteDuration, int noteOctave) {
        this.name = noteName;
        this.duration = noteDuration;
        this.octave = noteOctave;
    }
}
