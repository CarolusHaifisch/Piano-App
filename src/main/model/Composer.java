package model;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.*;


/** Class representing the methods used for logic and computations used in the UI class
 * but are not directly linked to user inputs such as methods to read and extract information from memory.
 */
public class Composer {

    // EFFECTS: Returns true if memory file already exists at given pathname, false otherwise.
    public boolean memExists(String pathname) {
        File memorypath = new File(pathname);
        return memorypath.isFile();
    }

    // MODIFIES: memory file at pathname filepath
    // EFFECTS: Creates a new Pieces_memory file to save serialized PiecesMemory data if it doesn't already exist
    // and returns the PiecesMemory, otherwise retrieves the saved PiecesMemory from the file.
    public PiecesMemory memRetrieve(String pathname) throws IOException {
        JsonReader reader = new JsonReader(pathname);
        return reader.read();
    }

    // MODIFIES: memory file at pathname filepath
    // EFFECTS: Saves current PiecesMemory to the memory file at pathname path.
    // This will overwrite whatever was originally in the memory file.
    public void memSave(PiecesMemory currentPMem, String pathname) throws IOException {
        JsonWriter writer = new JsonWriter(pathname);
        writer.open();
        writer.write(currentPMem);
        writer.close();
    }
}

