package model;

import java.util.ArrayList;
import static java.util.Arrays.asList;

public class NoteConstants {
    /** Class containing all common constants used throughout the program.
     */
    ArrayList<Character> notesList = new ArrayList<Character>(asList('C','D','E','F','G','A','B','R'));
    private static final double MAX_DURATION = 10;
    private static final int DEFAULT_TEMPO = 80;
    private static final int MIN_TEMPO = 40;
    private static final int MAX_TEMPO = 220;

}
