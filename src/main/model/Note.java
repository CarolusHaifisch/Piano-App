package model;

public class Note {
    private int frequency;
    private int duration;
    private int intensity;
    private String name;

    // REQUIRES: noteDuration != 0, noteIntensity != 0, noteFreq !=0
    // EFFECTS: Constructs a note with given frequency, duration, intensity, and name
    public Note(int noteFreq, int noteDuration, int noteIntensity, String noteName) {
        this.frequency = noteFreq;
        this.duration = noteDuration;
        this.intensity = noteIntensity;
        this.name = noteName;
    }
}
