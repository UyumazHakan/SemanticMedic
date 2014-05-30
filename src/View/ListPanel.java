package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Hakan on 24.05.2014.
 */
public class ListPanel extends Panel {
    private final int TABLE_X = 0, TABLE_Y = 50, TABLE_WIDTH = 490, TABLE_HEIGHT = 500;
    private final int BUTTON_X = 50, BUTTON_Y = 10, BUTTON_WIDTH = 200, BUTTON_HEIGHT = 30;



    public ListPanel() {

        createButton("Create Turtle Output", BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        createButton("Refresh", BUTTON_X+BUTTON_WIDTH, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

    }



    protected void createTable(String[] columnNames, Object[][] data) {
        DefaultTableModel tableModel=new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollTable = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollTable);
        scrollTable.setBounds(TABLE_X, TABLE_Y, TABLE_WIDTH, TABLE_HEIGHT);
        getElements().put("Table", tableModel);
    }
    public void refreshList(Object[] data){

        DefaultTableModel table=((DefaultTableModel)getElements().get("Table"));
        clearTable();
        for(int i=0;i<data.length;i++){
            table.addRow(new Object[] {data[i]});
        }

    }
    public void clearTable(){
        DefaultTableModel table=((DefaultTableModel)getElements().get("Table"));
        if (table.getRowCount() > 0) {
            for (int i = table.getRowCount() - 1; i > -1; i--) {
                table.removeRow(i);
            }
        }
    }



}
