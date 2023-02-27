package ui;

import model.*;
import org.jfugue.player.Player;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/** Class representing the main Composer UI. Contains all the methods accepting console inputs and printing
 * to console.
 */
public class ComposerUI {
    /** Googled certain components such as serialization of list data and scanner to read console inputs for this class.
     * Read some documentation on how to write and retrieve data stored in a serialization, and read particularly on
     * how to use the scanner methods to obtain the desired data from console inputs.
     */
    private Composer composer = new Composer();
    PiecesMemory memory;

    {
        try {
            memory = composer.memRetrieve(ComposerConstants.getFilePath() + "/Pieces_memory.ser");
        } catch (IOException e) {
            System.out.println("Could not retrieve memory.");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find PiecesMemory class.");
        }
    }

    // EFFECTS: Initializes a new Composer instance in the console
    public ComposerUI() {
        System.out.println("Welcome to the Composer V0.1-Alpha.");
        this.composerMenu();
    }

    // EFFECTS: Takes in a char input and progresses to the corresponding methods depending on input.
    // Acts as a 'main menu' of sorts for the program.
    private void composerMenu() {
        System.out.println(ComposerConstants.getMenuInstructions());
        Scanner input = new Scanner(System.in);
        char keyInput = input.nextLine().charAt(0);
        switch (keyInput) {
            case 'E': {
                this.emethod();
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
                memory = new PiecesMemory(new LinkedList<Piece>());
                System.out.println("PiecesMemory cleared.");
                this.composerMenu();
            }
        }
    }

    // EFFECTS: Links pieceComposer to pieceSelect, so that the selected piece is the one that is being composed.
    // Prints out list of piece names currently in memory to console.
    // Also allows for note deletion after finishing composing the piece.
    private void emethod() {
        System.out.println("Current pieces in memory: " + memory.getPieceNames());
        Piece piece = this.pieceSelect();
        System.out.println("Current piece: " + piece.pieceToString());
        System.out.println("To add to the piece, enter A. To delete notes from the piece, enter D. To view properties"
                + "of the piece, enter P. Enter any other key to return to menu.");
        Scanner sc = new Scanner(System.in);
        char einput = sc.nextLine().charAt(0);
        switch (einput) {
            case 'A': {
                this.pieceComposer(piece);
                this.composerMenu();
            }
            case 'D': {
                this.noteDelete(piece);
                this.composerMenu();
            }
            case 'P': {
                System.out.println("Length of piece in number of notes: " + piece.length());
                System.out.println("Entire duration of piece in number of beats: " + piece.pieceDuration());
            }
        }
        this.composerMenu();
    }

    // REQUIRES: index given must be a valid index of the piece (given index < piece size), index >=0
    // MODIFIES: piece
    // EFFECTS: Allows for deleting notes at given indices, or returns to composerMenu. Prints out current piece
    // contents to console before each note deletion. If invalid index entered, prints error message
    // and user can try again.
    private void noteDelete(Piece piece) {
        System.out.println("Enter index of note to be deleted");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        if (i < piece.length()) {
            System.out.println("Deleted note " + piece.getNoteAtIndex(i).noteToString());
            piece.delNote(i);
        } else {
            System.out.println("Invalid index, please try again!");
            noteDelete(piece);
        }
        System.out.println("Delete another note? If yes, enter D, enter any other key to return to menu.");
        char d = s.nextLine().charAt(0);
        if (d == 'D') {
            noteDelete(piece);
        } else {
            this.composerMenu();
        }
    }

    // MODIFIES: PiecesMemory memory
    // EFFECTS: Returns the piece with the given name, if none exist a new piece is created and added to memory,
    // and then returned.
    private Piece pieceSelect() {
        System.out.println("Please provide the name of the piece you want to edit");
        Scanner piecenameinput = new Scanner(System.in);
        String pieceName = piecenameinput.nextLine();
        System.out.println("Editing " + pieceName);
        return memory.getPieceWithName(pieceName);
    }

    // REQUIRES: Given index is a valid index of memory. (given index < memory size) , index >=0
    // MODIFIES: PiecesMemory memory
    // EFFECTS: Deletes piece from memory with given index. If given index falls outside of valid indices,
    // prints error message and you can try again.
    private void dmethod() {
        System.out.println("Please provide the index of the piece you want to delete from memory. Note pieces are"
                + " indexed in reverse order with last piece composed being first in order.");
        Scanner inputd = new Scanner(System.in);
        while (!inputd.hasNextInt()) {
            inputd.next();
        }
        int pieceindex = inputd.nextInt();
        if (pieceindex < memory.numSavedPieces()) {
            System.out.println("Deleted piece " + memory.getPieceWithIndex(pieceindex).getPieceName());
            memory.delPiece(pieceindex);
        } else {
            System.out.println("Invalid index, please try again!");
            this.dmethod();
        }
        this.composerMenu();
    }

    // EFFECTS: Plays the given piece in JFugue. If no piece of given name exists, an empty piece is added to
    // PiecesMemory and is played. Also prints the piece's contents as a string to console.
    private void pmethod() {
        System.out.println("Please provide the name of the piece you want to play with JFugue");
        System.out.println("Current pieces in memory are: " + memory.getPieceNames());
        Scanner inputp = new Scanner(System.in);
        String pieceName = inputp.nextLine();
        Player piecePlayer = new Player();  // Creates new JFugue Player
        Piece selectedPiece = memory.getPieceWithName(pieceName);
        String pieceString = selectedPiece.pieceToString();
        System.out.println("Piece being played: " + pieceName + " " + pieceString);
        piecePlayer.play(pieceString);
        System.out.println("Input Y to play another piece, or N to return to menu");
        Scanner inputp2 = new Scanner(System.in);
        char inputval = inputp2.nextLine().charAt(0);
        if (inputval == 'Y') {
            this.pmethod();
        } else if (inputval == 'N') {
            this.composerMenu();
        }
    }

    // EFFECTS: Tries to save PiecesMemory memory to the serialized file, and then exits the program.
    private void wmethod() {
        try {
            composer.memSave(memory, ComposerConstants.getFilePath() + "/Pieces_memory.ser");
        } catch (IOException e) {
            System.out.println(ComposerConstants.getSaveFailed());
        }
        System.out.println("Save Successful!");
        System.exit(0);
    }

    // MODIFIES: piece
    // EFFECTS: Composes a piece by adding notes created with noteCreator to the piece until
    // user finishes the piece by entering X when prompted for the Note name. Tries to save piece after finishing.
    private void pieceComposer(Piece piece) {
        boolean isFinished = false;
        System.out.println(ComposerConstants.getPieceComposerPrompt());
        while (!isFinished) {
            System.out.println("Please enter the note name, or X to finish piece. Enter R for a rest.");
            Scanner s = new Scanner(System.in);
            char inputNoteName = s.nextLine().charAt(0);
            if (ComposerConstants.getNotesList().contains(inputNoteName)) {
                Note note = this.noteCreator(inputNoteName);
                piece.addNote(note);
                System.out.println("Piece so far: " + piece.pieceToString());
            } else if (inputNoteName == 'X') {
                System.out.println("Piece has been finished. Saving...");
                isFinished = true;
                try {
                    composer.memSave(memory, ComposerConstants.getFilePath() + "/Pieces_memory.ser");
                } catch (IOException e) {
                    System.out.println(ComposerConstants.getSaveFailed());
                }
                System.out.println("Save Successful!");
            } else {
                System.out.println("Invalid note entered, please try again");
            }
        }
    }

    // EFFECTS: Prompts user for inputs for each note parameter, then constructs a Note from the given parameters.
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
