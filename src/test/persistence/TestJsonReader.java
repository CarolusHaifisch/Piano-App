package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonReader {
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
        void testReaderInvalidFile() {
            JsonReader reader = new JsonReader("./data/noSuchFile.json");
            try {
                PiecesMemory pmem1 = reader.read();
                fail("Expected IOException");
            } catch (IOException e) {
            }
        }

        @Test
        void testReaderEmptyPiecesMemory() {
            JsonReader reader = new JsonReader(ComposerConstants.getFilePath() + "/EmptyPmemTest.json");
            try {
                PiecesMemory pmem = reader.read();
                assertEquals(0, pmem.numSavedPieces());
            } catch (IOException e) {
                fail("Cannot read saved file");
            }
        }

        @Test
        void testReaderPiecesMemoryWithPieces() {
            JsonReader reader = new JsonReader(ComposerConstants.getFilePath() + "/PmemTest.json");
            try {
                PiecesMemory pmem1 = reader.read();
                assertEquals("Test, Test1, ", pmem1.getPieceNames());
                assertEquals(2, pmem1.numSavedPieces());
                try {
                    assertEquals(4, pmem1.getPieceWithName("Test").length());
                } catch (Exception e) {
                    fail("Should not have thrown exception");
                }
                assertEquals(2, pmem1.getPieceWithIndex(1).length());
                assertEquals(1.5, pmem1.getPieceWithIndex(0).pieceDuration());
                assertEquals("D0/0.25 F#4/1.0 ", pmem1.getPieceWithIndex(1).pieceToString());
            } catch (IOException e) {
                fail("Cannot read saved file");
            }
        }
    }

