package student.view;
import javax.swing.*;
import java.util.List;

import student.controller.PokedexController;
import student.model.PokeRecord;
import student.view.Components.GridBackground;
import student.view.Components.ListItem;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.ArrayList;

public class PokemonTeamPanel extends JPanel {
        private static PokemonTeamPanel instance;
        PokedexController controller = new PokedexController();
        List<PokeRecord> pokemonTeam;
        List<ListItem> customRectTeam = new ArrayList<>();
        JPanel teamPanel = new JPanel();

        private PokemonTeamPanel() throws IOException {
            // prevents instantiation
            refreshPanel();
        }

        // Public method to provide access to the instance
        public static PokemonTeamPanel getInstance() throws IOException {
            if (instance == null) {
                instance = new PokemonTeamPanel();
            }
            return instance;
        }

        /**
         * Initializes the team view.
         *
         * @throws IOException
         */
        public void initializePanel() throws IOException {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            pokemonTeam = controller.getAllPokemonInTeam();
            for (PokeRecord pokemon : pokemonTeam) {
                ListItem listItem = new ListItem(pokemon);
                listItem.setPreferredSize(new Dimension(400, 100)); // Set fixed size for each ListItem
                listItem.setMaximumSize(new Dimension(400, 100)); // Ensure max height is 100
                customRectTeam.add(listItem);
            }

            for (ListItem item : customRectTeam) {
                teamPanel.add(item);
            }

            teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));

            if (pokemonTeam.size() == 0) {
                // Set GridBackground as the background
                GridBackground gridBackground = new GridBackground();
                gridBackground.setLayout(new BorderLayout());
                gridBackground.add(teamPanel, BorderLayout.CENTER);

                teamPanel.setOpaque(false);

                // Create a JPanel to show the message
                JPanel messagePanel = new JPanel();
                messagePanel.setLayout(new BorderLayout());
                JLabel messageLabel = new JLabel("Pokemon team is empty", JLabel.CENTER);
                messagePanel.setOpaque(false);
                messageLabel.setFont(PokedexView.getPokemonFont().deriveFont(30f));
                messagePanel.add(messageLabel, BorderLayout.CENTER);


                // Add the messagePanel to the center of gridBackground
                gridBackground.add(messagePanel, BorderLayout.CENTER);


                JScrollPane scrollPane = new JScrollPane(gridBackground);
                scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                // Remove all components from the main panel and add the scrollPane
                this.removeAll();
                this.add(scrollPane, BorderLayout.CENTER);
                this.revalidate();
                this.repaint();
            } else {
                JScrollPane scrollPane = new JScrollPane(teamPanel);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                // Remove all components from the main panel and add the scrollPane
                scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
                this.removeAll();
                this.add(scrollPane, BorderLayout.CENTER);
                this.revalidate();
                this.repaint();
            }
        }

        public void refreshPanel() throws IOException {
            if (pokemonTeam != null && customRectTeam != null) {
                pokemonTeam.clear();
                customRectTeam.clear();
            }
            teamPanel.removeAll();
            this.removeAll();

            initializePanel();

            teamPanel.revalidate();
            teamPanel.repaint();

            this.revalidate();
            this.repaint();

        }

        // Method to add mouse listener to list items
        public void addMouseListenerToListItems(MouseAdapter mouseAdapter) {
            for (ListItem listItem : customRectTeam) {
                listItem.addMouseListener(mouseAdapter);
            }
        }

        // placeholder to make visualizing panel easier
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.blue);
            g.fillRect(0, 0, 390, 440);
            // visibility true when team view if off
            this.setVisible(true);
        }
}
