package ui;

import javax.swing.*;

import exception.PieceNotFoundException;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


/** GUI for the Composer. Swing functions inspired by code from Alarm, SmartHome, and SpaceInvaders sample code provided
 * by CPSC 210 teaching team.
 */
// TODO: Fix issue with the combobox on main frame not updating to reflect added/deleted pieces
public class ComposerGUI extends JFrame {
    private final Composer composer = new Composer();
    private PiecesMemory memory;
    private Piece selectedPiece;
    private String pieceName;
    JComboBox<String> piecesDropdown;
    String[] pieces;
    private JButton[] pieceButtons;
    JPanel pieceButtonPanel;
    private ClickHandler keyHandler;

    public static void main(String[] args) {
        new ComposerGUI();
    }

    //MODIFIES: this
    //EFFECTS: creates ComposerGUI
    private ComposerGUI() {
        super("Java Music Composer V0.2");

        setSize(ComposerUIConstants.WIDTH, ComposerUIConstants.HEIGHT);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new SaveonClose());
        initializationLoad();
        addMenu();
        piecesDropdown();
        pieceButtons();
        setVisible(true);
    }

    // EFFECTS: Places dropdown bar to select a piece from memory.
    private void piecesDropdown() {
        JPanel dropdown = new JPanel();
        JLabel label = new JLabel("Choose a piece from memory:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        updatePieces();
        piecesDropdown.setMaximumSize(new Dimension(200, 50));
        piecesDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropdown.setLayout(new BoxLayout(dropdown, BoxLayout.Y_AXIS));
        dropdown.add(Box.createRigidArea(new Dimension(5, 10)));
        dropdown.add(label);
        dropdown.add(Box.createRigidArea(new Dimension(5, 20)));
        dropdown.add(piecesDropdown);
        dropdown.add(Box.createRigidArea(new Dimension(5, 20)));
        JButton select = new JButton("Select");
        select.setAlignmentX(Component.CENTER_ALIGNMENT);
        dropdown.add(select);
        this.add(dropdown);
    }

    // EFFECTS: Updates pieces list in memory.
    private void updatePieces() {
        pieces = memory.getPieceNames().split(", ");
        piecesDropdown = new JComboBox<>(pieces);
    }

    // EFFECTS: Places buttons for playing pieces, viewing piece information, and viewing piece image.
    private void pieceButtons() {
        pieceButtonPanel = new JPanel();
        pieceButtonPanel.setLayout(new FlowLayout());
        pieceButtons = new JButton[3];
        pieceButtons[0] = new JButton("Play Piece");
        pieceButtons[0].addActionListener(keyHandler);
        pieceButtons[1] = new JButton("View Piece Info");
        pieceButtons[1].addActionListener(keyHandler);
        pieceButtons[2] = new JButton("Piece Sheet Music Image");
        pieceButtons[2].addActionListener(keyHandler);
        pieceButtonPanel.add(pieceButtons[0]);
        pieceButtonPanel.add(pieceButtons[1]);
        pieceButtonPanel.add(pieceButtons[2]);
        this.add(pieceButtonPanel, BorderLayout.SOUTH);
    }

    // Class for handling saving on close operation.
    private class SaveonClose extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent we) {
            String[] choiceButtons = {"Yes","No"};
            int chosenOption = JOptionPane.showOptionDialog(
                    null,"Save before exiting?",
                    "Save",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
                    null,choiceButtons, choiceButtons[1]);
            if (chosenOption == JOptionPane.YES_OPTION) {
                try {
                    composer.memSave(memory, ComposerConstants.getFileDirectory());
                    JOptionPane.showMessageDialog(null, "Save Successful!", "Save Memory",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Could not save memory.", "Save Memory",
                            JOptionPane.ERROR_MESSAGE);
                }
                System.exit(0);
            } else {
                System.exit(0);
            }
        }
    }


    //EFFECTS: returns Composer object controlled by this UI
    public Composer getComposer() {
        return composer;
    }

    // EFFECTS: Adds menu bar to GUI.
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        addMenuItem(fileMenu, new SaveAction(),
                KeyStroke.getKeyStroke("control S"));
        addMenuItem(fileMenu, new LoadAction(), KeyStroke.getKeyStroke("control L"));
        addMenuItem(fileMenu, new ClearAction(), null);
        menuBar.add(fileMenu);
        // TODO BELOW

        JMenu codeMenu = new JMenu("Pieces");
        codeMenu.setMnemonic('P');
        addMenuItem(codeMenu, new AddPieceAction(), KeyStroke.getKeyStroke("control N"));
        addMenuItem(codeMenu, new RemovePieceAction(), KeyStroke.getKeyStroke("control R"));
        menuBar.add(codeMenu);

        setJMenuBar(menuBar);
    }

    /**
     * EFFECTS: Adds an item with given handler to the given menu, where menu is the menu for which the item is
     * added to, action is the handler for the new menu item, and accel is a keystroke accelerator for the
     * new menu item added.
     */
    private void addMenuItem(JMenu menu, AbstractAction action, KeyStroke accel) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accel);
        menu.add(menuItem);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /**
     * Represents save action for saving the current state of the program.
     */
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        // EFFECTS: Runs when the save action occurs (Whenever the save option is chosen by the user)
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                composer.memSave(memory, ComposerConstants.getFileDirectory());
                JOptionPane.showMessageDialog(null, "Save Successful!", "Save Memory",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not save memory.", "Save Memory",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents load action for loading the previously saved state of the program.
     */
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load");
        }

        // EFFECTS: Runs when the load action occurs (Whenever the load option is chosen by the user)
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                memory = composer.memRetrieve(ComposerConstants.getFileDirectory());
                JOptionPane.showMessageDialog(null, "Load Successful!", "Load Memory",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not retrieve memory.",
                        "Load Memory", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents clear action to clear all local memory from current state of the program
     */
    private class ClearAction extends AbstractAction {

        ClearAction() {
            super("Clear");
        }

        // EFFECTS: Runs when the clear action occurs (Whenever the clear option is chosen by the user)
        @Override
        public void actionPerformed(ActionEvent ae) {
            memory = new PiecesMemory(new LinkedList<>());
            JOptionPane.showMessageDialog(null, "PiecesMemory cleared.", "Clear Memory",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }




    /**
     * Represents action to add piece to memory.
     */
    private class AddPieceAction extends AbstractAction {

        AddPieceAction() {
            super("Add New Piece");
        }

        // EFFECTS: Runs when the add action occurs (Whenever the add option is chosen by the user)
        @Override
        public void actionPerformed(ActionEvent ae) {
            Object[] possibilities = {null};
            String inputPieceName = (String)JOptionPane.showInputDialog(null, "Enter name of the piece",
                    "New Piece", JOptionPane.INFORMATION_MESSAGE);
            if (inputPieceName != null) {
                try {
                    memory.getPieceWithName(inputPieceName);
                    JOptionPane.showMessageDialog(ComposerGUI.this,
                            "Piece already exists with this name!", "Piece", JOptionPane.ERROR_MESSAGE);
                } catch (PieceNotFoundException pnfe) {
                    selectedPiece = new Piece(inputPieceName, new ArrayList<>());
                    SimplePianoGUI sp = new SimplePianoGUI(selectedPiece);
                    memory.addPiece(selectedPiece);
                    updatePieces();
                    pieceButtonPanel.repaint();
                    ComposerGUI.this.validate();
                    ComposerGUI.this.repaint();
                }

            }


        }
    }

    /**
     * Represents action to remove piece from memory.
     */
    private class RemovePieceAction extends AbstractAction {

        RemovePieceAction() {
            super("Remove Piece");
        }

        // EFFECTS: Runs when the remove action occurs (Whenever the remove option is chosen by the user)
        @Override
        public void actionPerformed(ActionEvent ae) {
            updatePieces();
            String inputPieceName = (String)JOptionPane.showInputDialog(null,
                    "Select piece to be deleted:", "Delete Piece", JOptionPane.INFORMATION_MESSAGE,
                    null, pieces, null);
            if (inputPieceName != null) {
                try {
                    memory.delPiece(memory.getIndexOfPiece(inputPieceName));
                    updatePieces();
                    pieceButtonPanel.repaint();
                    ComposerGUI.this.revalidate();
                    ComposerGUI.this.repaint();

                } catch (PieceNotFoundException pnfe) {
                    JOptionPane.showMessageDialog(ComposerGUI.this, "Piece not found.",
                            "Not Found", JOptionPane.WARNING_MESSAGE);
                }
            }


        }
    }




    // EFFECTS: Runs when the initialization load action occurs (Runs upon starting program to load saved data)

    public void initializationLoad() {
        try {
            if (composer.memExists(ComposerConstants.getFileDirectory())) {
                memory = composer.memRetrieve(ComposerConstants.getFileDirectory());
            } else {
                memory = new PiecesMemory(new LinkedList<>());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not retrieve memory.",
                    "Load Memory", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Listener for keyEvents for Enter, Add Note, Clear, and Cancel Buttons
    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton src = (JButton) e.getSource();
            if (src.getText().equals("Play Piece")) {
                //
            } else if (src.getText().equals("View Piece Info")) {
                //
            } else if (src.getText().equals("Piece Sheet Music Image")) {
                //
                }
        }
    }
}

// TODO: Add buttons to main frame. Dropdown menu for choosing a piece from composed pieces in memory,
// Then upon choosing a piece which is noted in local memory there are buttons for playing the piece,
// opening a panel displaying information about the piece, and a button for adding notes, and a button for deleting
// notes from the piece. Piece addition and deletion is handled by buttons on top menu bar.


