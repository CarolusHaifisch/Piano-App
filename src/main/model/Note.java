package model;

public class Note {
    /** Class to represent a note, to be stored in a list of notes (A piece),
     * to simplify handling data, which will then be converted into music with JFugue. A note has a char name,
     * representing the musical note, a duration as a number of beats, an octave value representing which octave the
     * note is on, and whether the note is a sharp or flat. A note can only be either sharp or flat, or neither, it
     * cannot be both.
     */
    private char noteName;
    private double noteDuration;
    private int noteOctave;
    private boolean noteIsSharp;
    private boolean noteIsFlat;


    // REQUIRES: octave >= 0 && octave <= 10, duration > 0 && duration <= MAX_DURATION, isSharp ^ isFlat
    // EFFECTS: Constructs a note with given name, duration, and octave value
    // Note names must be Capital letters. Maximum duration MAX_DURATION is defined in NoteConstants.
    public Note(char name, double duration, int octave, boolean isSharp, boolean isFlat) {
        //if (noteName != 0) {
         //
       // }
        this.noteName = name;
        this.noteDuration = duration;
        this.noteOctave = octave;
        this.noteIsSharp = isSharp;
        this.noteIsFlat = isFlat;
    }

    // EFFECTS: Returns note duration
    public double getDuration() {
        return noteDuration;
    }

    // EFFECTS: Returns note name
    public double getName() {
        return noteName;
    }

    // EFFECTS: Returns note octave
    public double getOctave() {
        return noteOctave;
    }
}
