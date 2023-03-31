package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    Note test;

    @BeforeEach
    void runBefore() {
        C5quarter = new Note('C', 0.25, 5, false, false);
        D0quarter = new Note('D', .25, 0, false, false);
        F4sharpwhole = new Note('F', 1, 4, true, false);
        B6flathalf = new Note('B', 0.5, 6, false, true);
        Rest5half = new Note('R', 0.5, 5, false, false);
        Rest1half = new Note('R', 0.5, 1, false, false);
        E9sharpquarter = new Note('E', 0.25, 9, true, false);
        test = new Note('C', 0, 0, false, false);
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

    @Test
    public void testSetters() {
        test.setName('E');
        assertEquals(test.getName(), 'E');
        test.setDuration(0.5);
        assertEquals(test.getDuration(), 0.5);
        test.setOctave(7);
        assertEquals(test.getOctave(), 7);
        test.setFlat();
        assertTrue(test.getFlat());
        assertFalse(test.getSharp());
        test.setSharp();
        assertTrue(test.getSharp());
        assertFalse(test.getFlat());
        test.setNatural();
        assertFalse(test.getFlat());
        assertFalse(test.getSharp());
    }
}