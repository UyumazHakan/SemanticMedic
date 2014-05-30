package Controller;

import Model.OntologyModel;
import View.DiseaseAddingPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

/**
 * Created by UyumazHakan on 29.05.2014.
 */
public class DiseaseAddingPanelController extends PanelController{
    private DiseaseAddingPanel panel;
    public DiseaseAddingPanelController(HashMap<String, Object> elements, OntologyModel model, DiseaseAddingPanel panel) {
        super(elements, model);
        this.panel=panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String diseaseName=((JTextField)(getElements().get("Disease Name"))).getText();
        ((JTextField)(getElements().get("Disease Name"))).setText("");
        getModel().addDisease(diseaseName, panel.getSelectedSymptoms(), panel.getNotSelectedSymptoms());
        uncheckBoxes();
    }
    private void uncheckBoxes() {
        for(String symptom: panel.getSelectedSymptoms())
            ((JCheckBox)(getElements().get(symptom+" box"))).setSelected(false);
    }
}
