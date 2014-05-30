package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class Panel extends JPanel {
    private HashMap<String, Object> elements;
    public Panel() {
        elements = new HashMap<String, Object>();
        this.setLayout(null);
    }
    public HashMap<String, Object> getElements() {
        return elements;
    }
    protected void createButton(String buttonName, int x, int y, int width, int height) {
        JButton button = new JButton(buttonName);
        add(button);
        button.setBounds(x, y, width, height);
        elements.put(buttonName, button);
    }
    public void addButtonListener(String name, ActionListener listener){
        JButton button= (JButton) getElements().get(name);
        button.addActionListener(listener);
    }
    public void eraseElement(String name){
        remove((Component)getElements().get(name));
        getElements().remove(name);

    }
}
