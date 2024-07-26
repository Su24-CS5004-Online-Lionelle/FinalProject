package group5.controller;

import java.util.stream.Stream;

import javax.swing.JOptionPane;

import group5.model.Filter.FilterHandler;
import group5.model.IModel;
import group5.model.beans.MBeans;
import group5.view.IView;

/**
 * Controller class for the program.
 */
public class Controller implements IController, IFeature {

    /**
     * The model object representing the movie database.
     */
    IModel model;
    /**
     * The view object representing the user interface.
     */
    IView view;

    /**
     * Constructor for the controller.
     *
     * @param model the model object representing the movie database
     * @param view the view object representing the user interface
     */
    public Controller(IModel model, IView view) {

        System.out.println("[Controller] Controller constructor called");
        this.model = model;
        this.view = view;

        model.loadSourceData();
        // bindFeatures accept an IFeature interface, which is the controller itself
        view.bindFeatures(this);
        view.setSourceTableRecords(model.getSourceLists());

        // String sampleDataPath = "data/samples/source.json";
        // List<MBeans> records = MBeansLoader.loadMediasFromFile(sampleDataPath, Formats.JSON);
        model.loadWatchList("./data/samples/watchlist.json");
        
        for (int i = 0; i < model.getUserListCount(); i++) {
            view.createUserTable(model.getUserListName(i));
            view.setUserTableRecords(i,model.getWatchLists(i));
        }

    }

    @Override
    public void exportListToFile(String filepath) {
        String currentTab = "\"SourceTable\"";
        if (view.getCurrentTab() > 0) {
            currentTab = "\"UserTable " + (view.getCurrentTab() - 1 + "\"");
        }
        JOptionPane.showMessageDialog(null, "Exporting data from " + currentTab + " to " + filepath);
    }

    @Override
    public void addListFromFile(String filepath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("[Controller.java] Unimplemented method 'addListFromFile'");
    }

    @Override
    public void showRecordDetails(MBeans record) {
        System.out.println("[Controller] showRecordDetails called");
        view.setDetailsPaneEntry(record);
    }

    @Override
    public void applyFilters() {
        String titleFilter = view.getFilterPane().getFilteredTitle();
        String contentTypeFilter = view.getFilterPane().getFilteredContentType();
        String genreFilter = view.getFilterPane().getFilteredGenre();
        String mpaRatingFilter = view.getFilterPane().getFilteredMpaRating();
        String releasedMin = view.getFilterPane().getFilteredReleasedMin();
        String releasedMax = view.getFilterPane().getFilteredReleasedMax();
        String imdbRatingMin = view.getFilterPane().getFilteredImdbRatingMin();
        String imdbRatingMax = view.getFilterPane().getFilteredImdbRatingMax();
        String boxOfficeEarningsMin = view.getFilterPane().getFilteredBoxOfficeEarningsMin();
        String boxOfficeEarningsMax = view.getFilterPane().getFilteredBoxOfficeEarningsMax();
        String directorFilter = view.getFilterPane().getFilteredDirectorFilter();
        String actorFilter = view.getFilterPane().getFilteredActorFilter();
        String writerFilter = view.getFilterPane().getFilteredWriterFilter();
        String languageFilter = view.getFilterPane().getFilteredLanguageFilter();
        String countryOfOriginFilter = view.getFilterPane().getFilteredCountryOfOriginFilter();

        // Concatenate using StringBuilder with commas
        StringBuilder filters = new StringBuilder();
        filters.append(titleFilter).append(", ")
                .append(contentTypeFilter).append(", ")
                .append(genreFilter).append(", ")
                .append(mpaRatingFilter).append(", ")
                .append(releasedMin).append(", ")
                .append(releasedMax).append(", ")
                .append(imdbRatingMin).append(", ")
                .append(imdbRatingMax).append(", ")
                .append(boxOfficeEarningsMin).append(", ")
                .append(boxOfficeEarningsMax).append(", ")
                .append(directorFilter).append(", ")
                .append(actorFilter).append(", ")
                .append(writerFilter).append(", ")
                .append(languageFilter).append(", ")
                .append(countryOfOriginFilter);

        // send the list of filters to the filter handler w/ the stream
        FilterHandler handler = new FilterHandler();
        handler.filter(filters.toString(), getRecordsForCurrentTab());

    }

    /**
     * Clear the filters in the FilterPane.
     */
    @Override
    public void clearFilters() {
        view.getFilterPane().resetFilterOptions();
        // TODO: set the tables in the view to unfiltered
    }

    /**
     * Main entry point for the controller.
     */
    @Override
    public void go() {
        System.out.println("[Controller] Controller.go() called");

        view.display();
    }

    public void removeFromWatchList(MBeans mbean, int userListIndex) {
        // TODO Auto-generated method stub
        System.out.println("[Controller] removeFromWatchList called to remove " + mbean.getTitle() + " from user list index " + userListIndex);
        // TODO: Waiting for IModel implementation
        // model.removeFromWatchList(mbean, userListIndex);

        // Update the affected table in the view
        view.setUserTableRecords(userListIndex, model.getWatchLists(userListIndex));

    }

    public void addToWatchList(MBeans record, int userListIndex) {
        System.out.println("[Controller] addToWatchList called to add " + record.getTitle() + " to user list index " + userListIndex);
        model.addToWatchList(record, userListIndex);
        view.setUserTableRecords(userListIndex, model.getWatchLists(userListIndex));
        //throw new UnsupportedOperationException("[Controller.java] Unimplemented method 'addMovieToList'");
    }

    public void changeRating(MBeans record, double rating) {
        model.updateUserRating(record, rating);
        // TODO: Check if Views are correctly updated
    }

    public void changeWatchedStatus(MBeans mbean, boolean watched) {
        model.updateWatched(mbean, watched);

        // TODO: Check if Views are correctly updated
    }

    /**
     * Get the records for the currently active tab.
     *
     * @return a stream of MBeans
     */
    private Stream<MBeans> getRecordsForCurrentTab() {
        int currentTab = view.getCurrentTab();
        if (currentTab == 0) {
            return model.getSourceLists();
        } else if (currentTab > 0) {
            return model.getWatchLists(currentTab - 1); // decrement by 1 to get the user-defined list index
        } else {
            return null;
        }
    }

    public void handleTabChange(int tabIndex) {
        System.out.println("[Controller] Handling event: tab changed to " + tabIndex);
        // view.getFilterPane().setMovies(getRecordsForCurrentTab());
    }

}
