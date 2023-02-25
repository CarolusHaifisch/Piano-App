package model;

import java.util.ArrayList;

public class Piece {
    /** Class representing a music piece (list of notes).
     */
    private ArrayList<Note> pieceContents;
    private String pieceName;

    // EFFECTS: Constructs a new blank piece of music with no notes with given name
    public Piece(String name, ArrayList<Note> piececontents) {
        this.pieceContents = new ArrayList<Note>();
        this.pieceName = name;
    }

    // EFFECTS: Returns number of notes in the piece
    public int length() {
        return this.pieceContents.size();
    }

    // EFFECTS: Returns the duration of the piece as a number of beats
    public double getPieceDuration() {
        double piecetime = 0;
        for (Note n : this.pieceContents) {
            piecetime += n.getDuration();
        }
        return piecetime;
    }

    // EFFECTS: Returns the name of the piece.
    public String getPieceName() {
        return this.pieceName;
    }

    // EFFECTS: Returns the contents of the piece.
    public ArrayList<Note> getPieceContents() {
        return this.pieceContents;
    }

    // MODIFIES: this
    // EFFECTS: Adds Note to piece.
    public void addNote(Note n) {
        this.pieceContents.add(n);
    }

    // MODIFIES: this
    // EFFECTS: Deletes Note from piece at given index.
    public void delNote(int index) {
        this.pieceContents.remove(index);
    }
}
