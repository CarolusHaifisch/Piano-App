package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Class containing all tests for methods in Note class.
 *
 */

class TestNote {
    Note C5quarter;
    Note D0quarter;
    Note F4sharpwhole;
    Note B6flathalf;
    Note Rest5half;
    Note Rest1half;
    Note E9sharpquarter;

    @BeforeEach
    void runBefore() {
        C5quarter = new Note('C', 0.25, 5, false, false);
        D0quarter = new Note('D', .25, 0, false, false);
        F4sharpwhole = new Note('F', 1, 4, true, false);
        B6flathalf = new Note('B', 0.5, 6, false, true);
        Rest5half = new Note('R', 0.5, 5, false, false);
        Rest1half = new Note('R', 0.5, 1, false, false);
        E9sharpquarter = new Note('E', 0.25, 9, true, false);
    }

    @Test
    public void testNotetoString() {
        String C5qstring = "C5/0.25 ";
        assertEquals(C5qstring, C5quarter.noteToString());
        String D0qstring = "D0/0.25 ";
        assertEquals(D0qstring, D0quarter.noteToString());
        String F4shwstring = "F#4/1.0 ";
        assertEquals(F4shwstring, F4sharpwhole.noteToString());
        String B6flhstring = "Bb6/0.5 ";
        assertEquals(B6flhstring, B6flathalf.noteToString());
        String Resthstring = "R/0.5 ";
        assertTrue(Resthstring.equals(Rest1half.noteToString()) && Resthstring.equals(Rest5half.noteToString()));
    }
}
//TODO: Create tests for setters!!!!!