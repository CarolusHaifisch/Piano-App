package model;

import java.io.Serializable;

/** Class to represent a note, to be stored in a list of notes (A piece),
 * which will then be converted into music with JFugue. A note has a char name,
 * representing the musical note, a duration as a number of beats, an octave value representing which octave the
 * note is on, and whether the note is a sharp or flat. A note can only be either sharp or flat, or neither, it
 * cannot be both. Octave is limited to between 0 and 9 inclusive. Note duration is limited to less than or equal to
 * MAX_DURATION as defined in ComposerConstants.
 */
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    private char noteName;
    private double noteDuration;
    private int noteOctave;
    private boolean noteIsSharp;
    private boolean noteIsFlat;


    // REQUIRES: octave >= 0 && octave <= 9, duration > 0 && duration <= MAX_DURATION, isSharp ^ isFlat
    // noteName is one of chars C, D, E, F, G, A, B, or R
    // EFFECTS: Constructs a note with given name, duration, octave value, and booleans representing whether note is
    // sharp or flat.
    public Note(char name, double duration, int octave, boolean isSharp, boolean isFlat) {
        this.noteName = name;
        this.noteDuration = duration;
        this.noteOctave = octave;
        this.noteIsSharp = isSharp;
        this.noteIsFlat = isFlat;
    }

    // EFFECTS: Takes in a Note and returns the Note represented in String form in the format of a JFugue string.
    public String noteToString() {
        if (this.getName() == 'R') {
            return this.restToString();
        } else if (this.getSharp()) {
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

    // EFFECTS: Converts a rest Note to a string in the format of JFugue strings and returns the string.
    public String restToString() {
        String noteString = String.valueOf(this.getName()) + "/" + this.getDuration() + " ";
        return noteString;
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

    // EFFECTS: Returns boolean whether note is flat or not
    public boolean getFlat() {
        return noteIsFlat;
    }
}
