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
    /**
     * GUI for the piano keyboard for composing notes.
     * Because I cannot figure out how to drag the individual jbutton elements in the GUI form so I can create a
     * proper piano, (It simply won't let me drag and resize the individual jbutton elements, and forces
     * the buttons to a grid that I cannot modify), this simplified GUI of a piano will have to do.
     */
    private JButton[] keys;
    private JButton[] pianoKeys;
    private JButton[] accidentalKeys;
    private JButton durationSave;
    private ClickHandler keyHandler;
    private JFrame pianoFrame;
    private StringBuilder noteString;
    private JPanel pianoKeyboard;
    private JLabel label;
    private static JTextField inputText;
    private double duration;

    public SimplePianoGUI() {
        noteString = new StringBuilder("");
        pianoFrame = new JFrame("Piano GUI");
        pianoFrame.setSize(ComposerUIConstants.WIDTH, ComposerUIConstants.HEIGHT);
        pianoFrame.setVisible(true);
        keyHandler = new ClickHandler();
        label = new JLabel("Start by selecting a note, then choose an octave and accidental. If no accidental"
                + " is chosen note will be natural.");
        addMainPanel();
        addLabel();

        pianoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel setPianoPanel() {
        JPanel pianoKeyboard = new JPanel();
        pianoKeyboard.setLayout(new GridLayout(1, 8));
        addPianoButtons(pianoKeyboard);
        return pianoKeyboard;
    }

    // EFFECTS: Adds Piano buttons to panel p
    private void addPianoButtons(JPanel p) {
        pianoKeys = new JButton[8];

        for (int i = 0; i < 8; i++) {
            pianoKeys[i] = new JButton(Character.toString(ComposerUIConstants.notesList.get(i)));
            pianoKeys[i].addActionListener(keyHandler);
            if (i == 7) {
                pianoKeys[i].setText("Rest");
            }
            p.add(pianoKeys[i]);
        }
    }

    // EFFECTS: Sets up simplified piano layout.
    private JPanel addPianoPanel() {
        JPanel pianoKeyboardPanel = new JPanel();
        pianoKeyboardPanel.setLayout(new GridLayout(2, 1));
        JLabel l = new JLabel("Note", SwingConstants.CENTER);
        pianoKeyboardPanel.add(l);
        pianoKeyboardPanel.add(setPianoPanel());
        return pianoKeyboardPanel;
    }

    // EFFECTS: Sets up panel for choosing octave
    private JPanel setOctavesPanel() {
        JPanel octavesPanel = new JPanel();
        octavesPanel.setLayout(new GridLayout(3, 3));
        keys = new JButton[9];

        for (int i = 0; i < 9; i++) {
            keys[i] = new JButton(Integer.toString(i + 1));
            keys[i].addActionListener(keyHandler);
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
        accidentalsPanel.add(setAccidentalButtonsPanel());
        return accidentalsPanel;
    }

    // EFFECTS: Sets up the panel of accidental buttons
    private JPanel setAccidentalButtonsPanel() {
        JPanel accidentals = new JPanel();
        accidentals.setLayout(new GridLayout(1, 2));
        addAccidentalButtons(accidentals);
        return accidentals;
    }

    // EFFECTS: Adds Accidental buttons to panel p
    private void addAccidentalButtons(JPanel p) {
        accidentalKeys = new JButton[2];
        accidentalKeys[0] = new JButton("#");
        accidentalKeys[0].addActionListener(keyHandler);
        p.add(accidentalKeys[0]);
        accidentalKeys[1] = new JButton("b");
        accidentalKeys[1].addActionListener(keyHandler);
        p.add(accidentalKeys[1]);
    }


    // EFFECTS: Places both octaves and accidentals panel with labels in pianoFrame
    private JPanel addOctaveandAccidentalPanel() {
        JPanel oapanel = new JPanel();
        oapanel.setLayout(new GridLayout(1, 2));
        oapanel.add(addAccidentalsPanel());
        oapanel.add(addOctavesPanel());
        return oapanel;
    }

    // EFFECTS: Places the notes, octaves, and accidentals panel into one main GUI panel
    private void addMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(addPianoPanel());
        mainPanel.add(addOctaveandAccidentalPanel());
        pianoFrame.add(mainPanel, BorderLayout.CENTER);
    }

    // EFFECTS: PLaces label displaying current note being composed at top of pianoFrame
    private void addLabel() {
        Box hbox = Box.createHorizontalBox();
        hbox.add(Box.createHorizontalGlue());
        hbox.add(label);
        hbox.add(Box.createHorizontalGlue());
        pianoFrame.add(hbox, BorderLayout.NORTH);
    }

    // EFFECTS: Places input for duration and buttons for adding note, clearing note, and cancel
    private void addBottomPanel() {

    }

    // EFFECTS: Places the input text field for note duration.
    private void addDurationField() {
        JPanel inputPanel = new JPanel();
        inputText = new JTextField();
        durationSave = new JButton("Enter");

    }

    // TODO: Create new split panel for the text input box for duration of notes on left, and buttons for
    // Add (note to piece) and Cancel on right

    // Listener for keyEvents, checks to see if note inputs are made and updates the noteLabel.
    // TODO: Restructure the if elses into a switch statement that depends only on the identity of the button rather
    // than length of string
    // TODO: Add clear option at the bottom along with add note and OK/Cancel buttons to clear noteString before adding
    // Requires modifying the GUI frame
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();
            if (noteString.length() == 0) {
                if (ComposerUIConstants.notesList.contains(src.getText().charAt(0))) {
                    noteString.append(src.getText().charAt(0));
                }
            } else if (noteString.length() == 1) {
                octaveHelper(src);
            } else if (noteString.length() == 2) {
                accidentalHelper(src);
            }
            if (noteString.length() > 2 && !noteString.toString().contains("/")) {
                if (src.getText().equals(("Add Duration"))) {
                    noteString.append("/" + duration);
                }
            }
            label.setText("Current Note: " + noteString);
            label.repaint();
        }
    }

    // EFFECTS: Helper for adding octave value to noteString
    private void octaveHelper(JButton src) {
        try {
            if (ComposerUIConstants.octavesList.contains(Integer.valueOf(src.getText()))) {
                noteString.append(src.getText());
            }
        } catch (NumberFormatException nfe) {
            // Do nothing
        }
    }

    // EFFECTS: Helper for adding accidental to noteString
    private void accidentalHelper(JButton src) {
        if (src.getText().equals("#")) {
            noteString.insert(1, src.getText());
        }
        if (src.getText().equals(("b"))) {
            noteString.insert(1, src.getText());
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

        if (key > '1' && key <= '9') {
            keys[ke.getKeyChar() - '1'].doClick();
        }
        if (ComposerUIConstants.notesList.contains(key)) {
            pianoKeys[ComposerUIConstants.notesList.indexOf(key)].doClick();
        }
    }

// Listener for keyEvents for Enter, Add Note, Clear, and Cancel Buttons
    private class ClickHandler2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();
            if (src.getText().equals("Enter")) {
                try {
                    if (Double.parseDouble(inputText.getText()) > 0) {
                        duration = Double.parseDouble(inputText.getText());
                    } else {
                        // Print same error message as below!
                    }
                } catch (NumberFormatException nfe) {
                    // TODO: Print an error message popup saying invalid input!
                }
            }
            label.setText("Duration saved! Duration = " + duration);
            label.repaint();
        }
    }
}

