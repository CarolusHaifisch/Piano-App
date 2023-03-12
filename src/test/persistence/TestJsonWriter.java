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

public class TestJsonWriter {
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
        composer = new Composer();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            PiecesMemory pmem = new PiecesMemory(new LinkedList<Piece>());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected IOException");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptyPiecesMemory() {
        try {
            PiecesMemory pmem = new PiecesMemory(new LinkedList<Piece>());
            JsonWriter writer = new JsonWriter(ComposerConstants.getFilePath() + "/EmptyPmemTest.json");
            writer.open();
            writer.write(pmem);
            writer.close();

            JsonReader reader = new JsonReader(ComposerConstants.getFilePath() + "/EmptyPmemTest.json");
            PiecesMemory pmem1 = reader.read();
            assertEquals("", pmem1.getPieceNames());
            assertEquals(0, pmem1.numSavedPieces());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterPiecesMemoryWithPieces() {
        try {
            PiecesMemory pmem = new PiecesMemory(new LinkedList<Piece>());
            Test.addNote(C5quarter);
            Test.addNote(D0quarter);
            Test.addNote(Rest5half);
            Test.addNote(B6flathalf);
            pmem.addPiece(Test);
            Test1.addNote(D0quarter);
            Test1.addNote(F4sharpwhole);
            pmem.addPiece(Test1);
            JsonWriter writer = new JsonWriter(ComposerConstants.getFilePath() + "/PmemTest.json");
            writer.open();
            writer.write(pmem);
            writer.close();

            JsonReader reader = new JsonReader(ComposerConstants.getFilePath() + "/PmemTest.json");
            PiecesMemory pmem1 = reader.read();
            assertEquals("Test, Test1, ", pmem1.getPieceNames());
            assertEquals(2, pmem1.numSavedPieces());
            assertEquals(4, pmem1.getPieceWithName("Test").length());
            assertEquals(2, pmem1.getPieceWithIndex(1).length());
            assertEquals(1.5, pmem1.getPieceWithIndex(0).pieceDuration());
            assertEquals("D0/0.25 F#4/1.0 ", pmem1.getPieceWithIndex(1).pieceToString());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

