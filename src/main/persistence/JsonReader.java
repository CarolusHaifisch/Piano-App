package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

import model.Note;
import model.Piece;
import model.PiecesMemory;
import org.json.*;

/**
 * Inspired and based on code from the JSON Serialization demo code provided by the CPSC 210 teaching team.
 */

public class JsonReader {
    // Represents a reader that reads a PiecesMemory from JSON data stored in file at given filepath
    private String filepath;
    private PiecesMemory pmem;

    // EFFECTS: constructs reader to read from source file at filepath
    public JsonReader(String pathname) {
        this.filepath = pathname;
    }

    // EFFECTS: reads PiecesMemory from file and returns it,
    // throws IOException if an error occurs while reading data from file
    public PiecesMemory read() throws IOException {
        String jsonData = readFile(filepath);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePiecesMemory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses PiecesMemory from JSON object and returns it
    private PiecesMemory parsePiecesMemory(JSONObject jsonObject) {
        pmem = new PiecesMemory(new LinkedList<Piece>());
        addPieces(pmem, jsonObject);
        return pmem;
    }

    // MODIFIES: PiecesMemory pmem
    // EFFECTS: parses pieces from JSON object and adds them to PiecesMemory
    private void addPieces(PiecesMemory pmem, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("piecesmemory");
        for (Object json : jsonArray) {
            JSONObject nextPiece = (JSONObject) json;
            addPiece(pmem, nextPiece);
        }
    }

    // MODIFIES: PiecesMemory pmem, Piece piece
    // EFFECTS: parses Piece from JSON object and adds it to PiecesMemory. Also parses each Note in Piece and adds
    // notes to the Piece.
    private void addPiece(PiecesMemory pmem, JSONObject jsonObject) {
        String name = jsonObject.getString("piecename");
        JSONArray jsonArray = jsonObject.getJSONArray("piececontents");
        Piece piece = new Piece(name, new ArrayList<Note>());
        for (Object json : jsonArray) {
            JSONObject nextNote = (JSONObject) json;
            addNote(piece, nextNote);
        }
        pmem.addPiece(piece);
    }

    // MODIFIES: Piece piece
    // EFFECTS: parses Note from JSON object and adds it to Piece
    private void addNote(Piece piece, JSONObject jsonObject) {
        int intname = jsonObject.getInt("name");
        char name = (char) intname;
        double duration = jsonObject.getDouble("duration");
        int octave = jsonObject.getInt("octave");
        boolean isSharp = jsonObject.getBoolean("sharp?");
        boolean isFlat = jsonObject.getBoolean("flat?");
        Note note = new Note(name, duration, octave, isSharp, isFlat);
        piece.addNote(note);
    }
}
