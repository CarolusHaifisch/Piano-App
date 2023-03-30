package ui;

import model.ComposerConstants;
import model.Note;
import model.Piece;

import javax.swing.*;
import java.awt.*;
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
    private JButton[] durationSave;
    private JButton[] bottomKeys;
    private ClickHandler keyHandler;
    private ClickHandler2 keyHandler2;
    private JFrame pianoFrame;
    private StringBuilder noteString;
    private JPanel pianoKeyboard;
    private JLabel label;
    private static JTextField inputText;
    private double duration;
    private Piece selectedPiece;
    private Note currentNote;

    public SimplePianoGUI(Piece piece) {
        noteString = new StringBuilder("");
        pianoFrame = new JFrame("Piano GUI");
        selectedPiece = piece;
        pianoFrame.setSize(ComposerUIConstants.WIDTH, ComposerUIConstants.HEIGHT);
        pianoFrame.setVisible(true);
        keyHandler = new ClickHandler();
        keyHandler2 = new ClickHandler2();
        label = new JLabel("Start by selecting a note, then choose an octave and accidental. If no accidental"
                + " is chosen note will be natural.");
        addMainPanel();
        addLabel();
        addBottomPanel();
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
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.add(addDurationField());
        bottomPanel.add(addBottomButtons());
        pianoFrame.add(bottomPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: Places the input text field for note duration.
    private JPanel addDurationField() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1));
        inputText = new JTextField();
        inputPanel.add(inputText);
        inputPanel.add(addDurationButtons());
        return inputPanel;
    }

    // EFFECTS: Places buttons for duration field panel.
    private JPanel addDurationButtons() {
        JPanel durationButtons = new JPanel();
        durationSave = new JButton[2];
        durationButtons.setLayout(new GridLayout(2, 1));
        durationSave[0] = new JButton("Enter");
        durationSave[0].addActionListener(keyHandler2);
        durationSave[1] = new JButton("Add Duration");
        durationSave[1].addActionListener(keyHandler);
        durationButtons.add(durationSave[0]);
        durationButtons.add(durationSave[1]);
        return durationButtons;
    }

    // EFFECTS: Places Add Note, Clear, and Cancel buttons for bottomPanel.
    private JPanel addBottomButtons() {
        JPanel bottomButtons = new JPanel();
        bottomKeys = new JButton[3];
        bottomButtons.setLayout(new FlowLayout());
        bottomKeys[0] = new JButton("Add Note");
        bottomKeys[0].addActionListener(keyHandler2);
        bottomKeys[1] = new JButton("Clear");
        bottomKeys[1].addActionListener(keyHandler2);
        bottomKeys[2] = new JButton("Cancel");
        bottomKeys[2].addActionListener(keyHandler2);
        bottomButtons.add(bottomKeys[0]);
        bottomButtons.add(bottomKeys[1]);
        bottomButtons.add(bottomKeys[2]);
        return bottomButtons;
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
            if (ComposerUIConstants.notesList.contains(src.getText().charAt(0))) {
                currentNote.setName(src.getText().charAt(0));
            }
            octaveHelper(src);
            accidentalHelper(src);
            if (src.getText().equals(("Add Duration"))) {
                currentNote.setDuration(duration);
            }

            label.setText("Current Note: " + noteString);
            label.repaint();
        }
    }

    // EFFECTS: Helper for generating note string to be displayed at top of GUI frame
    private String noteStringBuilder() {
        if (currentNote.getSharp()) {
            noteString.append(currentNote.getName() + "#" + currentNote.getOctave() + "/" + currentNote.getDuration());
        } else if (currentNote.getFlat()) {
            noteString.append(currentNote.getName() + "♭" + currentNote.getOctave() + "/" + currentNote.getDuration());
        } else {
            noteString.append(currentNote.getName() + currentNote.getOctave() + "/" + currentNote.getDuration());
        }
        return noteString.toString()
    }

    // EFFECTS: Helper for adding octave value to noteString
    private void octaveHelper(JButton src) {
        try {
            if (ComposerUIConstants.octavesList.contains(Integer.valueOf(src.getText()))) {
                currentNote.setOctave(Integer.valueOf(src.getText()));
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(pianoFrame, "Octave must be a positive integer.",
                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: Helper for adding accidental to noteString
    private void accidentalHelper(JButton src) {
        if (src.getText().equals("#")) {
            currentNote.setSharp();
        }
        if (src.getText().equals(("b"))) {
            currentNote.setFlat();
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
                enterHelper();
            } else if (src.getText().equals("Add Note")) {
                selectedPiece.addNote(currentNote);
                label.setText("Note added to piece");
            } else if (src.getText().equals("Clear")) {
                noteString = new StringBuilder();
                label.setText("Note cleared.");
            } else if (src.getText().equals("Cancel")) {
                pianoFrame.dispose();
            }
            label.repaint();
        }
    }

    // EFFECTS: Helper for Enter button case in ClickHandler2
    private void enterHelper() {
        try {
            if (Double.parseDouble(inputText.getText()) > 0) {
                duration = Double.parseDouble(inputText.getText());
                label.setText("Duration saved! Duration = " + duration);
            } else {
                JOptionPane.showMessageDialog(pianoFrame, "Duration must be a positive number.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(pianoFrame, "Duration must be a positive number.",
                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
        }
    }
}

