package model;

import exception.PieceNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.LinkedList;

public class PiecesMemory extends LinkedList<Piece> implements Writable {
    /**
     * Class representing the memory of the program where composed pieces are saved. Pieces are saved in reverse order
     * as they are saved into memory (newest piece is first in memory).
     */
    private LinkedList<Piece> memory;
    private Event pieceaddedEvent;
    private Event pieceremovedEvent;
    private Event memoryclearedEvent;

    // EFFECTS: Constructs a new blank memory of pieces
    public PiecesMemory(LinkedList<Piece> mem) {
        this.memory = mem;
    }

    // EFFECTS: Returns number of saved pieces in memory
    public int numSavedPieces() {
        return memory.size();
    }

    // MODIFIES: this
    // EFFECTS: Adds a Piece to the PiecesMemory.
    public void addPiece(Piece p) {
        memory.addFirst(p);
        pieceaddedEvent = new Event("Piece " + p.getPieceName() + " added to local memory");
        EventLog.getInstance().logEvent(pieceaddedEvent);
    }

    // MODIFIES: this
    // EFFECTS: Removes a Piece from the PiecesMemory at given index.
    public void delPiece(int index) {
        pieceremovedEvent = new Event("Piece " + this.getPieceWithIndex(index) + "removed from local memory");
        memory.remove(index);
        EventLog.getInstance().logEvent(pieceremovedEvent);
    }

    // REQUIRES: pieces have unique names
    // MODIFIES: this
    // EFFECTS: Returns piece with the given piece name. If none have the given name creates a new empty Piece with
    // given name, adds it to th e memory, and returns this new piece.***** Potentially add a control here where we can
    // control whether we want to add a new piece or not with keyboard presses controlled in Composer. This can be done
    // with exception handling where we can throw an exception here if piece is not found and deal with it however we
    // want.
    public Piece getPieceWithName(String name) throws PieceNotFoundException {
        for (Piece p : memory) {
            if (p.getPieceName().equals(name)) {
                return p;
            }
        }
        throw new PieceNotFoundException("Piece not found.");
    }

    // EFFECTS: Returns the piece at the given index.
    public Piece getPieceWithIndex(int index) {
        return memory.get(index);
    }

    // EFFECTS: Returns a string with all the piece names of the pieces in memory,
    // in same order as memory (newest first)
    public String getPieceNames() {
        String names = "";
        for (Piece p : memory) {
            names += p.getPieceName() + ", ";

        }
        return names;
    }

    // EFFECTS: Returns index of piece with given name. Throws PieceNotFOundException if piece is not found.
    public int getIndexOfPiece(String name) throws PieceNotFoundException {
        return memory.indexOf(getPieceWithName(name));
    }

    // EFFECTS: Returns the PiecesMemory as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("piecesmemory", memorytoJson());
        return json;
    }

    // EFFECTS: Returns the contents of the PiecesMemory as a JSON array
    private JSONArray memorytoJson() {
        JSONArray jsonArray = new JSONArray();

        for (Piece p : memory) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    // MODIFIES: PiecesMemory memory
    // EFFECTS: Clear local PiecesMemory memory
    public void clearMemory() {
        this.memory =  new PiecesMemory(new LinkedList<>());
        memoryclearedEvent = new Event("Local memory cleared!");
        EventLog.getInstance().logEvent(memoryclearedEvent);

    }
}
