package model;

import java.util.ArrayList;

public class Piece {
    /** Class representing a music piece (list of notes).
     */
    private ArrayList<Note> piece;

    // EFFECTS: Constructs a new blank piece of music with no notes
    private Piece() {
        piece = new ArrayList<Note>();
    }

    // EFFECTS: Returns number of notes in the piece
    public int length() {
        return piece.size();
    }

    // EFFECTS: Returns the duration of the piece as a number of beats
    public double pieceDuration() {
        double piecetime = 0;
        for (Note n : piece) {
            piecetime += n.getDuration();
        }
        return piecetime;
    }

}
