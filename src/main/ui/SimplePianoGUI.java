package ui;

import model.ComposerConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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


    // EFFECTS: Sets up simplified piano layout.
    private void addPianoPanel() {
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
        pianoFrame.add(pianoKeyboardPanel, BorderLayout.CENTER);
    }

    // EFFECTS: Sets up panel for choosing octave
    private JPanel addOctavesPanel() {
        JPanel octavesPanel = new JPanel();
        octavesPanel.setLayout(new GridLayout(3, 3));
        keys = new JButton[12];

        for (int i = 0; i < 9; i++) {
            keys[i] = new JButton(Integer.toString(i + 1));
            octavesPanel.add(keys[i]);
        }
        return octavesPanel;
    }

    // EFFECTS: Sets up panel for choosing whether note is sharp or flat
    private JPanel addAccidentalsPanel() {
        JPanel accidentalsPanel = new JPanel();
        accidentalsPanel.setLayout(new GridLayout(1, 2));
        accidentalsPanel.add(new JButton("#"));
        accidentalsPanel.add(new JButton("♭"));
        return accidentalsPanel;
    }

    private void addOctaveandAccidentalPanel() {
        JPanel oapanel = new JPanel();
        oapanel.setLayout(new GridLayout(1, 2));
        oapanel.add(addAccidentalsPanel());
        oapanel.add(addOctavesPanel());
        pianoFrame.add(oapanel, BorderLayout.SOUTH);
    }
}
