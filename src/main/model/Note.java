package model;

import java.io.Serializable;

public class Note implements Serializable {
    /** Class to represent a note, to be stored in a list of notes (A piece),
     * to simplify handling data, which will then be converted into music with JFugue. A note has a char name,
     * representing the musical note, a duration as a number of beats, an octave value representing which octave the
     * note is on, and whether the note is a sharp or flat. A note can only be either sharp or flat, or neither, it
     * cannot be both.
     */
    private static final long serialVersionUID = 1L;
    private char noteName;
    private double noteDuration;
    private int noteOctave;
    private boolean noteIsSharp;
    private boolean noteIsFlat;


    // REQUIRES: octave >= 0 && octave <= 10, duration > 0 && duration <= MAX_DURATION, isSharp ^ isFlat
    // noteName is one of C, D, E, F, G, A, B, or R
    // EFFECTS: Constructs a note with given name, duration, and octave value
    // Note names must be Capital letters.
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

    // EFFECTS:
    public String noteToString() {
        if (this.getSharp()) {
            String noteString = String.valueOf(this.getName()) + "#" + this.getOctave() + "/"
                    + this.getDuration() + " ";
            return noteString;
        } else if (this.getFlat()) {
            String noteString = String.valueOf(this.getName()) + "b" + this.getOctave() + "/"
                    + this.getDuration() + " ";
            return noteString;
        } else {
            String noteString = String.valueOf(this.getName()) + this.getOctave() + "/" + this.getDuration() + " ";
            return noteString;
        }
    }


    // EFFECTS: Returns note duration
    public double getDuration() {
        return noteDuration;
    }

    // EFFECTS: Returns note name
    public char getName() {
        return noteName;
    }

    // EFFECTS: Returns note octave
    public int getOctave() {
        return noteOctave;
    }

    // EFFECTS: Returns boolean whether note is sharp or not
    public boolean getSharp() {
        return noteIsSharp;
    }

    // EFFECTS: Returns boolean whether note is sharp or not
    public boolean getFlat() {
        return noteIsFlat;
    }
}
