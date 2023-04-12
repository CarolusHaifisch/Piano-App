package model;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.*;


/** Class representing the methods used for logic and computations used in the UI class
 * but are not directly linked to user inputs such as methods to read and extract information from memory.
 */
public class Composer {
    private Event loadEvent;
    private Event saveEvent;
    private static JsonReader reader;
    private static JsonWriter writer;

    // EFFECTS: Returns true if memory file already exists at given pathname, false otherwise.
    public boolean memExists(String pathname) {
        File memorypath = new File(pathname);
        return memorypath.isFile();
    }

    // EFFECTS: Retrieves the saved PiecesMemory from the file.
    public PiecesMemory memRetrieve(String pathname) throws IOException {
        reader = new JsonReader(pathname);
        loadEvent = new Event("Saved memory loaded. \n");
        EventLog.getInstance().logEvent(loadEvent);
        return reader.read();
    }

    // MODIFIES: memory file at pathname filepath
    // EFFECTS: Saves current PiecesMemory to the memory file at pathname path.
    // This will overwrite whatever was originally in the memory file.
    public void memSave(PiecesMemory currentPMem, String pathname) throws IOException {
        writer = new JsonWriter(pathname);
        writer.open();
        writer.write(currentPMem);
        writer.close();
        saveEvent = new Event("Memory saved to JSON file. \n");
        EventLog.getInstance().logEvent(saveEvent);
    }
}

