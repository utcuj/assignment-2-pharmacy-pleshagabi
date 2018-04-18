package MVC.controller;

import factory.report.Report;
import factory.report.ReportFactory;
import MVC.model.*;
import MVC.view.AdministratorViewGUI;
import MVC.view.LoginViewGUI;
import xml.operations.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

/**
 * Created by plesha on 07-Apr-18.
 */
public class AdministratorController {

    private AdministratorModel model;
    private AdministratorViewGUI view;

    private ArrayList<RegularUserModel> users;
    private ArrayList<MedicationModel> meds;

    public AdministratorController(){

    }

    public AdministratorController( AdministratorModel model , AdministratorViewGUI view ){
        this.model = model;
        this.view = view;

        this.view.addViewALLMedsButtonListener( new ViewAllMedsButtonListener() );
        this.view.addViewALLUsersButtonListener( new ViewAllUsersButtonListener() );
        this.view.addLogoutButtonListener( new LogoutButtonListener() );
        this.view.addMedButtonListener( new AddMedButtonListener() );
        this.view.addUpdateMedButtonListener( new UpdateMedButtonListener() );
        this.view.addDeleteMedButtonListener( new DeleteMedButtonListener() );
        this.view.addNewUserButtonListener( new AddUserButtonListener() );
        this.view.addUpdateUserButtonListener( new UpdateUserButtonListener() );
        this.view.addDeleteUserButtonListener( new DeleteUserButtonListener() );
        this.view.addGenerateReportButtonListener( new GenerateReportButtonListener() );

        this.meds = new XMLOperation().readXMLFile("Medicines",0);
        this.users = new XMLOperation().readXMLFile("Users",2);
    }

    // RegularUser setters

    public void setAdministratorID( int id ){
        this.model.setId( id );
    }

    public void setAdministratorName( String name ){
        this.model.setName( name );
    }

    public void setAdministratorAge( int age ){
        this.model.setAge( age );
    }

    public void setAdministratorUsername( String username ){
        this.model.setUsername( username );
    }

    public void setAdministratorPassword( String password ){
        this.model.setPassword( password );
    }

    // RegularUser getters

    public int getAdministratorID(){
        return this.model.getId();
    }

    public String getAdministratorName( String name ){
        return this.model.getName();
    }

    public int getAdministratorAge(){
        return this.model.getAge();
    }

    public String getAdministratorUsername(){
        return this.model.getUsername();
    }

    public String getAdministratorPassword(){
        return this.model.getPassword();
    }



    //----------------------------------------------------- MEDICATIONS LISTENERS -----------------------------------------------------

    class ViewAllMedsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                view.cleanModel();

