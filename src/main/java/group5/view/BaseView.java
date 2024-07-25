package group5.view;

import group5.controller.IFeature;
import group5.model.beans.MBeans;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.stream.Stream;

public class BaseView extends JFrame implements IView {
    private final static String APP_TITLE = "App Title";
    private final static int DEFAULT_WIDTH = 1024;
    private final static int DEFAULT_HEIGHT = 600;


    FilterPane filterPane;
    ListPaneV2 listPane;
    DetailsPane detailsPane;


    public BaseView() {
        super(APP_TITLE);

        // Let the backstage crew do their thing
        setVisible(false);

        // Set System Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating Base Pane
        JPanel basePane = new JPanel();
        basePane.setLayout(new BorderLayout());
        add(basePane);

        // Creating the Functional Panes
        filterPane = new FilterPane();
        listPane = new ListPaneV2();
        detailsPane = new DetailsPane();


        // Attaching the Functional Panes to the Base Pane
        basePane.add(filterPane, BorderLayout.WEST);
        basePane.add(listPane, BorderLayout.CENTER);
        basePane.add(detailsPane, BorderLayout.EAST);

    }


    @Override
    public void display() {
        filterPane.setVisible(true);
        listPane.setVisible(true);
        detailsPane.setVisible(true);
        setVisible(true);
    }

    @Override
    public void bindFeatures(IFeature features) {
        System.out.println("[BaseView] BaseView is binding features...");
        filterPane.bindFeatures(features);
        listPane.bindFeatures(features);
        detailsPane.bindFeatures(features);
    }

    /**
     * Set the user list pane entries
     * Prompts the view to create a new tab pane if the userListId is not found
     * @param userListIndex the index of the user list
     * @param mbeans the MBeans to set
     */
    public void setUserTableRecords(int userListIndex, Stream<MBeans> mbeans) {
        System.out.println("[BaseView] setUserTableRecords");
        listPane.setUserTableRecords(userListIndex, mbeans);
    }

    public void createUserTable(String userListName) {
        System.out.println("[BaseView] createUserTable");
        listPane.createUserTableTab(userListName);
    }

    public int getCurrentTab() {
        return listPane.getCurrentTab();
    }

    @Override
    public void setDetailsPaneEntry(MBeans record) {
        System.out.println("[BaseView] calling setDetailsPaneEntry, passing MBeans to detailsPane");
        detailsPane.setMedia(record);
    }

    @Override
    public void setSourceTableRecords(Stream<MBeans> mbeans) {
        Collection<MBeans> records = mbeans.toList();
        System.out.println("[BaseView] setMainTableRecords");
        listPane.setSourceTableRecords(records.stream());
        filterPane.setMovies(records.stream());
    }

    @Override
    public FilterPane getFilterPane() {
        return filterPane;
    }



}
