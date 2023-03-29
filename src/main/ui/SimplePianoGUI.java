package ui;

import model.ComposerConstants;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SimplePianoGUI extends JFrame implements KeyListener {
    /** GUI for the piano keyboard for composing notes.
     * Because I cannot figure out how to drag the individual jbutton elements in the GUI form so I can create a
     * proper piano, (It simply won't let me drag and resize the individual jbutton elements, and forces
     * the buttons to a grid that I cannot modify), this simplified GUI of a piano will have to do.
     */
    private JButton[] keys;
    private JFrame pianoFrame;
    private String noteString;
    private JLabel label;

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
        accidentals.add(new JButton("b"));
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

    // EFFECTS: PLaces label displaying current note being composed at top of pianoFrame
    private void addLabel() {
        JLabel noteLabel = new JLabel();
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(noteLabel);
        hbox.add(Box.createHorizontalGlue());
        pianoFrame.add(hbox, BorderLayout.NORTH);
    }


    // Listener for keyEvents, checks to see if note inputs are made and updates the noteLabel.
    // TODO: Restructure the if elses into a switch statement that depends only on the identity of the button rather
    // than length of string
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();

            if (noteString.length() == 0) {
                if (ComposerUIConstants.notesList.contains(src.getText().charAt(0))) {
                    noteString += src.getText();
                }
            } else if (noteString.length() == 1) {
                if (ComposerUIConstants.octavesList.contains(Integer.valueOf(src.getText())));

                noteString = noteString + src.getText();
            }

            label.setText("");
            label.repaint();
        }
    }
// TODO: Add method to deal with note duration! Idea: Radiobuttons for common durations (whole quarter half eighth
    // triplet, add input bar for choice to enter other duration
    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char key = ke.getKeyChar();

        if (key == '0')
            keys[10].doClick();
        else if (key > '0' && key <= '9')
            keys[ke.getKeyChar() - '1'].doClick();
    }
}