                for( MedicationModel med : meds ){

                    String[] data = { Integer.toString(med.getId()),med.getName(),med.getIngredients(),med.getManufacturer(),
                            Integer.toString(med.getQuantity()), Integer.toString(med.getPrice()) };

                    view.addModelRow(data);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found for ViewAllMedsButtonListener !");
            }
        }
    }//end inner class ViewAllMedsButtonListener


    class AddMedButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String[] dataFromGUI = view.getMedDataFromTextFields();

                if( !dataFromGUI[0].equals("") && !dataFromGUI[1].equals("") && !dataFromGUI[2].equals("")
                        && !dataFromGUI[3].equals("") && !dataFromGUI[4].equals("") && !dataFromGUI[5].equals("") ){

                   try{
                       int id = Integer.parseInt(dataFromGUI[0]);
                       int quantity = Integer.parseInt(dataFromGUI[4]);
                       int price = Integer.parseInt(dataFromGUI[5]);

                       for( MedicationModel m : meds ){
                           if( id == m.getId() ) {
                               view.showDialog("The Medicine ID you've entered is not UNIQUE !");
                               return;
                           }
                       }

                       MedicationModel newMed = new MedicationModel( id,dataFromGUI[1],dataFromGUI[2], dataFromGUI[3], quantity, price );

                       view.addModelRow(dataFromGUI);
                       meds.add(newMed);

                       new XMLOperation().saveDataToXMLFile("Medicines",meds,0);

                   }
                   catch (NumberFormatException ex){
                       view.showDialog("[Medicine ID] or [Quantity] or [Price] you've entered are not numerical !");
                   }
                } // end if equals
                else{
                    view.showDialog("You need to complete all Medicine text fields !");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found from Admin AddMedButtonListener !");
            }
        }
    }//end inner class AddMedButtonListener


    class UpdateMedButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String[] dataFromGUI = view.getMedDataFromTextFields();

                if( !dataFromGUI[0].equals("") && !dataFromGUI[1].equals("") && !dataFromGUI[2].equals("")
                        && !dataFromGUI[3].equals("") && !dataFromGUI[4].equals("") && !dataFromGUI[5].equals("") ){

                    try{
                        int id = Integer.parseInt(dataFromGUI[0]);
                        int quantity = Integer.parseInt(dataFromGUI[4]);
                        int price = Integer.parseInt(dataFromGUI[5]);

                        boolean foundID = false;
                        int index = 0;

                        for( MedicationModel m : meds ){
                            if( id == m.getId() ) {
                                foundID = true;
                                break;
                            }
                            index++;
                        }

                        if( foundID ) {
                            MedicationModel newMed = new MedicationModel(id, dataFromGUI[1], dataFromGUI[2], dataFromGUI[3], quantity, price);

                            meds.set(index, newMed);

                            new XMLOperation().saveDataToXMLFile("Medicines", meds, 0);
                            view.activateViewALLMedsButton(); // refresh table
                        }
                        else{
                            view.showDialog("We can't find the Medicine ID you've entered !");
                        }

                    }
                    catch (NumberFormatException ex){
                        view.showDialog("[Medicine ID] or [Quantity] or [Price] you've entered are not numerical !");
                    }
                } // end if equals
                else{
                    view.showDialog("You need to complete all Medicine text fields !");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found from Admin UpdateMedButtonListener !");
            }
        }
    }//end inner class UpdateMedButtonListener


    class DeleteMedButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String idString = view.getMedIDFromTextField();

                if( !idString.equals("") ){
                    try{
                        int id = Integer.parseInt(idString);
                        boolean foundID = false;
                        int index = 0;

                        for( MedicationModel m : meds ){
                            if( id == m.getId() ) {
                                foundID = true;
                                break;
                            }
                            index++;
                        }

                        if( foundID ) {
                            meds.remove(index);
                            new XMLOperation().saveDataToXMLFile("Medicines", meds, 0);
                            view.activateViewALLMedsButton(); // refresh table
                        }
                        else{
                            view.showDialog("We can't find the Medicine ID you've entered !");
                        }

                    }
                    catch (NumberFormatException ex){
                        view.showDialog("The Medicine ID you've entered it's not numerical !");
                    }
                } // end if equals
                else{
                    view.showDialog("You need to complete the Medicine ID text field!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found from Admin AddMedButtonListener !");
            }
        }
    }//end inner class DeleteMedButtonListener

    //----------------------------------------------------- USER LISTENERS -----------------------------------------------------

    class ViewAllUsersButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                view.cleanModel2();

                for( RegularUserModel user : users ){

                    String[] data = { Integer.toString(user.getId()),user.getName(),
                            Integer.toString(user.getAge()),user.getUsername(), user.getPassword() };

                    view.addModel2Row(data);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found for ViewAllUsersButtonListener !");
            }
        }
    }//end inner class ViewAllUsersButtonListener

    class AddUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String[] dataFromGUI = view.getUserDataFromTextFields();

                if( !dataFromGUI[0].equals("") && !dataFromGUI[1].equals("") && !dataFromGUI[2].equals("")
                        && !dataFromGUI[3].equals("") && !dataFromGUI[4].equals("") ){
                    try{
                        int id = Integer.parseInt(dataFromGUI[0]);
                        int age = Integer.parseInt(dataFromGUI[2]);

                        for( RegularUserModel user : users ){
                            if( id == user.getId() ) {
                                view.showDialog("The User ID you've entered is not UNIQUE !");
                                return;
                            }
                        }

                        RegularUserModel newUser = new RegularUserModel( id,dataFromGUI[1],age,dataFromGUI[3],dataFromGUI[4]);

                        view.addModel2Row(dataFromGUI);
                        users.add(newUser);

                        new XMLOperation().saveDataToXMLFile("Users",users,2);

                    }
                    catch (NumberFormatException ex){
                        view.showDialog("[User ID] or [Age] you've entered are not numerical !");
                    }
                } // end if equals
                else{
                    view.showDialog("You need to complete all User text fields !");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found from Admin AddUserButtonListener !");
            }
        }
    }//end inner class AddUserButtonListener

    class UpdateUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String[] dataFromGUI = view.getUserDataFromTextFields();

                if( !dataFromGUI[0].equals("") && !dataFromGUI[1].equals("") && !dataFromGUI[2].equals("")
                        && !dataFromGUI[3].equals("") && !dataFromGUI[4].equals("")){

                    try{
                        int id = Integer.parseInt(dataFromGUI[0]);
                        int age = Integer.parseInt(dataFromGUI[2]);

                        boolean foundID = false;
                        int index = 0;

                        for( RegularUserModel user : users ){
                            if( id == user.getId() ) {
                                foundID = true;
                                break;
                            }
                            index++;
                        }

                        if( foundID ) {
                            RegularUserModel newUser = new RegularUserModel(id, dataFromGUI[1],age, dataFromGUI[3], dataFromGUI[4]);

                            users.set(index, newUser);

                            new XMLOperation().saveDataToXMLFile("Users", users, 1);
                            view.activateViewALLUsersButton(); // refresh table
                        }
                        else{
                            view.showDialog("We can't find the User ID you've entered !");
                        }

                    }
                    catch (NumberFormatException ex){
                        view.showDialog("[ID] or [Age] you've entered are not numerical !");
                    }
                } // end if equals
                else{
                    view.showDialog("You need to complete all User text fields !");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found from Admin UpdateUserButtonListener !");
            }
        }
    }//end inner class UpdateUserButtonListener


    class DeleteUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String idString = view.getUserIDFromTextField();

                if( !idString.equals("") ){
                    try{
                        int id = Integer.parseInt(idString);
                        boolean foundID = false;
                        int index = 0;

                        for( RegularUserModel user : users ){
                            if( id == user.getId() ) {
                                foundID = true;
                                break;
                            }
                            index++;
                        }

                        if( foundID ) {
                            users.remove(index);
                            new XMLOperation().saveDataToXMLFile("Users", users, 1);
                            view.activateViewALLUsersButton(); // refresh table
                        }
                        else{
                            view.showDialog("We can't find the User ID you've entered !");
                        }
                    }
                    catch (NumberFormatException ex){
                        view.showDialog("The User ID you've entered it's numerical !");
                    }
                } // end if equals
                else{
                    view.showDialog("You need to complete the User ID text field!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found from Admin DeleteUserButtonListener !");
            }
        }
    }//end inner class DeleteMedButtonListener



    //----------------------------------------------------- LOGOUT LISTENER -----------------------------------------------------

    class LogoutButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                view.closeFrame();

                LoginModel model = new LoginModel();
                LoginViewGUI view = new LoginViewGUI();
                new LoginController(model,view);

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found from Admin LogoutButtonListener !");
            }
        }
    }//end inner class LogoutButtonListener

    //----------------------------------------------------- GENERATE REPORTS LISTENER -----------------------------------------------------

    class GenerateReportButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                int select = view.getSelectedRadioButton();

                if( select == 0 ){
                    ReportFactory reportFactory = new ReportFactory();
                    Report report = reportFactory.getReportType("pdf");
                    report.createReportFile(getAdministratorUsername(),meds);

                    view.showDialog("PDF Report generated successfully !");
                }
                else if( select == 1 ){
                    ReportFactory reportFactory = new ReportFactory();
                    Report report = reportFactory.getReportType("csv");
                    report.createReportFile(getAdministratorUsername(),meds);

                    view.showDialog("CSV Report generated successfully !");
                }
                else{
                    view.showDialog("No radio button is selected !");
                }


            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found from Admin GenerateReportButtonListener !");
            }
        }
    }//end inner class GenerateReportButtonListener

    public void updateView(){
       // this.view.printAdministratorDetails(model.getId(),model.getName(),model.getAge(),model.getUsername(),model.getPassword());
    }

}
