package model;

import java.io.*;
import java.util.LinkedList;

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
    public PiecesMemory memRetrieve(String pathname) throws IOException, ClassNotFoundException {
        if (memExists(pathname)) {
            FileInputStream fis = new FileInputStream(pathname);
            ObjectInputStream ois = new ObjectInputStream(fis);
            PiecesMemory memory = (PiecesMemory) ois.readObject();
            fis.close();
            ois.close();
            return memory;
        }
        FileOutputStream fos = new FileOutputStream(pathname);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        PiecesMemory newMemory = new PiecesMemory(new LinkedList<Piece>());
        oos.writeObject(newMemory);
        fos.close();
        oos.close();
        FileInputStream fis = new FileInputStream(pathname);
        ObjectInputStream ois = new ObjectInputStream(fis);
        PiecesMemory memory = (PiecesMemory) ois.readObject();
        fis.close();
        ois.close();
        return memory;
    }

    // MODIFIES: memory file at pathname filepath
    // EFFECTS: Saves current PiecesMemory to the memory file at pathname path.
    // This will overwrite whatever was originally in the memory file.
    public void memSave(PiecesMemory currentPMem, String pathname) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(currentPMem);
        fos.close();
        oos.close();
    }
}

