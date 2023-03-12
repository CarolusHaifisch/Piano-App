package persistence;

import model.PiecesMemory;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String filepath;

    // EFFECTS: constructs writer to write to a file at given pathname
    public JsonWriter(String pathname) {
        this.filepath = pathname;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer, throws FileNotFoundException if the file at given filepath cannot be opened to write
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(filepath));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of the current state of PiecesMemory to a JSON file
    public void write(PiecesMemory currentPMem) {
        JSONObject json = currentPMem.toJson();
        saveStringToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveStringToFile(String json) {
        writer.print(json);
    }


}
