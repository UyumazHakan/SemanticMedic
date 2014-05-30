package Model;

import DLQuery.DLQueryEngine;
import DLQuery.DLQueryParser;
import DLQuery.DLQueryPrinter;
import View.MainFrame;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class OntologyModel {
    private MainFrame frame;
    private OWLOntologyManager manager;
    private OWLOntology ontology;
    private File owlFile;
    private Reasoner owlReasoner;
    private IRI iri;
    private OWLDataFactory dataFactory;
    private DLQueryPrinter queryPrinter;
    private DLQueryEngine queryEngine;
    private DLQueryParser queryParser;
    private ShortFormProvider shortFormProvider;



    public OntologyModel(MainFrame frame) throws OWLOntologyCreationException {
        this.frame=frame;
        this.owlFile=new File("ontology.owl");
        this.iri =IRI.create("http://www.medicalsemantic.com");
        this.dataFactory=OWLManager.getOWLDataFactory();
        this.manager= OWLManager.createOWLOntologyManager();
        this.ontology=manager.loadOntologyFromOntologyDocument(owlFile);
        this.owlReasoner=createReasoner(ontology);
        this.shortFormProvider = new SimpleShortFormProvider();
        this.queryEngine=new DLQueryEngine(owlReasoner,shortFormProvider);
        this.queryPrinter=new DLQueryPrinter(queryEngine,shortFormProvider);
        this.queryParser=new DLQueryParser(ontology,shortFormProvider);
        refreshSymptoms();
        refreshDiseases();

    }
    private Reasoner createReasoner(OWLOntology rootOntology) {
        return new Reasoner(rootOntology);
    }
    public void addSymptom(String symptomName){
        OWLClass newClass=dataFactory.getOWLClass(IRI.create(iri +"#"+symptomName));
        OWLDeclarationAxiom declaration = dataFactory.getOWLDeclarationAxiom(newClass);
        OWLSubClassOfAxiom subclassOf =dataFactory.getOWLSubClassOfAxiom(newClass,queryParser.parseClassExpression("Symptom"));
        manager.addAxiom(ontology, declaration);
        manager.addAxiom(ontology, subclassOf);
        for(String s:getDiseases())
        System.out.println(s);
        refreshSymptoms();
        //saveOntology();
    }
    public void refreshSymptoms(){
        frame.getSymptomListPanel().refreshList(getSymptoms());
        frame.getDiseaseAddingPanel().refreshSymptomLines(getSymptoms());
    }
    public void refreshDiseases(){
        frame.getDiseaseListPanel().refreshList(getDiseases());
    }
    public String[] getSymptoms(){
        return getSubClasses("Symptom");
    }
    public String[] getDiseases(){
        return getSubClasses("Disease");
    }
    public String[] getSubClasses(String className){
        Set<OWLClass> classes=queryEngine.getSubClasses(className,true);
        String[] subClasses=new String[classes.size()];
        int i=0;
        for(OWLClass c:classes)
            subClasses[i++]=shortFormProvider.getShortForm(c);
        return subClasses;
    }
    public String[] getSuperClasses(String className){
        Set<OWLClass> classes=queryEngine.getSuperClasses(className, true);
        String[] superClasses =new String[classes.size()];

        int i=0;
        for(OWLClass c:classes)
            superClasses[i++]=shortFormProvider.getShortForm(c);
        return superClasses;
    }
    public Set getSuperOfExpression(OWLClassExpression expression){
        return owlReasoner.getSuperClasses(expression, false).getFlattened();
    }
    public Set getSubOfExpression(OWLClassExpression expression){
        return owlReasoner.getSubClasses(expression, false).getFlattened();
    }
    public Set getEquivalentOfExpression(OWLClassExpression expression){
        return owlReasoner.getEquivalentClasses(expression).getEntities();
    }
    public void saveOntology(){
        try {
            OutputStream os = new FileOutputStream(owlFile);
            manager.saveOntology(ontology, new OWLXMLOntologyFormat(), os);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public OWLDataFactory getDataFactory() {
        return dataFactory;
    }
    public OWLClass getClass(String name){
        return dataFactory.getOWLClass(IRI.create(iri+"#"+name));
    }
    public void addDisease(String diseaseName, ArrayList<String> selectedSymptoms, ArrayList<String> notSelectedSymptoms) {
        OWLClass newDiseaseClass=dataFactory.getOWLClass(IRI.create(iri+"#"+diseaseName));
        OWLClassExpression generalDiseaseClass=queryParser.parseClassExpression("Disease");
        OWLDeclarationAxiom diseaseDeclaration=dataFactory.getOWLDeclarationAxiom(newDiseaseClass);
        OWLSubClassOfAxiom diseaseSubClassOf=dataFactory.getOWLSubClassOfAxiom(newDiseaseClass,generalDiseaseClass);
        OWLObjectPropertyExpression hasSymptom=dataFactory.getOWLObjectProperty(IRI.create(iri+"#hasDisease"));
        manager.addAxiom(ontology,diseaseDeclaration);
        manager.addAxiom(ontology,diseaseSubClassOf);
        for(String symptom:selectedSymptoms){
            OWLClassExpression symptomExpression=queryParser.parseClassExpression(symptom);
            OWLObjectSomeValuesFrom someValuesFrom=dataFactory.getOWLObjectSomeValuesFrom(hasSymptom,symptomExpression);
            OWLClass hasSomeSymptom=dataFactory.getOWLClass(IRI.create(iri+"#Symptom"+symptom));
            OWLEquivalentClassesAxiom equivalent=dataFactory.getOWLEquivalentClassesAxiom(someValuesFrom,hasSomeSymptom);
            OWLSubClassOfAxiom subClassOfAxiom=dataFactory.getOWLSubClassOfAxiom(newDiseaseClass,hasSomeSymptom);
            manager.addAxiom(ontology,equivalent);
            manager.addAxiom(ontology,subClassOfAxiom);
        }
        saveOntology();
    }
}
