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
    /** List to keep track of checked items. */
    private final List<String> checkedItems = new ArrayList<>();
    /** Placeholder text on menu, acts as a title ("Types"). */
    private final String placeholder;
    /** Variable to store pokemon font. */
    private Font pokemonFont;

    /**
     * Constructs a checkableComboBox with the specified items and placeholder text.
     *
     * @param items       The array of items to be displayed in the combo box.
     * @param placeholder The placeholder text to display when no item is selected.
     */
    public CheckableComboBox(String[] items, String placeholder) {
        super(items);
        this.placeholder = placeholder;

        // Set custom renderer
        setRenderer(new CheckableComboBoxRenderer());

        // Set placeholder text when no item is selected
        setSelectedIndex(-1);

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
            int selectedIndex = getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedItem = (String) getItemAt(selectedIndex);
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

    /**
     * Method returns items as array.
     * @return string array
     */
    public String[] getItems() {
        String[] items = new String[getItemCount()];
        for (int i = 0; i < getItemCount(); i++) {
            items[i] = (String) getItemAt(i);
        }
        return items;
    }

    /**
     * Method gets checked items as list.
     * @return string list
     */
    public List<String> getCheckedItems() {
        return checkedItems;
    }

    /** Custom renderer to show checkboxes in the combo box */
    private class CheckableComboBoxRenderer extends JPanel implements ListCellRenderer<String> {
        /** The checkbox component for rendering each item. */
        private final JCheckBox checkBox;

        /**
         * Constructs a CheckableComboBoxRenderer with a checkbox for each item.
         */
        public CheckableComboBoxRenderer() {
            setLayout(new BorderLayout());
            checkBox = new JCheckBox();
            checkBox.setFont(pokemonFont);
            add(checkBox, BorderLayout.CENTER);
        }

        /**
         * Returns a component that displays the value of each item in the combobox.
         * This method is called by the combobox each time it needs to display an item.
         *
         * @param list The JList that displays the combo box items.
         * @param value The value of the item to be rendered.
         * @param index The index of the item in the list.
         * @param isSelected Whether the item is selected.
         * @param cellHasFocus Whether the item has focus.
         * @return A component configured to display the specified item.
         */
        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            if (index == -1) {
                // Render placeholder text if no item is selected
                checkBox.setText(placeholder);
                checkBox.setSelected(false);
                checkBox.setEnabled(false);
            } else {
                // Render actual item
                checkBox.setText(value);
                checkBox.setSelected(checkedItems.contains(value));
                checkBox.setEnabled(true);
            }
            // Handle selection
            checkBox.setOpaque(true);
            setOpaque(true);
            return this;
        }
    }
}
