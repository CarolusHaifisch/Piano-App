package model;

import ui.ComposerConstants;

import java.io.*;

public class Composer {
    // EFFECTS: Returns true if memory file already exists, false otherwise.
    public boolean memExists() {
        File memorypath = new File(ComposerConstants.getFilePath() + "/Pieces_memory.ser");
        return memorypath.isFile();
    }

    // MODIFIES: Pieces_memory file in /data folder
    // EFFECTS: Creates a new Pieces_memory file to save serialized PiecesMemory data if it doesn't already exist
    // and returns the PiecesMemory, otherwise retrieves the saved PiecesMemory from the file.
    public PiecesMemory memRetrieve() throws IOException, ClassNotFoundException {
        if (memExists()) {
            FileInputStream fis = new FileInputStream(ComposerConstants.getFilePath() + "/Pieces_memory.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            PiecesMemory memory = (PiecesMemory) ois.readObject();
            fis.close();
            ois.close();
            return memory;
        }
        FileOutputStream fos = new FileOutputStream(ComposerConstants.getFilePath() + "/Pieces_memory.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        PiecesMemory newMemory = new PiecesMemory();
        oos.writeObject(newMemory);
        fos.close();
        oos.close();
        FileInputStream fis = new FileInputStream(ComposerConstants.getFilePath() + "/Pieces_memory.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        PiecesMemory memory = (PiecesMemory) ois.readObject();
        fis.close();
        ois.close();
        return memory;
    }

    // MODIFIES: Pieces_memory file in /data folder.
    // EFFECTS: Saves current PiecesMemory to the Pieces_memory file in /data.
    // This will overwrite whatever was originally in the Pieces_memory file.
    public void memSave(PiecesMemory currentPMem) throws IOException {
        FileOutputStream fos = new FileOutputStream(ComposerConstants.getFilePath() + "/Pieces_memory.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(currentPMem);
        fos.close();
        oos.close();
    }
}
