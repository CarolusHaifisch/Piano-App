package ui;

import java.util.Scanner;

public class Composer {
    public static void main(String[] args) {

        // Add notes to piece until program interrupted, then save piece
        // (Add x to y arbitrarily)
        // Allow for changing instruments halfway through piece (with keyboard)
        // Maybe use abstract classes and extensions to represent tempos and such???
        // Archive saved pieces and retrieve by a string name

        /** For composing notes: If char name given is not in notesList in NoteConstants give a system print out
         * saying invalid note! and asks for user to try again (This can be done with some sort of loop?)
         *
         * - For composing a piece: use some loop to loop through the composing code for notes, add each composed note
         * to the piece, until user is done at which point a keyboard press will break the loop and finish the piece
         *
         * Users will be able to edit any piece they have saved to the memory, and will be able to play back any piece
         * in memory with JFugue.
         */
    }
}

