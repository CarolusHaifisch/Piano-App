package model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Class containing all tests for methods in ComposerConstants class.
 *
 */

public class TestComposerConstants {
    String menuInstructions = "To load or save Pieces from saved memory enter L, "
            + "to compose a new piece or edit an existing piece in memory "
            + "enter E, to delete a piece from memory enter D, to play a piece from memory enter P,"
            + " to save Pieces and exit the program enter W, and to clear all pieces from PiecesMemory enter C."
            + " Pressing any other key will exit the program."
            + " All inputs for this program are case sensitive.";
    String saveFailed = "Unable to save properly. Save unsuccessful.";
    String pieceComposerPrompt = "Please input the note parameters when prompted to add a note"
            + " to the piece. When finished, enter X in place of noteName to finish piece and save the memory.";
    String FilePath = new File("").getAbsolutePath() + "/data";
    String FileDirectory = new File("").getAbsolutePath() + "/data/Pieces_Memory.json";
    ArrayList<Character> notesList = new ArrayList<Character>(asList('C','D','E','F','G','A','B','R'));

    @Test
            public void testComposerConstants() {
        assertEquals(menuInstructions, ComposerConstants.getMenuInstructions());
        assertEquals(saveFailed, ComposerConstants.getSaveFailed());
        assertEquals(pieceComposerPrompt, ComposerConstants.getPieceComposerPrompt());
        assertEquals(notesList, ComposerConstants.getNotesList());
        assertEquals(FilePath, ComposerConstants.getFilePath());
        assertEquals(FileDirectory, ComposerConstants.getFileDirectory());
    }



}
