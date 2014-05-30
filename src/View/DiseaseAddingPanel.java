package View;

import Controller.DiseaseAddingPanelController;
import Controller.SymptomAddingPanelController;
import Model.OntologyModel;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class DiseaseAddingPanel extends AddingPanel{
    ArrayList<String> symptomNames;

    public DiseaseAddingPanel() {
        super();
        this.symptomNames =new ArrayList<String>();
        addInputRow("Disease Name");
        addSendButton();
    }
    public void refreshSymptomLines(String[] symptoms){
        eraseSymptomLines();
        addSymptomLines(symptoms);

    }
    private void addSymptomLines(String[] symptoms){
        for(String symptom: symptoms) {
            addcheckBoxRow(symptom);
            symptomNames.add(symptom);
        }
    }
    private void eraseSymptomLines(){
        for(String symptom: symptomNames){
            eraseElement(symptom+" label");
            eraseElement(symptom+" box");
            decreaseNumberOfFormElements();
        }
        symptomNames.clear();

    }
    public ArrayList<String> getSelectedSymptoms(){
        ArrayList<String> selectedSymptoms=new ArrayList<>();
        for(String symptom: symptomNames){
            if(((JCheckBox)(getElements().get(symptom+" box"))).isSelected())
                selectedSymptoms.add(symptom);

        }
        return selectedSymptoms;
    }
    public ArrayList<String> getNotSelectedSymptoms(){
        ArrayList<String> notSelectedSymptoms=new ArrayList<>();
        for(String symptom: symptomNames){
            if(!((JCheckBox)(getElements().get(symptom+" box"))).isSelected())
                notSelectedSymptoms.add(symptom);

        }
        return notSelectedSymptoms;
    }
    public void addButtonListener(String name, OntologyModel model){
        JButton button= (JButton) getElements().get(name);
        button.addActionListener(new DiseaseAddingPanelController(getElements(),model,this));
    }
}
