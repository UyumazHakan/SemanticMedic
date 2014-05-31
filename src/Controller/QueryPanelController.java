package Controller;

import Model.DiseaseQueryMaker;
import Model.OntologyModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

/**
 * Created by UyumazHakan on 30.05.2014.
 */
public class QueryPanelController extends PanelController{
    private DiseaseQueryMaker queryMaker;
    public QueryPanelController(HashMap<String, Object> elements, OntologyModel model, DiseaseQueryMaker queryMaker) {
        super(elements, model);
        this.queryMaker=queryMaker;
        System.out.println(queryMaker);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String possibleSymptom=queryMaker.getNextPossibleSymptom();
        while(possibleSymptom!=null) {
            int selection = JOptionPane.showConfirmDialog(null, "Do you have " + possibleSymptom + "?", possibleSymptom, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(selection==0)
                queryMaker.addSelectedSymptom(possibleSymptom);
            else
                queryMaker.addNotSelectedSymptom(possibleSymptom);
            possibleSymptom=queryMaker.getNextPossibleSymptom();
        }
        JOptionPane.showMessageDialog(null,"You probably have following disease:"+queryMaker.getPossibleDisease(),"Disease",JOptionPane.OK_OPTION);
    }
}
