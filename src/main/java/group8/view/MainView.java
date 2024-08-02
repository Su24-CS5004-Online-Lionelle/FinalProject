package group8.view;

import com.formdev.flatlaf.FlatDarkLaf;
import group8.view.helpers.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * MainView class represents the main user interface for the Trivia Generator application.
 */
public class MainView extends JFrame {
    private JFrame frame;
    private JList<String> apiList;
    private JList<String> userList;
    private DefaultListModel<String> apiListModel;
    private DefaultListModel<String> userListModel;
    private JButton toUserButton;
    private JButton toApiButton;
    private MainViewState state;

    /**
     * Constructor for MainView. Sets up the main interface components.
     */
    public MainView() {
        state = new MainViewState();

        // Set the FlatLaf Dark look and feel
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Frame creation
        frame = new JFrame("Trivia Generator 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Welcome to Trivia Generator 1.0", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 1;

        // Left Panel
        JPanel leftPanel = createLeftPanel();
        // Right Panel
        JPanel rightPanel = createRightPanel();
        // Center Panel
        JPanel centerPanel = createCenterPanel();

        // Add panels to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(leftPanel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        mainPanel.add(rightPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        mainPanel.add(centerPanel, gbc);

        // Add main panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Creates the left panel which contains the API list and associated controls.
     *
     * @return JPanel representing the left panel.
     */
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
        apiListModel = new DefaultListModel<>();
        apiList = new JList<>(apiListModel);
        apiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        apiList.addListSelectionListener(e -> updateButtons());
        JScrollPane apiScrollPane = new JScrollPane(apiList);

        JPanel apiTopPanel = createApiTopPanel();
        JPanel apiBottomPanel = createApiBottomPanel();

        GridBagConstraints leftGbc = new GridBagConstraints();
        leftGbc.gridx = 0;
        leftGbc.gridy = 0;
        leftGbc.weightx = 1;
        leftGbc.weighty = 0;
        leftPanel.add(apiTopPanel, leftGbc);

        leftGbc.gridy = 1;
        leftGbc.weighty = 1;
        leftGbc.fill = GridBagConstraints.BOTH;
        leftPanel.add(apiScrollPane, leftGbc);

        leftGbc.gridy = 2;
        leftGbc.weighty = 0;
        leftPanel.add(apiBottomPanel, leftGbc);

        return leftPanel;
    }

    /**
     * Creates the right panel which contains the user list and associated controls.
     *
     * @return JPanel representing the right panel.
     */
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));
        userListModel = new DefaultListModel<>();
        userList = new JList<>(userListModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.addListSelectionListener(e -> updateButtons());
        JScrollPane userScrollPane = new JScrollPane(userList);

        JPanel userTopPanel = createUserTopPanel();
        JPanel userBottomPanel = createUserBottomPanel();

        GridBagConstraints rightGbc = new GridBagConstraints();
        rightGbc.gridx = 0;
        rightGbc.gridy = 0;
        rightGbc.weightx = 1;
        rightGbc.weighty = 0;
        rightPanel.add(userTopPanel, rightGbc);

        rightGbc.gridy = 1;
        rightGbc.weighty = 1;
        rightGbc.fill = GridBagConstraints.BOTH;
        rightPanel.add(userScrollPane, rightGbc);

        rightGbc.gridy = 2;
        rightGbc.weighty = 0;
        rightPanel.add(userBottomPanel, rightGbc);

        return rightPanel;
    }

    /**
     * Creates the center panel which contains the buttons for transferring items
     * between the API list and the user list.
     *
     * @return JPanel representing the center panel.
     */
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints arrowGbc = new GridBagConstraints();
        arrowGbc.gridx = 0;
        arrowGbc.gridy = GridBagConstraints.RELATIVE;
        arrowGbc.anchor = GridBagConstraints.CENTER;
        arrowGbc.insets = new Insets(10, 0, 10, 0);
        toUserButton = new JButton(">>");
        toApiButton = new JButton("<<");
        toUserButton.setEnabled(false);
        toApiButton.setEnabled(false);
        centerPanel.add(toUserButton, arrowGbc);
        centerPanel.add(toApiButton, arrowGbc);
        return centerPanel;
    }

    /**
     * Creates the top panel for the API list, which contains the filter and reset
     * filter buttons.
     *
     * @return JPanel representing the top panel for the API list.
     */
    private JPanel createApiTopPanel() {
        JPanel apiTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton filterButton = createIconButton("src/main/java/group8/view/icons/filter.png", 20, 20);
        JButton resetFilterButton = createIconButton("src/main/java/group8/view/icons/reload.png", 20, 20);
        filterButton.addActionListener(new FilterActionListener(frame, this, state));
        resetFilterButton.addActionListener(e -> resetFilters());
        apiTopPanel.add(filterButton);
        apiTopPanel.add(resetFilterButton);
        return apiTopPanel;
    }

    /**
     * Creates the bottom panel for the API list, which contains the generate new
     * list button.
     *
     * @return JPanel representing the bottom panel for the API list.
     */
    private JPanel createApiBottomPanel() {
        JPanel apiBottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton apiPullButton = new JButton("Generate New List");
        apiPullButton.addActionListener(new ApiPullActionListener(apiListModel));
        apiBottomPanel.add(apiPullButton);
        return apiBottomPanel;
    }

    /**
     * Creates the top panel for the user list, which contains the sort combo box.
     *
     * @return JPanel representing the top panel for the user list.
     */
    private JPanel createUserTopPanel() {
        JPanel userTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JComboBox<String> sortComboBox = new JComboBox<>(new String[]{"Sort by...", "Category", "Difficulty", "Question Type"});
        sortComboBox.addActionListener(new SortActionListener(userListModel));
        userTopPanel.add(sortComboBox);
        return userTopPanel;
    }

    /**
     * Creates the bottom panel for the user list, which contains the save and load
     * buttons.
     *
     * @return JPanel representing the bottom panel for the user list.
     */
    private JPanel createUserBottomPanel() {
        JPanel userBottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        saveButton.addActionListener(new SaveActionListener(userListModel));
        loadButton.addActionListener(new LoadActionListener(userListModel));
        userBottomPanel.add(saveButton);
        userBottomPanel.add(loadButton);
        return userBottomPanel;
    }

    /**
     * Creates a button with an icon image.
     *
     * @param path  the path to the image file
     * @param width the desired width of the icon
     * @param height the desired height of the icon
     * @return JButton with the specified icon
     */
    private JButton createIconButton(String path, int width, int height) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (img != null) {
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JButton button = new JButton(new ImageIcon(scaledImg));
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setMargin(new Insets(0, 0, 0, 0));
            return button;
        } else {
            return new JButton(); // Return an empty button if the image fails to load
        }
    }

    /**
     * Updates the state of the arrow buttons based on list selections.
     */
    private void updateButtons() {
        toUserButton.setEnabled(apiList.getSelectedIndex() != -1);
        toApiButton.setEnabled(userList.getSelectedIndex() != -1);
    }

    /**
     * Resets the filters applied to the API list.
     */
    private void resetFilters() {
        // Logic to reset filters
    }

    /**
     * Main method to launch the Trivia Generator application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainView::new);
    }
}
