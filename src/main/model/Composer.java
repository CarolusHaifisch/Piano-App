package model;

import java.io.*;

public class Composer {
    // EFFECTS: Returns true if memory file already exists, false otherwise.
    private boolean memExists() {
        File memorypath = new File(NoteConstants.getFilePath());
        return memorypath.isFile();
    }

    // MODIFIES: Pieces_memory file in /data folder
    // EFFECTS: Creates a new Pieces_memory file to save PiecesMemory data if it doesn't already exist and returns the
    // PiecesMemory, otherwise retrieves the saved PiecesMemory from the file.
    public PiecesMemory memRetrieve() throws IOException, ClassNotFoundException {
        if (memExists()) {
            FileInputStream fis = new FileInputStream(NoteConstants.getFilePath() + "Pieces_memory");
            ObjectInputStream ois = new ObjectInputStream(fis);
            PiecesMemory memory = (PiecesMemory) ois.readObject();
            fis.close();
            ois.close();
            return memory;
        }
        FileOutputStream fos = new FileOutputStream(NoteConstants.getFilePath() + "Pieces_memory");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        PiecesMemory newMemory = new PiecesMemory();
        oos.writeObject(newMemory);
        fos.close();
        oos.close();
        FileInputStream fis = new FileInputStream(NoteConstants.getFilePath() + "Pieces_memory");
        ObjectInputStream ois = new ObjectInputStream(fis);
        PiecesMemory memory = (PiecesMemory) ois.readObject();
        fis.close();
        ois.close();
        return memory;
    }

/*
    int keyCode =
            switch(keyCode) {
                //Implement a keyboard keyinstance switch case here one per each of the outlined cases above.
                // NOTE: THIS WILL NOT WORK. KEYEVENT ETC IS ONLY FOR SWING AND GUIS!!!!


                // ********************************************************
                // Do I even need this class? The SpaceInvaders has something like this but I could fit everything that
                // would go here into the ComposerUI class, I'll leave this here for now
                // ***********************************************************
                case
            }

 */
}
