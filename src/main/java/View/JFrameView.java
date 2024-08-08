package View;

import Model.SortFilter.ColumnData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The view to handle the displaying of information.
 */
public class JFrameView extends JFrame implements IView {
    /** Label to display prompt for the input field. */
    private JLabel prompt, title;
    /** Text field to enter search criteria or desired add or remove name, int or range. */
    private JTextField input;
    /** JComboBoxes to display filter and sort choices for user to select. */
    private JComboBox<String> filterChoice, sortChoice;
    /** List of elements to filter and sort by.  */
    private List<String> items, sortOrder;
    /** Buttons for searching players, showing list, loading roster, exporting roster, clearing display, adding & removing players and displaying help message. */
    private JButton searchButton, showListButton, loadButton, exportButton, clearButton, exitButton, addButton, removeButton, helpButton;
    /** Lower text area to display information. */
    private JTextArea lowerTextArea;

    /**
     * Contructor for the JFrameView.
     * @param caption String to display name of program.
     */
    public JFrameView(String caption) {
        super(caption);

        setSize(800, 800);
        // centers window
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // sub-panel to display logo and title of application
        Background titlePanel = new Background("src/main/java/View/View_Images/hardwood.jpg");

        // Add logo image
        JLabel imageLabel = createImageLabel("src/main/java/View/View_Images/nba_logo2.jpg", 80, 80);
        imageLabel.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        titlePanel.add(imageLabel);

        // Add JLabel to display title of application
        title = new JLabel("STAT TRACKER");
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.PLAIN, 45));
        titlePanel.add(title);

        // Add titlePanel to the main JFrame
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titlePanel);

        // sub-panel to hold JTextField and JComboBox
        Background searchPanel = new Background("src/main/java/View/View_Images/hardwood.jpg");

        // adding input text field
        input = new JTextField(20);
        // places textfield below JLabel
        input.setMaximumSize(input.getPreferredSize());
        searchPanel.add(input);

        // JComboBox setup
        // setting drop down items
        items = new ArrayList<>();
        items.add("First name");
        items.add("Last name");
        items.add("Position");
        items.add("Height");
        items.add("Draft year");
        items.add("Draft round");
        items.add("Draft pick");
        items.add("Team");
        items.add("Conference");
        items.add("Points per game");
        items.add("Rebounds per game");
        items.add("Assists per game");
        items.add("Blocks per game");
        items.add("Steals per game");
        items.add("Minutes per game");
        items.add("Field goal percentage");
        items.add("Free throw percentage");
        items.add("3 point percentage");

        // setting sort order list
        sortOrder = new ArrayList<>();
        sortOrder.add("Ascending");
        sortOrder.add("Descending");

        filterChoice = new JComboBox<>(items.toArray(new String[0]));

        filterChoice.setMaximumSize(filterChoice.getPreferredSize());
        searchPanel.add(filterChoice);

        sortChoice = new JComboBox<>(sortOrder.toArray(new String[0]));
        searchPanel.add(sortChoice);

        // Add search button
        searchButton = new JButton("Search");
        searchButton.setActionCommand("search");
        searchPanel.add(searchButton);

        // Add "add" and "remove" buttons
        addButton = new JButton("Add");
        addButton.setActionCommand("add");
        searchPanel.add(addButton);

        removeButton = new JButton("Remove");
        removeButton.setActionCommand("remove");
        searchPanel.add(removeButton);

        // Add searchPanel to the main JFrame
        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(searchPanel);

        // Create sub-panel for buttons
        Background buttonPanel = new Background("src/main/java/View/View_Images/hardwood.jpg");
