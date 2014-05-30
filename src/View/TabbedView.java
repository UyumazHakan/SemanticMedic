package View;

import javax.swing.*;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class TabbedView extends JTabbedPane {
    private DiseaseListPanel diseaseListPanel;
    private SymptomAddingPanel symptomAddingPanel;
    private SymptomsListPanel symptomsListPanel;
    private DiseaseAddingPanel diseaseAddingPanel;
    private QueryPanel queryPanel;


    public DiseaseAddingPanel getDiseaseAddingPanel() {
        return diseaseAddingPanel;
    }


    public TabbedView() {
        diseaseListPanel = new DiseaseListPanel();
        symptomAddingPanel=new SymptomAddingPanel();
        symptomsListPanel=new SymptomsListPanel();
        diseaseAddingPanel=new DiseaseAddingPanel();
        queryPanel=new QueryPanel();
        addTab("Query",queryPanel);

        addTab("Add Symptom",symptomAddingPanel);
        addTab("Add Disease",diseaseAddingPanel);
        addTab("Diseases", diseaseListPanel);
        addTab("Symptoms",symptomsListPanel);

    }

    public QueryPanel getQueryPanel() {
        return queryPanel;
    }
    public DiseaseListPanel getDiseaseListPanel() {
        return diseaseListPanel;
    }

    public SymptomAddingPanel getSymptomAddingPanel() {
        return symptomAddingPanel;
    }
    public SymptomsListPanel getSymptomsListPanel() {
        return symptomsListPanel;
    }
}
