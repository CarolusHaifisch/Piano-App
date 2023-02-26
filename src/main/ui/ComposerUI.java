package ui;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

import model.*;
import org.jfugue.player.Player;

public class ComposerUI {
    private Composer composer = new Composer(); // Necessary to avoid the non static/static error (See documentation)
    PiecesMemory memory;

    {
        try {
            memory = composer.memRetrieve();
        } catch (IOException e) {
            System.out.println("Could not retrieve memory.");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find PiecesMemory class.");
        }
    }
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
        System.out.println("Welcome to the Composer V0.1-Alpha.");
        this.composerMenu();
    }

    // EFFECTS: Takes in a char input and completes the corresponding action depending on input.
    private void composerMenu() {
        System.out.println(NoteConstants.getMenuInstructions());
        Scanner input = new Scanner(System.in);
        char keyInput = input.nextLine().charAt(0);
        switch (keyInput) {
            case 'E': {
                Piece piece = this.emethod();
                // Need code to start composing new piece after creating the new blank piece
                // Then move to a different UI method to handle editing of the piece
            }
            case 'D': {
                this.dmethod();
            }
            case 'P': {
                this.pmethod();
            }
            case 'W': {
                this.wmethod();
            }
            case 'C': {
                memory = new PiecesMemory();
                System.out.println("PiecesMemory cleared.");
            }
        }
    }

    // REQUIRES: name input is a name of a piece in memory
    // MODIFIES: PiecesMemory memory
    // EFFECTS: Returns the piece with the given name, if none exist a new piece is created and added to memory.
    private Piece emethod() {
        System.out.println("Please provide the name of the piece you want to edit");
        Scanner inpute = new Scanner(System.in);
        String pieceName = inpute.nextLine();
        // Code to search through PiecesMemory to find piece with said name: If none exist.....
        System.out.println("Editing " + pieceName);
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
            System.out.println("Deleted piece " + memory.getPieceWithIndex(pieceindex).getPieceName());
        } else {
            System.out.println("Invalid index, please try again!");
            this.dmethod();
        }
        this.composerMenu();
    }

    // REQUIRES: name input is a name of a piece in memory
    // EFFECTS: Plays the given piece in JFugue. If no piece of given name exists, an empty piece is played.
    private void pmethod() {
        System.out.println("Please provide the name of the piece you want to play with JFugue");
        Scanner inputp = new Scanner(System.in);
        String pieceName = inputp.nextLine();
        Player piecePlayer = new Player();  // Creates new JFugue Player
        Piece selectedPiece = memory.getPieceWithName(pieceName);
        String pieceString = selectedPiece.pieceToString();
        piecePlayer.play(pieceString);
        System.out.println("Input Y to play another piece, or N to return to menu");
        Scanner inputp2 = new Scanner(System.in);
        char inputval = inputp2.nextLine().charAt(0);
        if (inputval == 'Y') {
            this.pmethod();
        } else if (inputval == 'N') {
            this.composerMenu();
        }
        // Code to play piece in Jfugue. If none exist with given name in memory ...........
    }

    // EFFECTS: Tries to save piecesmemory to the serialized file, and then exits the program.
    private void wmethod() {
        try {
            composer.memSave(memory);
        } catch (IOException e) {
            System.out.println(NoteConstants.getSaveFailed());
        }
        System.exit(0);
    }

    // MODIFIES: piece
    // EFFECTS: Composes a piece by adding notes to the piece until user finishes the piece
    private void pieceComposer(Piece piece) {
        boolean isFinished = false;
        System.out.println("Please input the note parameters when prompted to add a note to the piece. When finished,"
                + "enter X in place of noteName to finish piece and save the memory.");
        while (!isFinished) {
            Scanner s = new Scanner(System.in);
            char inputNoteName = s.nextLine().charAt(0);
            if (NoteConstants.getNotesList().contains(inputNoteName)) {
                Note note = this.noteCreator(inputNoteName);
                piece.addNote(note);
            } else if (inputNoteName == 'X') {
                System.out.println("Piece has been finished. Saving....");
                isFinished = true;
                try {
                    composer.memSave(memory);
                } catch (IOException e) {
                    System.out.println(NoteConstants.getSaveFailed());
                }
            } else {
                System.out.println("Invalid note entered, please try again");
                inputNoteName = s.nextLine().charAt(0);
            }
        }
    }

    // ****************************************************
    private Note noteCreator(char inputNoteName) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the duration of the note.");
        double inputNoteDuration = s.nextDouble();
        s.nextLine();
        System.out.println("Please enter the octave of the note.");
        int inputNoteOctave = s.nextInt();
        s.nextLine();
        System.out.println("Is this note a sharp, flat, or natural?"
                + " Enter S for sharp, F for flat, any other input for natural");
        char inputNoteIdentity = s.nextLine().charAt(0);
        switch (inputNoteIdentity) {
            case 'S' : {
                return new Note(inputNoteName, inputNoteDuration, inputNoteOctave, true, false);
            }
            case 'F' : {
                return new Note(inputNoteName, inputNoteDuration, inputNoteOctave, false, true);
            }
        }
        return new Note(inputNoteName, inputNoteDuration, inputNoteOctave, false, false);
    }

    public static void main(String[]args) {
        new ComposerUI();

    }
}


// TODO: NEED TO ADD METHODS TO GET THE CURRENT LIST OF PIECES IN PIECESMEMORY AND PRINTS OUT THEIR NAMES TO CONSOLE
// TODO: Same for the piece, to get list of notes in piece and print it out to console