package model;

import java.util.ArrayList;

public class Piece {
    /** Class representing a music piece (list of notes).
     */
    private ArrayList<Note> piece;
    private String pieceName;

    // EFFECTS: Constructs a new blank piece of music with no notes
    public Piece(String name, ArrayList<Note> piece) {
        this.piece = new ArrayList<Note>();
        this.pieceName = name;
    }

    // EFFECTS: Returns number of notes in the piece
    public int length() {
        return this.piece.size();
    }

    // EFFECTS: Returns the duration of the piece as a number of beats
    public double getPieceDuration() {
        double piecetime = 0;
        for (Note n : this.piece) {
            piecetime += n.getDuration();
        }
        return piecetime;
    }

    // EFFECTS: Returns the name of the piece.
    public String getPieceName() {
        return this.pieceName;
    }

    // MODIFIES: this
    // EFFECTS: Adds Note to piece.
    public void addNote(Note n) {
        this.piece.add(n);
    }

    // MODIFIES: this
    // EFFECTS: Deletes Note from piece at given index.
    public void delNote(int index) {
        this.piece.remove(index);
    }
}
