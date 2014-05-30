package Controller;

import Model.DiseaseQueryMaker;
import Model.OntologyModel;
import View.DiseaseAddingPanel;
import View.MainFrame;
import View.QueryPanel;
import View.SymptomAddingPanel;
import org.semanticweb.owlapi.model.OWLClass;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class MainController {
    private OntologyModel model;
    private MainFrame view;

    public MainController(OntologyModel model, MainFrame view) {
        this.model = model;
        this.view = view;
        addSymptomAddingListeners();
        new DiseaseQueryMaker(model).getNextPossibleSymptom();
    }
    private void addSymptomAddingListeners(){
        SymptomAddingPanel symptomAddingPanel=view.getSymptomAddingPanel();
        symptomAddingPanel.addButtonListener("SEND", model);
        DiseaseAddingPanel diseaseAddingPanel=view.getDiseaseAddingPanel();
        diseaseAddingPanel.addButtonListener("SEND", model);
        QueryPanel queryPanel=view.getQueryPanel();
        queryPanel.addButtonListener("Query",model);
    }
}
