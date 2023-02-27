package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestComposerConstants {
    String menuInstructions = "To compose a new piece or edit an existing piece in memory "
            + "enter E, to delete a piece from memory enter D, to play a piece from memory enter P,"
            + " to save Pieces and exit the program enter W, and to clear all pieces from PiecesMemory enter C.";
    String saveFailed = "Unable to save properly. Save unsuccessful.";
    String pieceComposerPrompt = "Please input the note parameters when prompted to add a note"
            + " to the piece. When finished, enter X in place of noteName to finish piece and save the memory.";
    ArrayList<Character> notesList = new ArrayList<Character>(asList('C','D','E','F','G','A','B','R'));

    @Test
            public void testComposerConstants() {
        assertEquals(menuInstructions, ComposerConstants.getMenuInstructions());
        assertEquals(saveFailed, ComposerConstants.getSaveFailed());
        assertEquals(pieceComposerPrompt, ComposerConstants.getPieceComposerPrompt());
        assertEquals(notesList, ComposerConstants.getNotesList());
    }



}
