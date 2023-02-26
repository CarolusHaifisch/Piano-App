package ui;

import java.io.File;
import java.util.ArrayList;
import static java.util.Arrays.asList;

public class ComposerConstants {
    /** Class containing all common constants used throughout the program.
     */
    private static final ArrayList<Character> notesList = new ArrayList<Character>(asList('C','D','E','F','G','A','B','R'));
    private static final double MAX_DURATION = 10;  // Limit max duration of each note
    private static final int DEFAULT_TEMPO = 80;
    private static final int MIN_TEMPO = 40;
    private static final int MAX_TEMPO = 220;
    public static final int  NOTE_A = 440;
    public static final int NOTE_Asharp = 466;
    public static final int  NOTE_B = 494;
    public static final int  NOTE_C = 262;
    public static final int NOTE_Csharp = 277;
    public static final int NOTE_D = 294;
    public static final int NOTE_Dsharp = 311;
    public static final int NOTE_E = 330;
    public static final int NOTE_F = 349;
    public static final int NOTE_Fsharp = 370;
    public static final int NOTE_G = 392;
    public static final int NOTE_Gsharp = 415;

    private static String basePath = new File("").getAbsolutePath();
    private static String FilePath = basePath + "/data";

    private static String menuInstructions = "To compose a new piece or edit an existing piece in memory "
            + "enter E, to delete a piece from memory enter D, to play a piece from memory enter P,"
            + " to save Pieces and exit the program enter W, and to clear all pieces from PiecesMemory enter C.";

    private static String saveFailed = "Unable to save properly. Save unsuccessful.";

    // EFFECTS: Returns FilePath of the memory file.
    public static String getFilePath() {
        return FilePath;
    }

    // EFFECTS: Returns the menuInstructions string.
    public static String getMenuInstructions() {
        return menuInstructions;
    }

    // EFFECTS: Returns the list of valid notes notesList
    public static ArrayList<Character> getNotesList() {
        return notesList;
    }

    public static String getSaveFailed() {
        return saveFailed;
    }
}
