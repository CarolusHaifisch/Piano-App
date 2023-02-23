package model;

public class Note {
    /** Class to represent a note, to be stored in a list of notes (A piece),
     * to simplify handling data, which will then be converted into music with JFugue. A note has a char name,
     * representing the musical note, a duration as a number of beats, an octave value representing which octave the
     * note is on, and whether the note is a sharp or flat. A note can only be either sharp or flat, or neither, it
     * cannot be both.
     */
    private char name;
    private double duration;
    private int octave;
    private boolean isSharp;
    private boolean isFlat;


    // REQUIRES: noteOctave >= 0 && noteOctave <= 10, noteDuration > 0, isSharp ^ isFlat
    // EFFECTS: Constructs a note with given name, duration, and octave value
    // Note names must be Capital letters.
    public Note(char noteName, double noteDuration, int noteOctave, boolean noteIsSharp, boolean noteIsFlat) {
        //if (noteName != 0) {
         //
       // }
        this.name = noteName;
        this.duration = noteDuration;
        this.octave = noteOctave;
        this.isSharp = noteIsSharp;
        this.isFlat = noteIsFlat;
    }

    // EFFECTS: Returns note duration
    public double getDuration() {
        return duration;
    }
    // EFFECTS: Returns note name
    public double getName() {
        return name;
    }
    // EFFECTS: Returns note octave
    public double getOctave() {
        return octave;
    }
}
