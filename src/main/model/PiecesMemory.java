package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class PiecesMemory {
    /**
     * Class representing the memory of the program where composed pieces are saved. Pieces are saved in reverse order
     * as they are saved into memory (newest piece is first in memory).
     */
    private LinkedList<Piece> memory;

    // EFFECTS: Constructs a new blank memory of pieces
    public PiecesMemory() {
        memory = new LinkedList<Piece>();
    }

    // EFFECTS: Returns number of saved pieces in memory
    public int numSavedPieces() {
        return memory.size();
    }

    // MODIFIES: this
    // EFFECTS: Adds a Piece to the PiecesMemory.
    public void addPiece(Piece p) {
        memory.addFirst(p);
    }

    // MODIFIES: this
    // EFFECTS: Removes a Piece from the PiecesMemory at given index.
    public void delPiece(int index) {
        memory.remove(index);
    }

    // REQUIRES: pieces have unique names
    // MODIFIES: this
    // EFFECTS: Returns piece with the given piece name. If none have the given name creates a new empty Piece with
    // given name, adds it to th e memory, and returns this new piece.***** Potentially add a control here where we can
    // control whether we want to add a new piece or not with keyboard presses controlled in Composer. This can be done
    // with exception handling where we can throw an exception here if piece is not found and deal with it however we
    // want.
    public Piece getPieceWithName(String name) {
        for (Piece p : memory) {
            if (p.getPieceName() == name) {
                return p;
            }
        }
        Piece newPiece = new Piece(name, new ArrayList<Note>());
        this.addPiece(newPiece);
        return newPiece;
    }
}
