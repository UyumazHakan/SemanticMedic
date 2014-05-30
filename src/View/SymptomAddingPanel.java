package View;

import Controller.SymptomAddingPanelController;
import Model.OntologyModel;

import javax.swing.*;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class SymptomAddingPanel extends AddingPanel{
    public SymptomAddingPanel() {
        super();
        addInputRow("Symptom Name");
        addSendButton();
    }
    public void addButtonListener(String name, OntologyModel model){
        JButton button= (JButton) getElements().get(name);
        button.addActionListener(new SymptomAddingPanelController(getElements(),model));
    }
}
