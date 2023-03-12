package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

/** Class containing all tests for methods in Composer class.
 *
 */

class ComposerTest {
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
    File SavedMem;
    File NewMem;
    Composer composer;

    @BeforeEach
    void runBefore() {
        C5quarter = new Note('C', 0.25, 5, false, false);
        D0quarter = new Note('D', .25, 0, false, false);
        F4sharpwhole = new Note('F', 1, 4, true, false);
        B6flathalf = new Note('B', 0.5, 6, false, true);
        Rest5half = new Note('R', 0.5, 5, false, false);
        Rest1half = new Note('R', 0.5, 1, false, false);
        E9sharpquarter = new Note('E', 0.25, 9, true, false);
        SavedMem = new File(ComposerConstants.getFilePath() + "/Test.txt");
        NewMem = new File("Notvalidpath.txt");
        Test = new Piece("Test", new ArrayList<Note>());
        Test1 = new Piece("Test1", new ArrayList<Note>());
        Test2 = new Piece("Test2", new ArrayList<Note>());
        PMem = new PiecesMemory(new LinkedList<Piece>());
        PEmpty = new PiecesMemory(new LinkedList<Piece>());
        composer = new Composer();
    }

    @Test
    public void testMemExists() {
        assertTrue(composer.memExists(ComposerConstants.getFileDirectory()));
        assertFalse(composer.memExists(ComposerConstants.getFilePath() + "/Nonexistent.json"));
    }

    @Test
    public void testMemSaveandRetrieve(){
        Test.addNote(C5quarter);
        Test.addNote(Rest5half);
        Test.addNote(F4sharpwhole);
        PMem.addPiece(Test);
        PMem.addPiece(Test1);
        try {
            composer.memSave(PMem, ComposerConstants.getFilePath() + "/Test.json");
            assertEquals(PMem, composer.memRetrieve(ComposerConstants.getFilePath() + "/Test.json"));
            PiecesMemory pmemtest = composer.memRetrieve(ComposerConstants.getFilePath() + "/Test.json");
            assertEquals("Test, Test1, ", pmemtest.getPieceNames());
            assertEquals(2, pmemtest.numSavedPieces());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testMemRetrieveFail(){
        Test.addNote(C5quarter);
        Test.addNote(Rest5half);
        Test.addNote(F4sharpwhole);
        PMem.addPiece(Test);
        PMem.addPiece(Test1);
        try {
            PiecesMemory pmemtest = composer.memRetrieve(ComposerConstants.getFilePath() + "/TestFail.json");
            fail("Should have thrown exception");
        } catch (IOException e) {
        }
    }

    @Test
    public void testMemSaveFail(){
        Test.addNote(C5quarter);
        Test.addNote(Rest5half);
        Test.addNote(F4sharpwhole);
        PMem.addPiece(Test);
        PMem.addPiece(Test1);
        try {
            composer.memSave(PMem, ComposerConstants.getFilePath() + "/$#*&^@#R%$&#*%$(!*&#$<>?|}{.json");
            fail("Should have thrown exception");
        } catch (IOException e) {
        }
    }


}