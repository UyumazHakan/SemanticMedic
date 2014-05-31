package Model;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by UyumazHakan on 30.05.2014.
 */
public class DiseaseQueryMaker {
    private ArrayList<OWLClass> selectedSymptoms;
    private ArrayList<OWLClass> notSelectedSymptoms;
    private ArrayList<OWLClass> allSymptoms;
    private OntologyModel model;

    public DiseaseQueryMaker(OntologyModel model) {
        this.model = model;
        selectedSymptoms = new ArrayList<OWLClass>();
        notSelectedSymptoms = new ArrayList<OWLClass>();
        allSymptoms = new ArrayList<OWLClass>();
        setAllSymptoms();
    }

    private void setAllSymptoms() {
        String[] symptomArray = model.getDiseases();
        for (String symptom : symptomArray)
            allSymptoms.add(model.getClass(symptom));
    }

    public void addSelectedSymptom(String name) {
        OWLClass newClass = model.getClass("Symptom" + name);
        selectedSymptoms.add(newClass);
    }

    public void addNotSelectedSymptom(String name) {
        OWLClass newClass = model.getClass("Symptom" + name);
        notSelectedSymptoms.add(newClass);
    }

    public String getNextPossibleSymptom() {
        OWLClass disease = model.getClass("Disease");
        OWLObjectUnionOf allDiseases = model.getDataFactory().getOWLObjectUnionOf(model.getSubOfExpression(disease));
        OWLObjectIntersectionOf intersectionOfSelected = getIntersectionOfSelected();
        OWLObjectUnionOf unionOfSubSelected = model.getDataFactory().getOWLObjectUnionOf(model.getSubOfExpression(intersectionOfSelected));
        OWLObjectIntersectionOf possibleDiseasesIntersection = model.getDataFactory().getOWLObjectIntersectionOf(allDiseases, unionOfSubSelected);
        Set<OWLClass> possibleDiseases = model.getSubOfExpression(possibleDiseasesIntersection);
        possibleDiseases.addAll(model.getEquivalentOfExpression(possibleDiseasesIntersection));
        possibleDiseases.remove(model.getDataFactory().getOWLNothing());
        Set<OWLClass> possibleSymptoms = new TreeSet<OWLClass>();
        Iterator<OWLClass> possibleDiseasesIterator = possibleDiseases.iterator();
        while (possibleDiseasesIterator.hasNext()) {
            OWLClass next = possibleDiseasesIterator.next();
            possibleSymptoms.addAll(model.getSuperOfExpression(next));
        }
        possibleSymptoms.remove(model.getDataFactory().getOWLThing());
        possibleSymptoms.remove(model.getClass("Disease"));
        possibleSymptoms.removeAll(selectedSymptoms);
        possibleSymptoms.removeAll(notSelectedSymptoms);
        System.out.println(possibleDiseases);
        if(possibleSymptoms.size()!=0 && possibleDiseases.size()>1)
        return (new SimpleShortFormProvider().getShortForm((OWLClass)possibleSymptoms.toArray()[0])).substring(7);
        else
        return null;

    }
    public String getPossibleDisease(){
        OWLClass disease = model.getClass("Disease");
        OWLObjectUnionOf allDiseases = model.getDataFactory().getOWLObjectUnionOf(model.getSubOfExpression(disease));
        OWLObjectIntersectionOf intersectionOfSelected = getIntersectionOfSelected();
        OWLObjectUnionOf unionOfSubSelected = model.getDataFactory().getOWLObjectUnionOf(model.getSubOfExpression(intersectionOfSelected));
        OWLObjectIntersectionOf possibleDiseasesIntersection = model.getDataFactory().getOWLObjectIntersectionOf(allDiseases, unionOfSubSelected);
        Set<OWLClass> possibleDiseases = model.getSubOfExpression(possibleDiseasesIntersection);
        possibleDiseases.addAll(model.getEquivalentOfExpression(possibleDiseasesIntersection));
        possibleDiseases.remove(model.getDataFactory().getOWLNothing());
        String value="";
        if(possibleDiseases.size()!=0) {
            Iterator<OWLClass> it = possibleDiseases.iterator();
            while (it.hasNext()) {
                value+="\n"+new SimpleShortFormProvider().getShortForm(it.next());
            }
        }else
            value+="\nCould not found";
        return value;
    }

    public OWLObjectIntersectionOf getIntersectionOfSelected() {
        OWLClass top = model.getDataFactory().getOWLThing();
        OWLObjectIntersectionOf intersection = model.getDataFactory().getOWLObjectIntersectionOf(top, top);
        for (OWLClass selected : selectedSymptoms)
            intersection = model.getDataFactory().getOWLObjectIntersectionOf(intersection, selected);
        return intersection;
    }

}
