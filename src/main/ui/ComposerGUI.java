package ui;

import javax.swing.*;
import exception.PieceNotFoundException;
import model.*;
import org.jfugue.player.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ComposerGUI extends JFrame {
    private JTabbedPane sidebar;
    private final Composer composer = new Composer();
    private PiecesMemory memory;
    private String pieceName;

    public static void main(String[] args) {
        new ComposerGUI();
    }

    //MODIFIES: this
    //EFFECTS: creates ComposerGUI
    private ComposerGUI() {
        super("Java Music Composer V0.2");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loadAppliances();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    //EFFECTS: returns SmartHome object controlled by this UI
    public SmartHome getSmartHome() {
        return smartHome;
    }

    //MODIFIES: this
    //EFFECTS: installs several appliances and sets no one home
    private void loadAppliances() {
        Appliance fridge = new Refrigerator(5);
        Appliance oven = new Oven(0);
        Appliance ac = new HeatingAC(18);
        Appliance fireplace = new Fireplace(0);

        smartHome.install(fridge);
        smartHome.install(oven);
        smartHome.install(ac);
        smartHome.install(fireplace);

        ac.setRunsWhileAway(true);
        fridge.setRunsWhileAway(true);

        smartHome.leaveHome();
    }

    //MODIFIES: this
    //EFFECTS: adds home tab, settings tab and report tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel settingsTab = new SettingsTab(this);
        JPanel reportTab = new ReportTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(settingsTab, SETTINGS_TAB_INDEX);
        sidebar.setTitleAt(SETTINGS_TAB_INDEX, "Settings");
        sidebar.add(reportTab, REPORT_TAB_INDEX);
        sidebar.setTitleAt(REPORT_TAB_INDEX, "Report");
    }

    // EFFECTS: Adds menu bar to GUI.
    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        addMenuItem(fileMenu, new SaveAction(),
                KeyStroke.getKeyStroke("control S"));
        addMenuItem(fileMenu, new LoadAction(), KeyStroke.getKeyStroke("control L"));
        menuBar.add(fileMenu);
        // TODO BELOW

        JMenu codeMenu = new JMenu("Code");
        codeMenu.setMnemonic('C');
        addMenuItem(codeMenu, new AddCodeAction(), null);
        addMenuItem(codeMenu, new RemoveCodeAction(), null);
        menuBar.add(codeMenu);

        JMenu systemMenu = new JMenu("System");
        systemMenu.setMnemonic('y');
        addMenuItem(systemMenu, new ArmAction(),
                KeyStroke.getKeyStroke("control A"));
        addMenuItem(systemMenu, new DisarmAction(),
                KeyStroke.getKeyStroke("control D"));
        menuBar.add(systemMenu);

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
                JOptionPane.showMessageDialog(null, "Save Successful!", "Save",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not save memory.", "Save",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Represents save action for saving the current state of the program.
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
                JOptionPane.showMessageDialog(null, "Load Successful!", "Load",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not retrieve memory.", "Save",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}




