package MVC.controller;

import MVC.model.*;
import MVC.view.LoginViewGUI;
import MVC.view.RegularUserViewGUI;
import xml.operations.XMLOperation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by plesha on 07-Apr-18.
 */
public class RegularUserController {
    private RegularUserModel model;
    private RegularUserViewGUI view;


    private ArrayList<MedicationModel> readMedArray;
    private ArrayList<BuyingRequestModel> readBuyReqArray;

    public RegularUserController(){

    }

    public RegularUserController( RegularUserModel model , RegularUserViewGUI view ){
        this.model = model;
        this.view = view;

        this.readMedArray = new XMLOperation().readXMLFile("Medicines",0);
        this.readBuyReqArray = new XMLOperation().readXMLFile("BuyingRequests",3);

        this.view.addViewAllButtonListener( new ViewAllButtonListener() );
        this.view.addSearchButtonListener( new SearchButtonListener() );
        this.view.addViewAllRequestButtonListener( new ViewAllRequestButtonListener() );
        this.view.addSellMedicationButtonListener( new SellMedicineRequestButtonListener() );
        this.view.addLogoutButtonListener( new LogoutButtonListener() );
    }

    // RegularUser setters

    public void setRegularUserID( int id ){
       this.model.setId( id );
    }

    public void setRegularUserName( String name ){
        this.model.setName( name );
    }

    public void setRegularUserAge( int age ){
        this.model.setAge( age );
    }

    public void setRegularUserUsername( String username ){
        this.model.setUsername( username );
    }

    public void setRegularUserPassword( String password ){
        this.model.setPassword( password );
    }

    // RegularUser getters

    public int getRegularUserID(){
        return this.model.getId();
    }

    public String getRegularUserName( String name ){
        return this.model.getName();
    }

    public int getRegularUserAge(){
        return this.model.getAge();
    }

    public String getRegularUserUsername(){
        return this.model.getUsername();
    }

    public String getRegularUserPassword(){
        return this.model.getPassword();
    }




    // button listeners
    class ViewAllButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                view.cleanModel();

