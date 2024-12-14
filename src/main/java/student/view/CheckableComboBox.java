package student.view;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom component that makes combobox items checkable.
 * Can return checked items as string array.
 */
public class CheckableComboBox extends JComboBox<String> {

    private final List<String> checkedItems = new ArrayList<>();
    private final String placeholder; // Placeholder text
    private Font pokemonFont;

    public CheckableComboBox(String[] items, String placeholder) {
        super(items);
        this.placeholder = placeholder;

        // Set custom renderer
        setRenderer(new CheckableComboBoxRenderer());

        // Set placeholder text when no item is selected
        setSelectedIndex(-1); // Ensure placeholder is visible initially

        // Set the font for the combo box
        // try to get pokemon font from resources
        try {
            pokemonFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/gbboot.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pokemonFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            pokemonFont = new Font("Courier New", Font.BOLD, 20);
        }
        setFont(pokemonFont);

        // Add action listener to handle item selection
        addActionListener(e -> {
            int selectedIndex = getSelectedIndex(); // Get the index of the selected item
            if (selectedIndex != -1) {
                String selectedItem = (String) getItemAt(selectedIndex); // Get the selected item
                if (checkedItems.contains(selectedItem)) {
                    // If item is already checked, uncheck it
                    checkedItems.remove(selectedItem);
                } else {
                    // If item is not checked, check it
                    checkedItems.add(selectedItem);
                }
                // Update the model to reflect the new state
                setModel(new DefaultComboBoxModel<>(getItems()));
                
                // Keep the combo box open
                SwingUtilities.invokeLater(() -> showPopup());
            }
        });

        // Add mouse listener to open the combo box on focus
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup();
            }
        });
    }

    // Method to return items as array
    public String[] getItems() {
        String[] items = new String[getItemCount()];
        for (int i = 0; i < getItemCount(); i++) {
            items[i] = (String) getItemAt(i);
        }
        return items;
    }

    // Method to get checked items
    public List<String> getCheckedItems() {
        return checkedItems;
    }

    // Custom renderer to show checkboxes in the combo box
    private class CheckableComboBoxRenderer extends JPanel implements ListCellRenderer<String> {
        private final JCheckBox checkBox;

        public CheckableComboBoxRenderer() {
            setLayout(new BorderLayout());
            checkBox = new JCheckBox();
            checkBox.setFont(pokemonFont); // Set font for JCheckBox
            add(checkBox, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            if (index == -1) {
                // Render placeholder text if no item is selected
                checkBox.setText(placeholder);
                checkBox.setSelected(false);
                checkBox.setEnabled(false); // Make it unselectable
            } else {
                // Render actual item
                checkBox.setText(value);
                checkBox.setSelected(checkedItems.contains(value));
                checkBox.setEnabled(true);
            }
            // Handle selection
            checkBox.setOpaque(true);
            setOpaque(true);
            return this; // Return this panel as the renderer component
        }
    }
}
