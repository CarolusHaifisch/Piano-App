package ui;

import model.*;
import org.jfugue.player.Player;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ComposerUI {
    /** Googled certain components such as serialization of list data and scanner to read console inputs for this class.
     * Read some documentation on how to write and retrieve data stored in a serialization, and read particularly on
     * how to use the scanner methods to obtain the desired data from console inputs.
     */
    private Composer composer = new Composer(); // Necessary to avoid the non static/static error (See documentation)
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
                + "of the piece, enter P. Enter any other key to return to menu. All inputs are case sensitive.");
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

    // REQUIRES: index must be a valid index of the piece (index value given cannot exceed maximum index of piece)
    // TODO: Add an if block to check against case when index exceeds max index of piece.
    // MODIFIES: piece
    // EFFECTS: Allows for deleting notes at given indices, or returns to composerMenu. Prints out current piece
    // contents to console before each note deletion.
    private void noteDelete(Piece piece) {
        System.out.println("Enter index of note to be deleted");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        s.nextLine();
        System.out.println("Deleted note " + piece.getNoteAtIndex(i).noteToString());
        piece.delNote(i);
        System.out.println("Delete another note? If yes, enter D, enter any other key to return to menu.");
        char d = s.nextLine().charAt(0);
        if (d == 'D') {
            noteDelete(piece);
        } else {
            this.composerMenu();
        }
    }

    // REQUIRES: name input is a name of a piece in memory
    // MODIFIES: PiecesMemory memory
    // EFFECTS: Returns the piece with the given name, if none exist a new piece is created and added to memory.
    private Piece pieceSelect() {
        System.out.println("Please provide the name of the piece you want to edit");
        Scanner piecenameinput = new Scanner(System.in);
        String pieceName = piecenameinput.nextLine();
        // Code to search through PiecesMemory to find piece with said name: If none exist.....
        System.out.println("Editing " + pieceName);
        return memory.getPieceWithName(pieceName);
    }

    // REQUIRES: Given index is a valid index of memory. (Does not exceed index of memory)
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
        if (pieceindex < memory.numSavedPieces()) {  //TODO THis ifelse block will need to be moved to model Composer
            System.out.println("Deleted piece " + memory.getPieceWithIndex(pieceindex).getPieceName());
            memory.delPiece(pieceindex);
        } else {
            System.out.println("Invalid index, please try again!");
            this.dmethod();
        }
        this.composerMenu();
    }

    // EFFECTS: Plays the given piece in JFugue. If no piece of given name exists, an empty piece is added to
    // memory and is played. Also prints the piece's contents as a string to console.
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

    // EFFECTS: Tries to save piecesmemory to the serialized file, and then exits the program.
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
    // EFFECTS: Composes a piece by adding notes to the piece until user finishes the piece
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