                // {"ID Medication", "Name", "Ingredients", "Manufacturer", "Quantity", "Price"};
                for( MedicationModel med : readMedArray ){

                    String[] data = { Integer.toString(med.getId()),med.getName(),med.getIngredients(),med.getManufacturer(),
                            Integer.toString(med.getQuantity()), Integer.toString(med.getPrice()) };

                    view.addModelRow(data);

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found for View All button !");
            }
        }
    }//end inner class ViewAllButtonListener

    class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                // {"ID Medication", "Name", "Ingredients", "Manufacturer", "Quantity", "Price"};
                if( !view.getUserSearchDataTextField().equals("") ) {
                    boolean showDialog = true;
                    view.cleanModel();

                    for (MedicationModel med : readMedArray) {
                        boolean isSelected = false;

                        if (view.isNameRadioButtonSelected()) {
                            if (view.getUserSearchDataTextField().equalsIgnoreCase(med.getName())) {
                                isSelected = true;
                            }
                        } else if (view.isManufacturerRadioButtonSelected()) {
                            if (view.getUserSearchDataTextField().equalsIgnoreCase(med.getManufacturer())) {
                                isSelected = true;
                            }
                        } else if (view.isIngredientsRadioButtonSelected()) {
                            String ingredient = view.getUserSearchDataTextField();
                            if (med.getIngredients().toLowerCase().contains(ingredient.toLowerCase())) {
                                isSelected = true;
                            }
                        } else if (showDialog) {
                            view.showDialog("We cant find the selected data for search !");
                            showDialog = false;
                        }

                        if (isSelected) {
                            String[] data = {Integer.toString(med.getId()), med.getName(), med.getIngredients(), med.getManufacturer(),
                                    Integer.toString(med.getQuantity()), Integer.toString(med.getPrice())};
                            view.addModelRow(data);
                        }
                    }
                }else{
                    view.showDialog("You need to complete the search data text field !");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found for Search Button !");
            }
        }
    }//end inner class SearchButtonListener


    class ViewAllRequestButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                view.cleanModel2();

                for( BuyingRequestModel buy : readBuyReqArray ){

                    String[] data = { Integer.toString(buy.getIdRquest()),buy.getMedName(),Integer.toString(buy.getQuantity()),buy.getClientName(),
                            buy.getClientAddress(), Integer.toString(buy.getClientMoney()) };

                    view.addModel2Row(data);

                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found for View All Requests button !");
            }
        }
    }//end inner class ViewAllRequestButtonListener

    class SellMedicineRequestButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

               // view.cleanModel2();
                if( !view.getBuyingRequestTextField().equals("") ) {
                    int idRequestToFind = Integer.parseInt(view.getBuyingRequestTextField());

                    BuyingRequestModel saveRequest = new BuyingRequestModel();

                    for (BuyingRequestModel buy : readBuyReqArray) {
                        //  System.out.println("id to remove = "+idToRemove+"\n");
                        if (idRequestToFind == buy.getIdRquest()) {
                            saveRequest = buy;
                            MedicationModel medData = getMedicationDataByName(buy.getMedName());
                            int arrayIndex = getMedicationArrayIndex(buy.getMedName());

                            if (buy.getQuantity() < medData.getQuantity() ) {
                                if (buy.getClientMoney() >= buy.getQuantity() * medData.getPrice() ) {

                                    buy.setClientMoney(buy.getClientMoney() - (buy.getQuantity() * medData.getPrice()));
                                    medData.setQuantity( medData.getQuantity() - buy.getQuantity() );

                                    view.activateViewALLButton();
                                    view.activateViewALLRequestButton();
                                    //int idRow = view.findRowByID(medData.getId(), view.getModel1());
                                    //view.UpdateModelDataAt(Integer.toString(medData.getQuantity()), idRow, 4); // update quantity in medicine table1

                                    readMedArray.set(arrayIndex,medData);

                                    view.showDialog("You accepted the following Buying Request\n" +
                                            "Request ID: " + idRequestToFind + "\n" +
                                            "Client Name: " + buy.getClientName() + "\n" +
                                            "Client Address: " + buy.getClientAddress() + "\n" +
                                            "Medicine Name: " + buy.getMedName() + "\n" +
                                            "Quantity: " + buy.getQuantity() + "\n" +
                                            "Price Paid: " + buy.getQuantity() * medData.getPrice() + "\n"
                                    );

                                    view.removeModel2Row(view.findRowByID(buy.getIdRquest(), view.getModel2()));
                                    buy.setQuantity(0);
                                } else {
                                    view.showDialog("Client has Not enough money !");
                                }
                            } else {
                                view.showDialog("Medicine Out of stock !");
                            }
                        }
                    } // end for

                    if (!readBuyReqArray.remove(saveRequest)) {
                        view.showDialog("Wrong Request !");
                    }

                    new XMLOperation().saveDataToXMLFile("Medicines",readMedArray,0);
                    new XMLOperation().saveDataToXMLFile("BuyingRequests",readBuyReqArray,3);


                }else{
                    view.showDialog("You need to complete the Request ID text field !");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found for SellMedicineReuestButtonListener Requests button !");
            }
        }
    }//end inner class ViewAllRequestButtonListener


    class LogoutButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                view.closeFrame();

                LoginModel model = new LoginModel();
                LoginViewGUI view = new LoginViewGUI();
                new LoginController(model,view);

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showDialog("Exception found from User LogoutButtonListener !");
            }
        }
    }//end inner class LogoutButtonListener


    private MedicationModel getMedicationDataByName( String name ){
        MedicationModel save = new MedicationModel();

        for( MedicationModel med : readMedArray ) {
            if( med.getName().equals(name) ){
               save = med;
            }
        }
        return save;
    }

    private int getMedicationArrayIndex( String name ){
        int index = 0;
        for( MedicationModel med : readMedArray ) {

            if( med.getName().equals(name) ){
                return index;
            }

            index++;
        }
        return index;
    }

}
