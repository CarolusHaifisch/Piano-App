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


    // MODIFIES: Pieces_memory file in /data folder.
    // EFFECTS: Saves current PiecesMemory to the Pieces_memory file in /data. Prints a string to screen indicating
    // whether save was successful or not. This will overwrite whatever was originally in the Pieces_memory file.

    // Move this to the Composer class in Model, and add something to catch the exception here and print the success
    // statement if successful somewhere in the UI body.
    public void memSave(PiecesMemory currentPMem) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(NoteConstants.getFilePath() + "Pieces_memory");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(currentPMem);
            fos.close();
            oos.close();
            System.out.println("Save Successful!");
        } catch (IOException) {
            System.out.println("Did not save properly.");
        }
    }

    // Constructs new composer instance
    // EFFECTS: Initializes a new Composer in the console

    public ComposerUI() {
        System.out.println("Welcome to the Composer V0.1-Alpha. To compose a new piece press N, to edit an existing" +
                "piece in memory press E, to play a piece from memory press P, and to exit the program press W.");
        // Need to use the scanner to detect char inputs
        Scanner input = new Scanner(System.in);
        char keyInput = input.nextLine().charAt(0);
        Composer.memExists();
        switch (keyInput) {
            case 'N': {
                Piece newPiece = new Piece();
                // Need code to start composing new piece after creating the new blank piece
                // IE move to a different UI method to edit the piece
            }
            case 'E': {
                System.out.println("Please provide the name of the piece you want to edit");
                Scanner inpute = new Scanner(System.in);
                String pieceName = inpute.nextLine();
                // Code to search through PiecesMemory to find piece with said name: If none exist.....
                // Then move to a different UI method to handle editing of the piece

            }
            case 'P': {
                System.out.println("Please provide the name of the piece you want to play with JFugue");
                Scanner inputp = new Scanner(System.in);
                String pieceName = inputp.nextLine();

                Player piecePlayer = new Player();  // Creates new JFugue Player
                piecePlayer.play();
                // Code to play piece in Jfugue. If none exist with given name in memory ...........
            }
            case 'W': {
                System.exit(0);
            }
        }
    }

    public static void main(String[]args) {
        new ComposerUI();

    }
}