//        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Add the buttons
        showListButton = new JButton("Show Current Roster");
        showListButton.setActionCommand("showRoster");
        buttonPanel.add(showListButton);

        exportButton = new JButton("Export Current Roster");
        exportButton.setActionCommand("export");
        buttonPanel.add(exportButton);

        loadButton = new JButton("Load Roster");
        loadButton.setActionCommand("load");
        buttonPanel.add(loadButton);

        clearButton = new JButton("Clear Display");
        clearButton.setActionCommand("clear");
        buttonPanel.add(clearButton);

        helpButton = new JButton("Help");
        helpButton.setActionCommand("help");
        buttonPanel.add(helpButton);

        exitButton = new JButton("Exit Program");
        exitButton.setActionCommand("exit");
        buttonPanel.add(exitButton);

        // Add buttonPanel to the main JFrame
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(buttonPanel);

        // Create lower display panel
        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(Color.LIGHT_GRAY);
        lowerPanel.setLayout(new BorderLayout());

        // Initialize JTextArea
        lowerTextArea = new JTextArea();
        lowerTextArea.setOpaque(false);
        lowerTextArea.setEditable(false);

        // Add scroll bar to lower panel
        lowerPanel.add(new JScrollPane(lowerTextArea), BorderLayout.CENTER);

        display(getHelp());

        // Add lower panel to the frame
        this.add(lowerPanel);
    }

    /**
     * Displays information to the lower panel of the JFrame.
     * @param text String that the user wants to display.
     */
    @Override
    public void display(String text) {
        lowerTextArea.append(text + "\n");
    }

    /**
     * Sets up ActionListeners for JButtons.
     * @param clicks Button clicks.
     */
    @Override
    public void setListeners(ActionListener clicks) {
        this.searchButton.addActionListener(clicks);
        this.showListButton.addActionListener(clicks);
        this.loadButton.addActionListener(clicks);
        this.exportButton.addActionListener(clicks);
        this.clearButton.addActionListener(clicks);
        this.exitButton.addActionListener(clicks);
        this.addButton.addActionListener(clicks);
        this.removeButton.addActionListener(clicks);
        this.helpButton.addActionListener(clicks);
    }

    /**
     * Gets the String input from the JTextField.
     * @return String user input
     */
    @Override
    public String getInputString() {
        return input.getText();
    }

    /**
     * Gets the user selected filter choice from JComboBox.
     * @return ColumnData filter enum
     */
    @Override
    public ColumnData getFilterChoice() {
        // casting Object to string to pass into method
        return ColumnData.fromColumnName((String) filterChoice.getSelectedItem());
    }

    /**
     * Gets the user selected sort choice from JComboBox.
     * @return boolean true for Ascending, false for Descending
     */
    @Override
    public boolean getSortChoice() {
        if (sortChoice.getSelectedItem() == "Ascending") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Clears the JTextField.
     */
    @Override
    public void clearInputField() {
        input.setText("");
    }

    /**
     * Clears the lower panel display in the JFrame.
     */
    @Override
    public void clearDisplay() {
        lowerTextArea.setText("");
    }

    /**
     * Provide a help menu to guide user on how to use the program.
     *
     * @return a string containing instructions on what the program does/how to use it
     */
    @Override
    public String getHelp() {
        return "Instructions: \n"
                + "1. There is an empty text field you can type into to search for specific players.\n"
                + "2. Next to it is a drop down menu that you can use to filter your search.\n"
                + "E.g. If you want to see all the players in the league with the first name 'Ray', you'd select 'First Name'"
                + " from the drop down menu and type 'Ray' into the text field.\n"
                + "There are many different filter options to pick from, most of which will be integers or doubles.\n"
                + "You can also use operators to help refine your search (==, !=, ~=, >=, <=, >, <) \n"
                + "E.g. If you want to see players who were drafted in the year 2000, you'd select the 'Draft Year' option"
                + " from the drop down menu and type 2000 into the text field.\n"
                + "If you wanted to see the players who were drafted after the year 2000,"
                + "you'd type '> 2000' (not including year 2000) or '>= 2000' (including year 2000) into the text field.\n"
                + "3. You can adjust the order that your search results are displayed. Either ascending (first to last) or descending (last to first).\n"
                + "4. The 'Add' button allows you to add a player or a range of players to your roster.\n"
                + "5. The 'Remove' button allows you to remove a player or a range of players from your roster.\n"
                + "6. The 'Show Current List' button shows you the list of players you have added to your roster.\n"
                + "7. The 'Export Current List' button saves your current list of players to a specified file format.\n"
                + "8. The 'Load List' button loads a roster of players based on the file name given.\n"
                + "9. The 'Clear Display' button clears the display of any information.\n"
                + "10. The 'Exit Program' button closes the GUI.\n";
    }

    private JLabel createImageLabel(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return new JLabel(imageIcon);
    }

    /**
     * Sets JFrame to visible.
     */
    @Override
    public void start() {
        setVisible(true);
    }
}
