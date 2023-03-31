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
    private String noteString;
    private JPanel pianoKeyboard;
    private JLabel label;
    private static JTextField inputText;
    private double duration;
    private Piece selectedPiece;
    private Note currentNote;

    public SimplePianoGUI(Piece piece) {
        pianoFrame = new JFrame("Piano GUI");
        selectedPiece = piece;
        pianoFrame.setSize(ComposerUIConstants.WIDTH, ComposerUIConstants.HEIGHT);
        pianoFrame.setVisible(true);
        keyHandler = new ClickHandler();
        keyHandler2 = new ClickHandler2();
        currentNote = new Note(Character.MIN_VALUE, 0, 0, false, false);
        label = new JLabel("Start by selecting a note, then choose an octave and accidental.");
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
        accidentalKeys = new JButton[3];
        accidentalKeys[0] = new JButton("#");
        accidentalKeys[0].addActionListener(keyHandler);
        p.add(accidentalKeys[0]);
        accidentalKeys[1] = new JButton("♭");
        accidentalKeys[1].addActionListener(keyHandler);
        p.add(accidentalKeys[1]);
        accidentalKeys[2] = new JButton("♮");
        accidentalKeys[2].addActionListener(keyHandler);
        p.add(accidentalKeys[2]);
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

    // EFFECTS: Places Add Note, Clear, and Return buttons for bottomPanel.
    private JPanel addBottomButtons() {
        JPanel bottomButtons = new JPanel();
        bottomKeys = new JButton[4];
        bottomButtons.setLayout(new FlowLayout());
        bottomKeys[0] = new JButton("Add Note");
        bottomKeys[0].addActionListener(keyHandler2);
        bottomKeys[1] = new JButton("Delete Last Added Note");
        bottomKeys[1].addActionListener(keyHandler2);
        bottomKeys[2] = new JButton("Delete Note at Index");
        bottomKeys[2].addActionListener(keyHandler2);
        bottomKeys[3] = new JButton("Return");
        bottomKeys[3].addActionListener(keyHandler2);
        bottomButtons.add(bottomKeys[0]);
        bottomButtons.add(bottomKeys[1]);
        bottomButtons.add(bottomKeys[2]);
        bottomButtons.add(bottomKeys[3]);
        return bottomButtons;
    }

    // MODIFIES: currentNote
    // EFFECTS: Listener for keyEvents, checks to see if note inputs are made and updates the noteLabel.
    // TODO: Restructure the if elses into a switch statement that depends only on the identity of the button rather
    // than length of string
    // Requires modifying the GUI frame
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();
            if (src.getText().equals(("Add Duration"))) {
                currentNote.setDuration(duration);
            } else if (ComposerUIConstants.notesList.contains(src.getText().charAt(0))) {
                currentNote.setName(src.getText().charAt(0));
            }
            octaveHelper(src);
            accidentalHelper(src);

            label.setText("Editing Piece: " + selectedPiece.getPieceName() + " Current Note: " + noteStringBuilder());
            label.repaint();
        }
    }

    // EFFECTS: Helper for generating note string to be displayed at top of GUI frame
    private String noteStringBuilder() {
        if (currentNote.getSharp()) {
            noteString = (String.valueOf(currentNote.getName()) + "#" + currentNote.getOctave() + "/"
                    + currentNote.getDuration());
        } else if (currentNote.getFlat()) {
            noteString = (String.valueOf(currentNote.getName()) + "♭" + currentNote.getOctave() + "/"
                    + currentNote.getDuration());
        } else {
            noteString = (String.valueOf(currentNote.getName()) + currentNote.getOctave() + "/"
                    + currentNote.getDuration());
        }
        return noteString;
    }

    // MODIFIES: currentNote
    // EFFECTS: Helper for adding octave value to currentNote
    private void octaveHelper(JButton src) {
        try {
            if (ComposerUIConstants.octavesList.contains(Integer.valueOf(src.getText()))) {
                currentNote.setOctave(Integer.valueOf(src.getText()));
            }
        } catch (NumberFormatException nfe) {
            //Do nothing.
        }
    }

    // MODIFIES: currentNote
    // EFFECTS: Helper for adding accidental to currentNote
    private void accidentalHelper(JButton src) {
        if (src.getText().equals("#")) {
            currentNote.setSharp();
        }
        if (src.getText().equals(("♭"))) {
            currentNote.setFlat();
        }
        if (src.getText().equals("♮")) {
            currentNote.setNatural();
        }
    }


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

    // MODIFIES: selectedPiece
    // EFFECTS: Listener for keyEvents for Enter, Add Note, Delete Note at Index, Delete Last Added Note,
    // Clear, and Return Buttons. Return does not automatically save piece status.

    //TODO: Add option to insert note at index.
    private class ClickHandler2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();
            if (src.getText().equals("Enter")) {
                enterHelper();
            } else if (src.getText().equals("Add Note")) {
                selectedPiece.addNote(new Note(currentNote.getName(), currentNote.getDuration(),
                        currentNote.getOctave(), currentNote.getSharp(), currentNote.getFlat()));
                label.setText("Note added to piece");
            } else if (src.getText().equals("Delete Last Added Note")) {
                if (selectedPiece.length() >= 1) {
                    Note removedNote = selectedPiece.getNoteAtIndex(selectedPiece.length() - 1);
                    selectedPiece.delNote(selectedPiece.length() - 1);
                    label.setText("Note " + removedNote.noteToString() + " deleted.");
                } else {label.setText("Piece is already empty!");}
            } else if (src.getText().equals("Delete Note at Index")) {
                delNoteIndexHelper();
            } else if (src.getText().equals("Return")) {
                pianoFrame.dispose();
            }
            label.repaint();
        }
    }

    // MODIFIES: selectedPiece
    // EFFECTS: Helper for Deleting note at index in piece
    public void delNoteIndexHelper() {
        Object[] possibilities = {null};
        try {
            Integer inputNoteIndex = Integer.valueOf((String) JOptionPane.showInputDialog(null,
                    "Enter index of note to be deleted in piece",
                    "New Piece", JOptionPane.INFORMATION_MESSAGE));
            if (inputNoteIndex < selectedPiece.length()) {
                Note removedNote = selectedPiece.getNoteAtIndex(inputNoteIndex);
                selectedPiece.delNote(inputNoteIndex);
                label.setText("Note " + removedNote.noteToString() + " deleted.");
            } else {
                JOptionPane.showMessageDialog(pianoFrame, "Input must be an integer less than length of piece.",
                        "Invalid Input", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(pianoFrame, "Must be an integer.",
                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
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

