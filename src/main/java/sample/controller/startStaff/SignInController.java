package sample.controller.startStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.UserDB;
import sample.controller.adminStaff.AdminLeftMenuButtonsController;
import sample.dialogs.DialogsStaff;

import java.io.IOException;

/**
 * Created by Marcin on 2017-01-06.
 */
public class SignInController {

   // DatabaseController databaseController = new DatabaseController(); // polaczenie z baza danych

    private BorderPaneMainController borderPaneMainController;
    @FXML // fx:id="tfPasswordId"
    private PasswordField tfPasswordId; // Value injected by FXMLLoader
    @FXML // fx:id="tfLoginId"
    private TextField tfLoginId; // Value injected by FXMLLoader
    @FXML
    private Label labelFailedLoginId;

    //konstruktor
    public SignInController() {

        System.out.println("konstruktor Sign In");
    }

    @FXML
    public void initialize(){


    }

    @FXML
    void recoveryPasswordAction() {
        borderPaneMainController.setCenter("/fxml/startStaff/RecoveryPasswd.fxml");

    }

    @FXML
    void signInAction() {
        System.out.println(tfLoginId.getText());
        System.out.println(tfPasswordId.getText());

        UserDB userDB = null;
        userDB=DatabaseController.selectUser(tfLoginId.getText());


        try {
            if(userDB.getActivated()){ // sprawdzanie czy aktywowane
                //sprawdzanie loginu i hasla
                if((userDB.getPassword().equals(tfPasswordId.getText()) ) ){

                    System.out.println("Zalogowano");


                    if(userDB.getAdministrator()){
                        borderPaneMainController.setCenter("/fxml/adminStaff/onlineAdmin.fxml");

                        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/adminStaff/AdminLeftMenuButtons.fxml"));
                        Parent parent = null;
                        try {
                            parent = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        borderPaneMainController.setLeft(parent);
                        AdminLeftMenuButtonsController adminLeftMenuButtonsController = loader.getController();
                        adminLeftMenuButtonsController.setBorderPaneMainController(borderPaneMainController);
                        adminLeftMenuButtonsController.setOnlineUser(userDB);
                    }else{
                        borderPaneMainController.setCenter("/fxml/userStaff/OnlineUser.fxml");
                    }


                }else{
                    labelFailedLoginId.setVisible(true);
                }
                // koniec sprawdzania L/H
            }else{//jesli nie aktywowane
                DialogsStaff.activateDialog(userDB);
            }

        } catch (Exception e) {
            labelFailedLoginId.setVisible(true);
        }

    }
    // setters and getters
    public void setBorderPaneMainController(BorderPaneMainController borderPaneMainController) {
        this.borderPaneMainController = borderPaneMainController;
    }


}
