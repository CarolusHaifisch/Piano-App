package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/** Class containing all tests for methods in PiecesMemory class.
 *
 */

public class TestPiecesMemory {
    Note C5quarter;
    Note D0quarter;
    Note F4sharpwhole;
    Note B6flathalf;
    Note Rest5half;
    Note Rest1half;
    Note E9sharpquarter;
    Piece Test;
    Piece Test1;
    Piece Test2;
    PiecesMemory PMem;
    PiecesMemory PEmpty;

    @BeforeEach
    void runBefore() {
        C5quarter = new Note('C', 0.25, 5, false, false);
        D0quarter = new Note('D', .25, 0, false, false);
        F4sharpwhole = new Note('F', 1, 4, true, false);
        B6flathalf = new Note('B', 0.5, 6, false, true);
        Rest5half = new Note('R', 0.5, 5, false, false);
        Rest1half = new Note('R', 0.5, 1, false, false);
        E9sharpquarter = new Note('E', 0.25, 9, true, false);
        Test = new Piece("Test", new ArrayList<Note>());
        Test1 = new Piece("Test1", new ArrayList<Note>());
        Test2 = new Piece("Test2", new ArrayList<Note>());
        PMem = new PiecesMemory(new LinkedList<Piece>());
        PEmpty = new PiecesMemory(new LinkedList<Piece>());
        Test.addNote(C5quarter);
        Test.addNote(Rest5half);
        Test.addNote(F4sharpwhole);
        Test1.addNote(E9sharpquarter);
        Test1.addNote(B6flathalf);
    }

    @Test
    public void testPiecesMemory() {
        assertEquals(0, PMem.numSavedPieces());
        assertEquals("", PMem.getPieceNames());
        PMem.addPiece(Test);
        assertEquals("Test, ", PMem.getPieceNames());
        assertEquals(1, PMem.numSavedPieces());
        PMem.addPiece(Test2);
        PMem.addPiece(Test1);
        assertEquals(0, PEmpty.numSavedPieces());
        assertEquals("", PEmpty.getPieceNames());
        assertEquals(Test2, PMem.getPieceWithIndex(1));
        try {
            assertEquals(Test, PMem.getPieceWithName("Test"));
            assertEquals(Test2, PMem.getPieceWithName("Test2"));
        } catch (Exception e) {
            fail("Should not have thrown exception");
        }
        try {
            assertEquals("Gregor", PMem.getPieceWithName("Gregor").getPieceName());
            fail("Should have thrown exception");
        } catch (Exception e) {
        }
        assertEquals("Test1, Test2, Test, ", PMem.getPieceNames());
        assertEquals(3, PMem.numSavedPieces());
        assertEquals("", PEmpty.getPieceNames());
        PMem.delPiece(2);
        assertEquals("Test1, Test2, ", PMem.getPieceNames());
    }
}
