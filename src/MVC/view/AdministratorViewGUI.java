package MVC.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

/**
 * Created by plesha on 07-Apr-18.
 */
public class AdministratorViewGUI {
    private JTable table1;
    private JTable table2;
    private JButton viewALLMedsButton;
    private JButton viewALLUsersButton;
    private JTextPane medicationInfoTextPane;
    private JTextPane regularUsersInfoTextPane;
    private JTextPane medicationIDTextPane;
    private JTextField tfMedID;
    private JTextPane medicationNameTextPane;
    private JTextPane medicationIngredientsTextPane;
    private JTextPane medicationManufacturerTextPane;
    private JTextPane medicationQuantityTextPane;
    private JTextPane medicationPriceTextPane;
    private JTextField tfMedName;
    private JTextField tfMedIngredients;
    private JTextField tfMedManufact;
    private JTextField tfMedQuantity;
    private JTextField tfMedPrice;
    private JButton logoutButton;
    private JTextPane userIDTextPane;
    private JTextField tfUserID;
    private JTextPane userNAMETextPane;
    private JTextPane userAgeTextPane;
    private JTextPane usernameTextPane;
    private JTextPane passwordTextPane;
    private JTextField tfUserNAME;
    private JTextField tfAge;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JPanel panel;
    private JButton addMedButton;
    private JButton deleteMedButton;
    private JButton updateMedButton;
    private JButton addUserButton;
    private JButton deleteUserButton;
    private JButton updateUserButton;
    private JTextPane generateReportsWIthMedicationTextPane;
    private JRadioButton PDFFormatRadioButton;
    private JRadioButton CSVFormatRadioButton;
    private JTextPane selectFormatTextPane;
    private JButton generateButton;

    private DefaultTableModel model,model2;


    private JFrame frame = new JFrame("Administrator Panel - Pharmacy");

    public AdministratorViewGUI() {

        ButtonGroup group = new ButtonGroup();
        group.add(PDFFormatRadioButton);
        group.add(CSVFormatRadioButton);

        frame.setContentPane(panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void addViewALLMedsButtonListener( ActionListener viewAll ){viewALLMedsButton.addActionListener( viewAll );}
    public void addViewALLUsersButtonListener( ActionListener viewAll ){viewALLUsersButton.addActionListener( viewAll );}
    public void addLogoutButtonListener( ActionListener logout ){
        logoutButton.addActionListener( logout );
    }
    public void addMedButtonListener( ActionListener add ){
        addMedButton.addActionListener( add );
    }
    public void addUpdateMedButtonListener( ActionListener update ){
        updateMedButton.addActionListener( update );
    }
    public void addDeleteMedButtonListener( ActionListener delete ){
        deleteMedButton.addActionListener( delete );
    }
    public void addNewUserButtonListener( ActionListener add ){
        addUserButton.addActionListener( add );
    }
    public void addUpdateUserButtonListener( ActionListener update ){
        updateUserButton.addActionListener( update );
    }
    public void addDeleteUserButtonListener( ActionListener delete ){
        deleteUserButton.addActionListener( delete );
    }
    public void addGenerateReportButtonListener( ActionListener generate ) { generateButton.addActionListener( generate );}


    public String[] getMedDataFromTextFields( ){
        String[] data = {tfMedID.getText(), tfMedName.getText(), tfMedIngredients.getText(),
                tfMedManufact.getText(), tfMedQuantity.getText(), tfMedPrice.getText()};
        return data;
    }
    public String getMedIDFromTextField(){
        return tfMedID.getText();
    }

    public String[] getUserDataFromTextFields(){
        String[] data = {tfUserID.getText(),tfUserNAME.getText(),tfAge.getText(),tfUsername.getText(),tfPassword.getText()};
        return data;
    }
    public String getUserIDFromTextField(){
        return tfUserID.getText();
    }

    public void closeFrame(){
        frame.setVisible(false);
        frame.dispose();
    }

    public int getSelectedRadioButton(){
        if( PDFFormatRadioButton.isSelected() ){
            return 0;
        } else if ( CSVFormatRadioButton.isSelected() ) {
            return 1;
        }
        return 3;
    }


    public void addModelRow( String[] rowData ){
        model.addRow(rowData);
    }
    public void addModel2Row( String[] rowData ){
        model2.addRow(rowData);
    }


    public void activateViewALLMedsButton(){
        viewALLMedsButton.doClick();
    }
    public void activateViewALLUsersButton(){
        viewALLUsersButton.doClick();
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

    public void showDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        String column[] = {"ID ", "Name", "Ingredients", "Manufacturer", "Quantity", "Price/Product"};
        model = new DefaultTableModel(column, 0);
        table1 = new JTable(model);

        // ( int idRquest, String medName, int quantity, String clientName, String clientAddress, int clientMoney)
        String column2[] = {"ID User", "Name", "Age", "Username", "Password"};
        model2 = new DefaultTableModel(column2, 0);
        table2 = new JTable(model2);

    }
}
