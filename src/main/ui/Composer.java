package ui;

import java.util.Scanner;
import org.jfugue.player.Player;

public class Composer {
    // Add notes to piece until program interrupted, then save piece
    // (Add x to y arbitrarily)
    // Allow for changing instruments halfway through piece (with keyboard)
    // Maybe use abstract classes and extensions to represent tempos and such???
    // Archive saved pieces and retrieve by a string name

    /**
     * For composing notes: If char name given is not in notesList in NoteConstants give a system print out
     * saying invalid note! and asks for user to try again (This can be done with some sort of loop?)
     * <p>
     * - For composing a piece: use some loop to loop through the composing code for notes, add each composed note
     * to the piece, until user is done at which point a keyboard press will break the loop and finish the piece
     * <p>
     * Users will be able to edit any piece they have saved to the memory, and will be able to play back any piece
     * in memory with JFugue.
     */

    // Constructs new composer instance
    // EFFECTS: Initializes a new Composer in the console
    public Composer() {
        System.out.println("Welcome to the Composer V0.1. To compose a new piece press N, to edit an existing piece in"
                + "memory press E, to play a piece from memory press P, and to exit the program press W.");
        switch//Implement a keyboard keyinstance switch case here one per each of the outlined cases above.
    }
        public static void main (String[]args){
            new Composer();

        }
    }


