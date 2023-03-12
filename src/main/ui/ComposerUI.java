package ui;

import exception.PieceNotFoundException;
import model.*;
import org.jfugue.player.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class representing the main Composer UI. Contains all the methods accepting console inputs and printing
 * to console. If a different saved file is desired, change the filepath in the ComposerConstants class. By default
 * the save file location is given in the ComposerConstants class as FileDirectory.
 * Upon starting the program the saved file will automatically attempt to be loaded, if none exist it will create a new
 * LOCAL memory. You can save and load manually later in the program if you desire.
 */
public class ComposerUI {

    private final Composer composer = new Composer();
    PiecesMemory memory;
    String pieceName;

    {
        try {
            if (composer.memExists(ComposerConstants.getFileDirectory())) {
                memory = composer.memRetrieve(ComposerConstants.getFileDirectory());
            } else {
                memory = new PiecesMemory(new LinkedList<>());
            }
        } catch (IOException e) {
            System.out.println("Could not retrieve memory.");
        }
    }

    // EFFECTS: Initializes a new Composer instance in the console
    public ComposerUI() {
        System.out.println("Welcome to the Composer V0.1-Alpha.");
        this.composerInterface();
    }

    // EFFECTS: Takes in a char input and progresses to the corresponding methods depending on input.
    // Acts as a 'main menu' of sorts for the program.
    private void composerInterface() {
        System.out.println(ComposerConstants.getMenuInstructions());
        Scanner input = new Scanner(System.in);
        char keyInput = input.nextLine().charAt(0);
        composerMenu(keyInput);
    }

