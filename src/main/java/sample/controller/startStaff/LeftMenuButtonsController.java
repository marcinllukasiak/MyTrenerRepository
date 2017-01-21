package sample.controller.startStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.DatabaseHibernate.DatabaseController;

import java.io.IOException;

/**
 * Created by Marcin on 2017-01-05.
 */
public class LeftMenuButtonsController {

    private BorderPaneMainController borderPaneMainController;


    @FXML
    public void openSignIn( ) {
        System.out.println("openSignIn");

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/startStaff/SignIn.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setCenter(parent);
        SignInController signInController = loader.getController();
        signInController.setBorderPaneMainController(borderPaneMainController);

        // borderPaneMainController.setCenter("/fxml/SignIn.fxml");


    }
    @FXML
    public void openSingUp( ) {
        System.out.println("openSingUp");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/startStaff/SignUp.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setCenter(parent);
       // borderPaneMainController.setCenter("/fxml/SignUp.fxml");
    }
    @FXML
    public void openAboutApplication( ) {
        System.out.println("openAboutAplication");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/startStaff/AboutApplication.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setCenter(parent);
       // borderPaneMainController.setCenter("/fxml/AboutApplication.fxml");
    }

    public void setBorderPaneMainController(BorderPaneMainController borderPaneMainController) {
        this.borderPaneMainController = borderPaneMainController;
    }


    void initialize(){

    }


}
