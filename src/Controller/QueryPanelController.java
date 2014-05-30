package Controller;

import Model.OntologyModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

/**
 * Created by UyumazHakan on 30.05.2014.
 */
public class QueryPanelController extends PanelController{
    public QueryPanelController(HashMap<String, Object> elements, OntologyModel model) {
        super(elements, model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selection=JOptionPane.showConfirmDialog(null,"Do you have "+"?",null,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    }
}
