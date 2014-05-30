package Controller;

import Model.OntologyModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public abstract class PanelController implements ActionListener {
    private HashMap<String, Object> elements;
 private OntologyModel model;

    protected PanelController(HashMap<String, Object> elements, OntologyModel model) {
        this.elements = elements;
        this.model = model;
    }

    protected HashMap<String, Object> getElements() {
        return elements;
    }

    public OntologyModel getModel() {
        return model;
    }
}
