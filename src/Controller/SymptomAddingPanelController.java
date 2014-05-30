package Controller;

import Model.OntologyModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class SymptomAddingPanelController extends PanelController{
    public SymptomAddingPanelController(HashMap<String, Object> elements, OntologyModel model) {
        super(elements, model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String symptomName=((JTextField)(getElements().get("Symptom Name"))).getText();
        ((JTextField)(getElements().get("Symptom Name"))).setText("");
        getModel().addSymptom(symptomName);
    }


    }

