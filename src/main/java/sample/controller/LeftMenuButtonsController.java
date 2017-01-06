package sample.controller;

import javafx.fxml.FXML;

/**
 * Created by Marcin on 2017-01-05.
 */
public class LeftMenuButtonsController {

    private BorderPaneMainController borderPaneMainController;


    @FXML
    public void openSignIn( ) {
        System.out.println("openSignIn");
        borderPaneMainController.setCenter("/fxml/SignIn.fxml");
    }
    @FXML
    public void openSingUp( ) {
        System.out.println("openSingUp");
        borderPaneMainController.setCenter("/fxml/SignUp.fxml");
    }
    @FXML
    public void openAboutApplication( ) {
        System.out.println("openAboutAplication");
        borderPaneMainController.setCenter("/fxml/AboutApplication.fxml");
    }

    public void setBorderPaneMainController(BorderPaneMainController borderPaneMainController) {
        this.borderPaneMainController = borderPaneMainController;
    }
}
