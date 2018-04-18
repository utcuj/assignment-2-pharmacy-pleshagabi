package MVC.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

/**
 * Created by plesha on 07-Apr-18.
 */
public class RegularUserViewGUI {
    private JTable table1;
    private JTextPane searchMedicationTextPane;
    private JTextField tfGetSerchData;
    private JRadioButton byNAMERadioButton;
    private JRadioButton byINGREDIENTSRadioButton;
    private JRadioButton byMANUFACTURERRadioButton;
    private JButton searchButton;
    private JButton sellMedicineButton;
    private JScrollPane scrollPane1;
    private JPanel panel;
    private JButton viewALLButton;
    private JTable table2;
    private JTextPane sellMedicationTextPane;
    private JTextField tfRequestID;
    private JTextPane requestIDTextPane;
    private JButton viewALLButton2;
    private JButton logoutButton;

    private DefaultTableModel model,model2;
    private JFrame frame = new JFrame("Regular User Panel - Pharmacy");


    public RegularUserViewGUI(){

        ButtonGroup  group = new ButtonGroup();
        group.add(byNAMERadioButton);
        group.add(byINGREDIENTSRadioButton);
        group.add(byMANUFACTURERRadioButton);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void activateViewALLButton(){
        viewALLButton.doClick();
    }
    public void activateViewALLRequestButton(){
        viewALLButton2.doClick();
    }

    public void closeFrame(){
        frame.setVisible(false);
        frame.dispose();
    }


    public void addViewAllButtonListener(ActionListener viewAll) {
        viewALLButton.addActionListener(viewAll);
    }
    public void addSearchButtonListener(ActionListener search) {
        searchButton.addActionListener(search);
    }
    public void addLogoutButtonListener(ActionListener logout) { logoutButton.addActionListener(logout); }

    public void addViewAllRequestButtonListener(ActionListener viewAll) {
        viewALLButton2.addActionListener(viewAll);
    }
    public void addSellMedicationButtonListener(ActionListener sell) {
        sellMedicineButton.addActionListener(sell);
    }


    public String getUserSearchDataTextField(){
        return tfGetSerchData.getText();
    }
    public String getBuyingRequestTextField(){
        return tfRequestID.getText();
    }

    public boolean isNameRadioButtonSelected(){
        return byNAMERadioButton.isSelected();
    }
    public boolean isIngredientsRadioButtonSelected(){
        return byINGREDIENTSRadioButton.isSelected();
    }
    public boolean isManufacturerRadioButtonSelected(){
        return byMANUFACTURERRadioButton.isSelected();
    }


    public void addModelRow( String[] rowData ){
        model.addRow(rowData);
    }
    public void addModel2Row( String[] rowData ){
        model2.addRow(rowData);
    }

    public void removeModel2Row( int id ){
        model2.removeRow(id);
    }

   /* public void UpdateModelDataAt( String data, int id , int column){
        model.setValueAt(data,id,column);
    }*/

    public DefaultTableModel getModel1(){
        return this.model;
    }
    public DefaultTableModel getModel2(){
        return this.model2;
    }

    public int findRowByID(int id, DefaultTableModel model ) {
        //int cols = model.getColumnCount();
        int rows = model.getRowCount();

        int id_delete = 0;

        for (int i = 0; i < rows; i++) {
            String tmp = model.getValueAt(i, 0).toString();
            if (id == Integer.parseInt(tmp)) {
                id_delete = i;
            }
        }
        return id_delete;
    }


    public void showDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void cleanModel(){
        // remove all rows
        while (table1.getRowCount() > 0) {
            ((DefaultTableModel) table1.getModel()).removeRow(0);
        }
    }
    public void cleanModel2(){
        // remove all rows
        while (table2.getRowCount() > 0) {
            ((DefaultTableModel) table2.getModel()).removeRow(0);
        }
    }

    public void createUIComponents() {
        // TODO: place custom component creation code here

        String column[] = {"ID Medication", "Name", "Ingredients", "Manufacturer", "Quantity", "Price/Product"};
        model = new DefaultTableModel(column, 0);
        table1 = new JTable(model);

        // ( int idRquest, String medName, int quantity, String clientName, String clientAddress, int clientMoney)
        String column2[] = {"ID Request", "Medicine Name", "Quantity", "Client Name", "Address", "Money"};
        model2 = new DefaultTableModel(column2, 0);
        table2 = new JTable(model2);

    }
}
