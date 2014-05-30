package View;

import javax.swing.*;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class MainFrame extends JFrame {
    TabbedView tabbedView;

    public MainFrame() {
        tabbedView = new TabbedView();
        setTitle("Medical Asistant");
        setSize(500, 500);
        add(tabbedView);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public DiseaseListPanel getDiseaseListPanel() {
        return tabbedView.getDiseaseListPanel();
    }
    public QueryPanel getQueryPanel(){return  tabbedView.getQueryPanel();}
    public SymptomAddingPanel getSymptomAddingPanel() {
        return tabbedView.getSymptomAddingPanel();
    }
    public SymptomsListPanel getSymptomListPanel() {return  tabbedView.getSymptomsListPanel();}
    public DiseaseAddingPanel getDiseaseAddingPanel() {return  tabbedView.getDiseaseAddingPanel();}
}
