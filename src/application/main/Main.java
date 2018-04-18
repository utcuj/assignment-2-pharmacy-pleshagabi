package application.main;

import MVC.controller.LoginController;
import MVC.model.*;
import MVC.view.LoginViewGUI;
import xml.operations.XMLOperation;


import java.util.ArrayList;

/**
 * Created by plesha on 06-Apr-18.
 */
public class Main {

    public static void main( String[] args ){

        System.out.println((8*8)/3 + 3);

        // ------------------------------ ADD medication TO XML FILES ------------------------------
        //  {"ID Medication", "Name", "Ingredients", "Manufacturer", "Quantity", "Price"};
        ArrayList<MedicationModel> listMed = new ArrayList<>();

        MedicationModel med1 = new MedicationModel(1,"aspirin","bicar, beta alanina, bicarbonat, glutamina","bayer",1000,500);
        MedicationModel med2 = new MedicationModel(2,"neotigason","bicar, beta alanina, bicarbonat, glutamina","Merz Pharmaceuticals",100,1500);
        MedicationModel med3 = new MedicationModel(3,"betadine","omega, beta alanina, bicarbonat, glutamina","Terapia Cluj-Napoca",5000,150);
        MedicationModel med4 = new MedicationModel(4,"roacutane","afla, beta alanina, bicarbonat, glutamina","bayer",300,890);
        MedicationModel med5 = new MedicationModel(5,"vasdd","albastru de voronet, meta beta, teta, peta","Montreal Meds",1660,125);

        MedicationModel med6 = new MedicationModel(6,"Acetaminophen","Alprazolam, beta alanina, bicarbonat, glutamina","Europharm",6000,100);
        MedicationModel med7 = new MedicationModel(7,"Doxycycline","bicar, beta alanina, bicarbonat, glutamina","Merz Pharmaceuticals",890,550);
        MedicationModel med8 = new MedicationModel(8,"Ibuprofen","creatina, beta alanina, bicarbonat, glutamina","Terapia Cluj-Napoca",5000,150);
        MedicationModel med9 = new MedicationModel(9,"Xanax","bicar, beta alanina, bicarbonat, glutamina","Europharm",5500,55);
        MedicationModel med10 = new MedicationModel(10,"Tramadol","albastru de voronet, meta beta, teta, peta","Geden Richter Romania",1250,250);

        MedicationModel med11 = new MedicationModel(11,"Acetaminophen","Alprazolam, beta alanina, bicarbonat, glutamina","Europharm",0,100);
        MedicationModel med12 = new MedicationModel(12,"Doxycycline","bicar, beta alanina, bicarbonat, glutamina","Merz Pharmaceuticals",0,550);
        MedicationModel med13 = new MedicationModel(13,"Ibuprofen","creatina, beta alanina, bicarbonat, glutamina","Terapia Cluj-Napoca",0,150);
        MedicationModel med14 = new MedicationModel(14,"Xanax","bicar, beta alanina, bicarbonat, glutamina","Europharm",0,55);
        MedicationModel med15 = new MedicationModel(15,"Tramadol","albastru de voronet, meta beta, teta, peta","Geden Richter Romania",0,250);

        listMed.add(med1);listMed.add(med2);listMed.add(med3);listMed.add(med4);listMed.add(med5);
        listMed.add(med6);listMed.add(med7);listMed.add(med8);listMed.add(med9);listMed.add(med10);
        listMed.add(med11);listMed.add(med12);listMed.add(med13);listMed.add(med14);listMed.add(med15);

        // ------------------------------ adding request to XML file ------------------------------
        ArrayList<BuyingRequestModel> listReq = new ArrayList<>();
        BuyingRequestModel b1 = new BuyingRequestModel(1,"aspirin",9,"Vasile Matei","Bucegi 17",99700);
        BuyingRequestModel b2 = new BuyingRequestModel(2,"roacutane",6,"Anca Matei","Bucegi 17",85600);
        BuyingRequestModel b3 = new BuyingRequestModel(3,"neotigason",15,"Plesa Gabi","Bucegi 17",96600);
        BuyingRequestModel b4 = new BuyingRequestModel(4,"Acetaminophen",23,"Larisa Bratean","Bucegi 17",91600);
        BuyingRequestModel b5 = new BuyingRequestModel(5,"aspirin",5,"Bianca Floriana","Bucegi 17",98800);
        BuyingRequestModel b6 = new BuyingRequestModel(6,"aspirin",15,"Rosca Alexandru","Bucegi 17",98800);

        listReq.add(b1); listReq.add(b2); listReq.add(b3); listReq.add(b4); listReq.add(b5); listReq.add(b6);

        // --------------------------- add users to XML file
        ArrayList<RegularUserModel> listUsers = new ArrayList<>();
        RegularUserModel u1 = new RegularUserModel(1,"Plesa Gabita-Ioan",22,"plesha","gabi");
        RegularUserModel u2 = new RegularUserModel(2,"Antonia Madalina",23,"antonia","antonia");
        RegularUserModel u3 = new RegularUserModel(3,"Ilas Alexandra",21,"alexandra","alexandra");

        listUsers.add(u1); listUsers.add(u2); listUsers.add(u3);

        // --------------------------- add admins to XML file
        ArrayList<AdministratorModel> listAdmins = new ArrayList<>();
        AdministratorModel a1 = new AdministratorModel(1,"Plesa Gabi",22,"plesha","plesha");
        AdministratorModel a2 = new AdministratorModel(2,"Madalina Oprea",20,"oprea","madalina");
        AdministratorModel a3 = new AdministratorModel(3,"Andrei Muresan",23,"andrei","andrei");

        listAdmins.add(a1); listAdmins.add(a2); listAdmins.add(a3);

        // ---------------------------

        new XMLOperation().saveDataToXMLFile("Medicines",listMed,0);
        new XMLOperation().saveDataToXMLFile("BuyingRequests",listReq,3);
        new XMLOperation().saveDataToXMLFile("Users",listUsers,2);
        new XMLOperation().saveDataToXMLFile("Admins",listAdmins,1);

        // ------------------- RUN

        LoginModel model = new LoginModel();
        LoginViewGUI view = new LoginViewGUI();
        new LoginController(model,view);


       /* RegularUserModel model = new RegularUserModel();
        RegularUserViewGUI view = new RegularUserViewGUI();
        RegularUserController controller = new RegularUserController(model,view);
        */

   /*
        AdministratorModel model = new AdministratorModel();
        AdministratorViewGUI view = new AdministratorViewGUI();
        new AdministratorController(model,view);
 */

    }
}
