package View;

import javax.swing.*;

/**
 * Created by UyumazHakan on 24.05.2014.
 */
public class AddingPanel extends Panel {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int LABEL_WIDTH = 150;
    private final int FIELD_WIDTH = 100;
    private final int LABEL_X = 20;
    private final int FIELD_X = LABEL_WIDTH + LABEL_X + 20;
    private final int ROW_HEIGHT = 30;
    private final int EXTRA_X = FIELD_WIDTH + FIELD_X + 20;
    private final int BUTTON_WIDTH = 70;
    private final int CHECK_BOX_SIZE=20;

    private int numberOfFormElements;

    public AddingPanel() {
        numberOfFormElements=0;
    }

    protected void addInputRow(String fieldName) {
        createLabel(fieldName);
        JTextField text = createTextField();
        numberOfFormElements++;
        getElements().put(fieldName, text);
    }
    protected void addcheckBoxRow(String fieldName) {
        JLabel label=createLabel(fieldName);
        getElements().put(fieldName+" label",label);
        JCheckBox checkBox=createCheckBox();
        numberOfFormElements++;
        getElements().put(fieldName+" box", checkBox);
    }

    private JCheckBox createCheckBox() {
        JCheckBox checkBox=new JCheckBox();
        checkBox.setSelected(false);
        this.add(checkBox);
        checkBox.setBounds(FIELD_X, numberOfFormElements * ROW_HEIGHT, CHECK_BOX_SIZE, CHECK_BOX_SIZE);
        return checkBox;
    }

    private JTextField createTextField() {
        JTextField text = new JTextField(20);
        this.add(text);
        text.setBounds(FIELD_X, numberOfFormElements * ROW_HEIGHT, FIELD_WIDTH, 20);
        return text;
    }
    private JLabel createLabel(String fieldName) {
        JLabel label = new JLabel(fieldName + ": ");
        this.add(label);
        label.setBounds(LABEL_X, numberOfFormElements * ROW_HEIGHT, WIDTH, 20);
        return label;
    }
    protected void addSendButton() {
        createButton("SEND",EXTRA_X, numberOfFormElements * ROW_HEIGHT, BUTTON_WIDTH, 20);
    }
    protected void decreaseNumberOfFormElements(){
        this.numberOfFormElements--;
    }
}
