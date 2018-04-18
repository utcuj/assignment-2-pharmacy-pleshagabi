package MVC.controller;

import MVC.model.*;
import MVC.view.AdministratorViewGUI;
import MVC.view.LoginViewGUI;
import MVC.view.RegularUserViewGUI;
import xml.operations.XMLOperation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by plesha on 08-Apr-18.
 */
public class LoginController {

    private LoginModel model;
    private LoginViewGUI view;

    private ArrayList<RegularUserModel> users;
    private ArrayList<AdministratorModel> admins;


    public  LoginController(){
    }

    public LoginController( LoginModel model, LoginViewGUI view ){
        this.model = model;
        this.view = view;

        this.view.addLoginButtonListener( new LoginButtonListener() );
        this.admins = new XMLOperation().readXMLFile("Admins",1);
        this.users = new XMLOperation().readXMLFile("Users",2);
    }

    // button listener
    class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                boolean isLoggedAsUser = false;
                boolean wrongData = true;
                String username = view.getUsername();
                String password = view.getPassword();

                for( RegularUserModel reg : users ){
                    if( reg.getUsername().equals( username ) && reg.getPassword().equals(password) ){
                        // Login AS User
                        isLoggedAsUser = true;
                        wrongData = false;
                        view.closeLoginFrame();
                        view.showMessage("Welcome User: "+username+" !");

                      //  RegularUserModel model = new RegularUserModel();
                        RegularUserViewGUI view = new RegularUserViewGUI();
                        new RegularUserController(reg,view);
                    }
                }

                if( !isLoggedAsUser ){
                    for( AdministratorModel adm : admins ){
                        if( adm.getUsername().equals(username) && adm.getPassword().equals(password) ){
                            // Log in AS ADMIN

                            wrongData = false;
                            view.closeLoginFrame();
                            view.showMessage("Welcome Admininistrator: "+username+" !");

                            //AdministratorModel model = new AdministratorModel();

                            AdministratorViewGUI view = new AdministratorViewGUI();
                            new AdministratorController(adm,view);
                        }
                    }
                }

                if( wrongData ){
                    view.showMessage("Wrong username or password !");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                view.showMessage("Exception found for LoginButtonListener !");
            }
        }
    }//end inner class LoginButtonListener


}