    // EFFECTS: Takes user to corresponding function of program depending on user input.
    private void composerMenu(char keyInput) {
        switch (keyInput) {
            case 'L': {
                this.lmethod();
            }
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
                this.cmethod();
            }
            default: {
                System.exit(0);
            }
        }
    }

    // EFFECTS: Loads Pieces from saved JSON memory in the JSON file to the local session memory PiecesMemory, and
    // returns to the main menu.
    private void lmethod() {
        try {
            memory = composer.memRetrieve(ComposerConstants.getFileDirectory());
            this.composerInterface();
        } catch (IOException e) {
            System.out.println("Could not retrieve memory.");
            this.composerInterface();
        }
    }

    // EFFECTS: Links pieceComposer to pieceSelect, so that the selected piece is the one that is being composed.
    // If piece is not found creates a new piece with given name and edits that piece.
    // Prints out list of piece names currently in session memory to console.
    // Also allows for note deletion after finishing composing the piece.
    private void emethod() {
        System.out.println("Current pieces in memory: " + memory.getPieceNames());
        try {
            Piece piece = this.pieceSelect();
            this.pieceEditor(piece);
        } catch (PieceNotFoundException pnfe) {
            Piece piece = new Piece(pieceName, new ArrayList<Note>());
            memory.addPiece(piece);
            this.pieceEditor(piece);
        }

        this.composerInterface();
    }

    // EFFECTS: Edits the selected piece.
    private void pieceEditor(Piece piece) {
        System.out.println("Current piece: " + piece.pieceToString());
        System.out.println("To add to the piece, enter A. To delete notes from the piece, enter D. To view properties"
                + " of the piece, enter P. Enter any other key to return to menu.");
        Scanner sc = new Scanner(System.in);
        char einput = sc.nextLine().charAt(0);
        switch (einput) {
            case 'A': {
                this.pieceComposer(piece);
                this.composerInterface();
            }
            case 'D': {
                this.noteDelete(piece);
                this.composerInterface();
            }
            case 'P': {
                System.out.println("Length of piece in number of notes: " + piece.length());
                System.out.println("Entire duration of piece in number of beats: " + piece.pieceDuration());
                this.pieceEditor(piece);
            }
            default : {
                this.composerInterface();
            }
        }
    }

    // REQUIRES: index given must be a valid index of the piece (given index < piece size), index >=0
    // MODIFIES: piece
    // EFFECTS: Allows for deleting notes at given indices, or returns to piece editor menu. Prints out current piece
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
            this.pieceEditor(piece);
        }
    }

    // MODIFIES: PiecesMemory memory
    // EFFECTS: Returns the piece with the given name, if none exist a new piece is created and added to session memory,
    // and then returned.
    private Piece pieceSelect() throws PieceNotFoundException {
        System.out.println("Please provide the name of the piece you want to edit. Input another name to create a new"
                + "Piece with that name.");
        Scanner piecenameinput = new Scanner(System.in);
        pieceName = piecenameinput.nextLine();
        System.out.println("Editing " + pieceName);
        return memory.getPieceWithName(pieceName);
    }

    // REQUIRES: Given index is a valid index of session memory. (given index < memory size) , index >=0
    // MODIFIES: PiecesMemory memory
    // EFFECTS: Deletes piece from session memory with given index. If given index falls outside of valid indices,
    // prints error message and you can try again.
    private void dmethod() {
        System.out.println("Please provide the index of the piece you want to delete from memory. Note pieces are"
                + " indexed in the order shown below with left being index 0 and increasing to the right.");
        System.out.println("Current pieces in memory are: " + memory.getPieceNames());
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
        this.composerInterface();
    }

    // EFFECTS: Plays the given piece in JFugue. If no piece of given name exists, returns to main menu.
    // Also prints the piece's contents as a string to console.
    private void pmethod() {
        System.out.println("Please provide the name of the piece you want to play with JFugue");
        System.out.println("Current pieces in memory are: " + memory.getPieceNames());
        Scanner inputp = new Scanner(System.in);
        String pieceName = inputp.nextLine();
        Player piecePlayer = new Player();  // Creates new JFugue Player
        try {
            Piece selectedPiece = memory.getPieceWithName(pieceName);
            String pieceString = selectedPiece.pieceToString();
            System.out.println("Piece being played: " + pieceName + " " + pieceString);
            piecePlayer.play(pieceString);
        } catch (PieceNotFoundException pnfe) {
            System.out.println("Piece does not exist in memory, try again.");
            this.pmethod();
        }
        System.out.println("Input Y to play another piece, or any other key to return to menu");
        Scanner inputp2 = new Scanner(System.in);
        char inputval = inputp2.nextLine().charAt(0);
        if (inputval == 'Y') {
            this.pmethod();
        } else {
            this.composerInterface();
        }
    }

    // MODIFIES: Json memory file
    // EFFECTS: Tries to save PiecesMemory memory to the Json file, and then exits the program. If save fails, prints
    // error message and returns to menu.
    private void wmethod() {
        try {
            composer.memSave(memory, ComposerConstants.getFileDirectory());
        } catch (IOException e) {
            System.out.println(ComposerConstants.getSaveFailed());
            this.composerInterface();
        }
        System.out.println("Save Successful!");
        System.exit(0);
    }

    // MODIFIES: PiecesMemory memory
    // EFFECTS: Clears local memory, resetting it to a new PiecesMemory, and returns to main menu.
    private void cmethod() {
        memory = new PiecesMemory(new LinkedList<>());
        System.out.println("PiecesMemory cleared.");
        this.composerInterface();
    }

    // MODIFIES: piece
    // EFFECTS: Composes a piece by adding notes created with noteCreator to the piece until
    // user finishes the piece by entering X when prompted for the Note name. Tries to save piece after finishing.
    private void pieceComposer(Piece piece) {
        boolean isFinished = false;
        System.out.println(ComposerConstants.getPieceComposerPrompt());
        while (!isFinished) {
            System.out.println("Please enter the note name, or X to finish piece, or Q to return to editor menu."
                    + " Enter R for a rest.");
            Scanner s = new Scanner(System.in);
            char inputNoteName = s.nextLine().charAt(0);
            if (ComposerConstants.getNotesList().contains(inputNoteName)) {
                Note note = this.noteCreator(inputNoteName);
                piece.addNote(note);
                System.out.println("Piece so far: " + piece.pieceToString());
            } else if (inputNoteName == 'X') {
                isFinished = pieceComposerHelper(isFinished);
            } else if (inputNoteName == 'Q') {
                this.pieceEditor(piece);
            } else {
                System.out.println("Invalid note entered, please try again");
            }
        }
    }

    // MODIFIES: piece
    // EFFECTS: Handles the case when the piece is finished, saves the finished piece and PiecesMemory to the JSON file
    private boolean pieceComposerHelper(boolean isFinished) {
        System.out.println("Piece has been finished. Saving...");
        isFinished = true;
        try {
            composer.memSave(memory, ComposerConstants.getFileDirectory());
        } catch (IOException e) {
            System.out.println(ComposerConstants.getSaveFailed());
        }
        System.out.println("Save Successful!");
        return isFinished;
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
            case 'S': {
                return new Note(inputNoteName, inputNoteDuration, inputNoteOctave, true, false);
            }
            case 'F': {
                return new Note(inputNoteName, inputNoteDuration, inputNoteOctave, false, true);
            }
        }
        return new Note(inputNoteName, inputNoteDuration, inputNoteOctave, false, false);
    }

    public static void main(String[] args) {
        new ComposerUI();
    }
}
