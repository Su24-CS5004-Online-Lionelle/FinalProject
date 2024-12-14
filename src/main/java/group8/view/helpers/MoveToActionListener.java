package group8.view.helpers;

import group8.controller.MainController;
import group8.model.TriviaQuestion;
import group8.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MoveToActionListener implements ActionListener {
    private MainView view;
    private MainController controller;
    private JList<TriviaQuestion> sourceList;
    private boolean moveToUser; // true if moving to user, false if moving to API

    public MoveToActionListener(MainView view, MainController controller, JList<TriviaQuestion> sourceList, boolean moveToUser) {
        this.view = view;
        this.controller = controller;
        this.sourceList = sourceList;
        this.moveToUser = moveToUser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TriviaQuestion selectedQuestion = sourceList.getSelectedValue();
        if (selectedQuestion != null) {
            if (moveToUser) {
                controller.moveToUserCollection(selectedQuestion);
            } else {
                controller.moveToApiCollection(selectedQuestion);
            }
            view.updateApiListModel(controller.getApiQuestions());
            view.updateUserListModel(controller.getUserQuestions());
            view.updateButtons();
            // Reapply filters after the move
            FilterActionListener filterActionListener = new FilterActionListener(view.getFrame(), view, view.getCheckboxState(), controller);
            filterActionListener.applyFilters();
            SortActionListener sortActionListener = new SortActionListener(controller, view, view.getCheckboxState());
            sortActionListener.sortUserList();
        }
    }
}
