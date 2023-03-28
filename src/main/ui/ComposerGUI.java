package ui;

import javax.swing.*;

import model.*;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;

public class ComposerGUI extends JFrame {
    private JTabbedPane sidebar;
    private final Composer composer = new Composer();
    private PiecesMemory memory;
    private String pieceName;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar2;
    private JTree tree1;

    public static void main(String[] args) {
        new ComposerGUI();
    }

    //MODIFIES: this
    //EFFECTS: creates ComposerGUI
    private ComposerGUI() {
        super("Java Music Composer V0.2");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initializationLoad();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        add(sidebar);
        addMenu();

        setVisible(true);
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
            super("Clear");
        }

        // EFFECTS: Runs when the clear action occurs (Whenever the clear option is chosen by the user)
        @Override
        public void actionPerformed(ActionEvent ae) {
             SimplePianoGUI sp = new SimplePianoGUI();
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




