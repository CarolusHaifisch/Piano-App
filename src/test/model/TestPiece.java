package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.ComposerConstants;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPiece {
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
    }

    @Test
    public void testPiece() {
        assertEquals("", Test.pieceToString());
        assertEquals(0, Test1.pieceDuration());
        assertEquals(0, Test.length());
        Test.addNote(C5quarter);
        assertEquals("C5/0.25 ", Test.pieceToString());
        assertEquals(0.25, Test.pieceDuration());
        assertEquals(1, Test.length());
        Test.addNote(Rest5half);
        Test.addNote(F4sharpwhole);
        Test1.addNote(E9sharpquarter);
        assertEquals("E#9/0.25 ", Test1.pieceToString());
        assertEquals(0.25, Test1.pieceDuration());
        assertEquals(1, Test1.length());
        Test1.addNote(B6flathalf);
        assertEquals("C5/0.25 R/0.5 F#4/1.0 ", Test.pieceToString());
        assertEquals("E#9/0.25 Bb6/0.5 ", Test1.pieceToString());
        assertEquals("", Test2.pieceToString());
        assertEquals(3, Test.length());
        assertEquals(2, Test1.length());
        assertEquals(0, Test2.length());
        assertEquals(1.75, Test.pieceDuration());
        assertEquals(0.75, Test1.pieceDuration());
        assertEquals(0, Test2.pieceDuration());
        assertEquals(Rest5half, Test.getNoteAtIndex(1));
        assertEquals(E9sharpquarter, Test1.getNoteAtIndex(0));
        Test.delNote(1);
        assertEquals("C5/0.25 F#4/1.0 ", Test.pieceToString());
        assertEquals(2, Test.length());
        assertEquals(1.25, Test.pieceDuration());
        Test1.delNote(0);
        assertEquals("Bb6/0.5 ", Test1.pieceToString());
        Test1.delNote(0);
        assertEquals("", Test1.pieceToString());
        assertEquals(0, Test1.length());
        assertEquals(0, Test1.pieceDuration());
        assertEquals("Test", Test.getPieceName());
        assertEquals("Test2", Test2.getPieceName());
    }

}
