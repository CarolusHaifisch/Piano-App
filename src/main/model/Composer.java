package model;

import java.io.*;

public class Composer {
    // EFFECTS: Returns true if memory file already exists, false otherwise.
    public boolean memExists() {
        File memorypath = new File(NoteConstants.getFilePath()+"/Pieces_memory.ser");
        System.out.println(NoteConstants.getFilePath());
        return memorypath.isFile();
    }

    // MODIFIES: Pieces_memory file in /data folder
    // EFFECTS: Creates a new Pieces_memory file to save PiecesMemory data if it doesn't already exist and returns the
    // PiecesMemory, otherwise retrieves the saved PiecesMemory from the file.
    public PiecesMemory memRetrieve() throws IOException, ClassNotFoundException {
        if (memExists()) {
            FileInputStream fis = new FileInputStream(NoteConstants.getFilePath() + "/Pieces_memory.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            PiecesMemory memory = (PiecesMemory) ois.readObject();
            System.out.println("Successfully loaded memory");
            fis.close();
            ois.close();
            return memory;
        }
        FileOutputStream fos = new FileOutputStream(NoteConstants.getFilePath() + "/Pieces_memory.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        PiecesMemory newMemory = new PiecesMemory();
        oos.writeObject(newMemory);
        fos.close();
        oos.close();
        FileInputStream fis = new FileInputStream(NoteConstants.getFilePath() + "/Pieces_memory.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        PiecesMemory memory = (PiecesMemory) ois.readObject();
        System.out.println("New Memory created");
        fis.close();
        ois.close();
        return memory;
    }

    // MODIFIES: Pieces_memory file in /data folder.
    // EFFECTS: Saves current PiecesMemory to the Pieces_memory file in /data. Prints a string to screen indicating
    // whether save was successful or not. This will overwrite whatever was originally in the Pieces_memory file.

    // Move this to the Composer class in Model, and add something to catch the exception here and print the success
    // statement if successful somewhere in the UI body. NOT SURE IF THE CATCH IOEXCEPTION E IS CORRECT. SHOULD IT BE
    // E??? OR ALL???? NEED TO CHECK
    public void memSave(PiecesMemory currentPMem) throws IOException {
        FileOutputStream fos = new FileOutputStream(NoteConstants.getFilePath() + "/Pieces_memory.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(currentPMem);
        fos.close();
        oos.close();
        System.out.println("Save Successful!");
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
