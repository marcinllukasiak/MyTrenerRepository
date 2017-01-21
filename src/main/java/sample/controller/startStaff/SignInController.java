package sample.controller.startStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.UserDB;

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


    }

    @FXML
    void signInAction() {
        System.out.println(tfLoginId.getText());
        System.out.println(tfPasswordId.getText());

        UserDB userDB = null;
        userDB=DatabaseController.selectUser(tfLoginId.getText());


        try {
            if((userDB.getPassword().equals(tfPasswordId.getText()) ) ){

                System.out.println("Zalogowano");


                if(userDB.getAdministrator()){
                    borderPaneMainController.setCenter("/fxml/adminStaff/onlineAdmin.fxml");
                }else{
                    borderPaneMainController.setCenter("/fxml/userStaff/OnlineUser.fxml");
                }


            }else{
                labelFailedLoginId.setVisible(true);
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
