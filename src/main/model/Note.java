package model;

public class Note {
    /** Requires JFugue to work. Class to represent a note, to be stored in a list of notes (IE. A piece),
     * to simplify handling data, which will then be converted into music with JFugue.
     */
    private char name;
    private double duration;
    private int octave;
    private boolean isSharp;
    private boolean isFlat;


    // REQUIRES: noteOctave >= 0 && noteOctave <= 10, noteDuration > 0
    // EFFECTS: Constructs a note with given name, duration, and octave value
    // Note names must be Capital letters.
    public Note(char noteName, double noteDuration, int noteOctave, boolean noteIsSharp, boolean noteIsFlat) {
        if (noteName != )
        this.name = noteName;
        this.duration = noteDuration;
        this.octave = noteOctave;
        this.isSharp = noteIsSharp;
        this.isFlat = noteIsFlat;
    }
}
