package ui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class SimplePianoGUI {
    /**
     * Because I cannot figure out how to drag the individual jbutton elements in the GUI form so I can create a
     * proper piano, (It simply won't let me drag and resize the individual jbutton elements, and forces
     * the buttons to a grid that I cannot modify), this simplified GUI of a piano will have to do.
     */
    private JButton[] keys;
    private JFrame pianoFrame;

    public SimplePianoGUI() {
        pianoFrame = new JFrame("Piano GUI");
        pianoFrame.setSize(ComposerUIConstants.WIDTH, ComposerUIConstants.HEIGHT);
        pianoFrame.setVisible(true);
        addPianoPanel();
        addOctaveandAccidentalPanel();
        pianoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel setPianoPanel() {
        JPanel pianoKeyboardPanel = new JPanel();
        pianoKeyboardPanel.setLayout(new GridLayout(1, 8));
        pianoKeyboardPanel.add(new JButton("C"));
        pianoKeyboardPanel.add(new JButton("D"));
        pianoKeyboardPanel.add(new JButton("E"));
        pianoKeyboardPanel.add(new JButton("F"));
        pianoKeyboardPanel.add(new JButton("G"));
        pianoKeyboardPanel.add(new JButton("A"));
        pianoKeyboardPanel.add(new JButton("B"));
        pianoKeyboardPanel.add(new JButton("Rest"));
        return pianoKeyboardPanel;
    }

    // EFFECTS: Sets up simplified piano layout.
    private void addPianoPanel() {
        JPanel pianoKeyboardPanel = new JPanel();
        pianoKeyboardPanel.setLayout(new GridLayout(2, 1));
        JLabel l = new JLabel("Note", SwingConstants.CENTER);
        pianoKeyboardPanel.add(l);
        pianoKeyboardPanel.add(setPianoPanel());
        pianoFrame.add(pianoKeyboardPanel, BorderLayout.CENTER);
    }

    // EFFECTS: Sets up panel for choosing octave
    private JPanel setOctavesPanel() {
        JPanel octavesPanel = new JPanel();
        octavesPanel.setLayout(new GridLayout(3, 3));
        keys = new JButton[12];

        for (int i = 0; i < 9; i++) {
            keys[i] = new JButton(Integer.toString(i + 1));
            octavesPanel.add(keys[i]);
        }
        return octavesPanel;
    }

    // EFFECTS: Adds octaves panel with label to pianoFrame
    private JPanel addOctavesPanel() {
        JPanel octavesPanel = new JPanel();
        JLabel l = new JLabel("Octave Number", SwingConstants.CENTER);
        octavesPanel.setLayout(new GridLayout(2, 1));
        octavesPanel.add(l);
        octavesPanel.add(setOctavesPanel());
        return octavesPanel;
    }

    // EFFECTS: Sets up panel for choosing whether note is sharp or flat
    private JPanel addAccidentalsPanel() {
        JPanel accidentalsPanel = new JPanel();
        JLabel l = new JLabel("Accidentals", SwingConstants.CENTER);
        accidentalsPanel.setLayout(new GridLayout(2, 1));
        accidentalsPanel.add(l);
        accidentalsPanel.add(setAccidentalButtons());
        return accidentalsPanel;
    }

    private JPanel setAccidentalButtons() {
        JPanel accidentals = new JPanel();
        accidentals.setLayout(new GridLayout(1, 2));
        accidentals.add(new JButton("#"));
        accidentals.add(new JButton("♭"));
        return accidentals;
    }

    // EFFECTS: Places both octaves and accidentals panel with labels in pianoFrame
    private void addOctaveandAccidentalPanel() {
        JPanel oapanel = new JPanel();
        oapanel.setLayout(new GridLayout(1, 2));
        oapanel.add(addAccidentalsPanel());
        oapanel.add(addOctavesPanel());
        pianoFrame.add(oapanel, BorderLayout.SOUTH);
    }
}
