import Controller.MainController;
import Model.OntologyModel;
import View.MainFrame;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class Main {
    public static void main(String[] args) throws OWLOntologyCreationException {
        MainFrame frame=new MainFrame();
        OntologyModel ontologyModel=new OntologyModel(frame);
        MainController controller=new MainController(ontologyModel,frame);
    }
}
