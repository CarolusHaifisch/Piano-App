package model;

import java.io.*;
import java.util.LinkedList;

public class Composer {
    // EFFECTS: Returns true if memory file already exists, false otherwise.
    // pathname = ComposerConstants.getFilePath() + "/Pieces_memory.ser"
    public boolean memExists(String pathname) {
        File memorypath = new File(pathname);
        return memorypath.isFile();
    }

    // MODIFIES: Pieces_memory file in /data folder
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

    // MODIFIES: Pieces_memory file in /data folder.
    // EFFECTS: Saves current PiecesMemory to the Pieces_memory file in /data.
    // This will overwrite whatever was originally in the Pieces_memory file.
    public void memSave(PiecesMemory currentPMem, String pathname) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathname);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(currentPMem);
        fos.close();
        oos.close();
    }
}
