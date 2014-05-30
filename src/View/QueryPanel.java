package View;

import Controller.DiseaseAddingPanelController;
import Controller.QueryPanelController;
import Model.OntologyModel;

import javax.swing.*;

/**
 * Created by UyumazHakan on 30.05.2014.
 */
public class QueryPanel extends Panel {
    private final int BUTTON_X = 50, BUTTON_Y = 10, BUTTON_WIDTH = 200, BUTTON_HEIGHT = 30;
    public QueryPanel() {
        createButton("Query",BUTTON_X,BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
    }
    public void addButtonListener(String name, OntologyModel model){
        JButton button= (JButton) getElements().get(name);
        button.addActionListener(new QueryPanelController(getElements(),model));
    }
}
