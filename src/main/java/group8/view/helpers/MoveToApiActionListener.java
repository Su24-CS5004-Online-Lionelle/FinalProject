package group8.view.helpers;

import group8.model.TriviaQuestion;
import group8.model.helpers.QuestionExchange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveToApiActionListener implements ActionListener {
    private JList<String> userList;
    private DefaultListModel<String> userListModel;
    private DefaultListModel<String> apiListModel;
    private QuestionExchange questionExchange;

    public MoveToApiActionListener(JList<String> userList, DefaultListModel<String> userListModel,
                                   DefaultListModel<String> apiListModel, QuestionExchange questionExchange) {
        this.userList = userList;
        this.userListModel = userListModel;
        this.apiListModel = apiListModel;
        this.questionExchange = questionExchange;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = userList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedValue = userListModel.getElementAt(selectedIndex);
            TriviaQuestion selectedQuestion = questionExchange.getUserCollection().getQuestionByString(selectedValue);
            questionExchange.moveToApiCollection(selectedQuestion);
            userListModel.removeElementAt(selectedIndex);
            apiListModel.addElement(selectedValue);
        }
    }
}
