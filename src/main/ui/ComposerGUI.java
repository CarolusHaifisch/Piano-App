package ui;

import javax.swing.*;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


/** GUI for the Composer. Swing functions inspired by code from Alarm, SmartHome, and SpaceInvaders sample code provided
 * by CPSC 210 teaching team.
 */
public class ComposerGUI extends JFrame {
    private JTabbedPane sidebar;
    private final Composer composer = new Composer();
    private PiecesMemory memory;
    private Piece selectedPiece;
    private String pieceName;

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

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        add(sidebar);
        addMenu();

        setVisible(true);
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
        //addMenuItem(codeMenu, new RemovePieceAction(), KeyStroke.getKeyStroke("control R"));
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


    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
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
     * Represents clear action to clear all saved memory from current state of the program
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
     * Represents clear action to clear all saved memory from current state of the program
     */
    private class AddPieceAction extends AbstractAction {

        AddPieceAction() {
            super("Add New Piece");
        }

        // EFFECTS: Runs when the clear action occurs (Whenever the clear option is chosen by the user)
        @Override
        public void actionPerformed(ActionEvent ae) {
            selectedPiece = new Piece("Test", new ArrayList<>());
            SimplePianoGUI sp = new SimplePianoGUI(selectedPiece);
            memory.addPiece(selectedPiece);
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

}

// TODO: Add buttons to main frame. Dropdown menu for choosing a piece from composed pieces in memory,
// Then upon choosing a piece which is noted in local memory there are buttons for playing the piece,
// opening a panel displaying information about the piece, and a button for adding notes, and a button for deleting
// notes from the piece. Piece addition and deletion is handled by buttons on top menu bar.


