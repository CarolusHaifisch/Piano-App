package ui;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Composer;
import model.NoteConstants;
import model.Piece;
import model.PiecesMemory;
import org.jfugue.player.Player;

public class ComposerUI {
    private Composer composer = new Composer(); // Necessary to avoid the non static/static error (See documentation)
    PiecesMemory memory = composer.memRetrieve(); // UNHANDLED EXCEPTIONS!!! NEED TO FIX W TRY CATCH
    /** Googled certain components such as serialization of list data and scanner to read console inputs for this class.
     * Read some documentation on how to write and retrieve data stored in a serialization.
     */

    // Add notes to piece until program interrupted, then save piece
    // (Add x to y arbitrarily)
    // Allow for changing instruments halfway through piece (with keyboard)
    // Maybe use abstract classes and extensions to represent tempos and such???
    // Archive saved pieces and retrieve by a string name

    /**
     * For composing notes: If char name given is not in notesList in NoteConstants give a system print out
     * saying invalid note! and asks for user to try again (This can be done with some sort of loop?)
     *
     * - For composing a piece: use some loop to loop through the composing code for notes, add each composed note
     * to the piece, until user is done at which point a keyboard press will break the loop and finish the piece
     *
     * NOTE: To save the list of pieces to a permanent memory it must be written to a file. Then when the program is
     * started again it must be retrieved from said file. Otherwise any locally stored memory will be deleted when the
     * program is quit. NOTE:: SERIALIZATION IS MORE EFFICIENT FOR ARRAYLISTS THAN CONVERTING TO TEXT FILE!!!
     *
     * Users will be able to edit any piece they have saved to the memory, and will be able to play back any piece
     * in memory with JFugue.
     */



    // Constructs new composer instance
    // EFFECTS: Initializes a new Composer in the console

    public ComposerUI() {
        System.out.println("Welcome to the Composer V0.1-Alpha. To compose a new piece press N, to edit an existing"
                + "piece in memory press E, to delete a piece from memory press D, to play a piece from memory press P,"
                + " and to save Pieces and exit the program press W.");
        // Need to use the scanner to detect char inputs
        Scanner input = new Scanner(System.in);
        char keyInput = input.nextLine().charAt(0);
        switch (keyInput) {
            case 'N': {
                Piece piece = new Piece();
                // Need code to start composing new piece after creating the new blank piece
                // IE move to a different UI method to edit the piece
            }
            case 'E': {
                Piece piece = this.emethod();
                System.out.println("Editing" + piece.getPieceName());
                // Then move to a different UI method to handle editing of the piece
            }
            case 'D': {
                this.dmethod();
            }
            case 'P': {
                this.pmethod();
            }
            case 'W': {
                try {
                    composer.memSave(memory);
                } catch (IOException e) {  // This might not even be necessary because IOexceptions shouldn't be thrown
                    // in normal circumstances.
                    System.out.println("Did not save properly.");
                }
                System.exit(0);
            }
        }
    }

    // MODIFIES: PiecesMemory memory
    // EFFECTS: Returns the piece with the given name, if none exist a new piece is created and added to memory.
    private Piece emethod() {
        System.out.println("Please provide the name of the piece you want to edit");
        Scanner inpute = new Scanner(System.in);
        String pieceName = inpute.nextLine();
        // Code to search through PiecesMemory to find piece with said name: If none exist.....
        return memory.getPieceWithName(pieceName);
    }

    // REQUIRES: Given index is a valid index of memory. (Does not exceed index of memory)
    // MODIFIES: PiecesMemory memory
    // EFFECTS: Deletes piece from memory with given index. If given index falls outside of valid indices,
    // prints error message and you can try again.
    private void dmethod() {
        System.out.println("Please provide the index of the piece you want to delete from memory");
        Scanner inputd = new Scanner(System.in);
        // Code to search through PiecesMemory to find piece with said name: If none exist.....
        // Then move to a different UI method to handle editing of the piece
        while (!inputd.hasNextInt()) {
            inputd.next();
        }
        int pieceindex = inputd.nextInt();
        if (pieceindex < memory.numSavedPieces()) {
            memory.delPiece(pieceindex);
            System.out.println("Deleted piece" + memory.getPieceWithIndex(pieceindex).getPieceName());
        } else {
            System.out.println("Invalid index, please try again!");
            this.dmethod();
        }
    }

    // EFFECTS: Plays the given piece in JFugue. If no piece of given name exists, an empty piece is played.
    private void pmethod() {
        System.out.println("Please provide the name of the piece you want to play with JFugue");
        Scanner inputp = new Scanner(System.in);
        String pieceName = inputp.nextLine();
        Player piecePlayer = new Player();  // Creates new JFugue Player
        Piece selectedPiece = memory.getPieceWithName(pieceName);
        piecePlayer.play(selectedPiece.getPieceContents());
        // ABOVE LINE WILL NOT WORK AS IS. WILL NEED TO CONVERT THE ARRAYLIST TO A STRING AND
        // JOIN ALL THE NOTES WITH SPACES.
        // TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Code to play piece in Jfugue. If none exist with given name in memory ...........


    }

    public static void main(String[]args) {
        new ComposerUI();

    }
}


