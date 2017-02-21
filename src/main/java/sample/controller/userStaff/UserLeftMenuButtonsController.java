package sample.controller.userStaff;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.DatabaseHibernate.UserDB;
import sample.controller.adminStaff.AdminEditController;
import sample.controller.adminStaff.ChangePasswordController;
import sample.controller.startStaff.BorderPaneMainController;

import java.io.IOException;

/**
 * Created by Marcin on 2017-02-15.
 */
public class UserLeftMenuButtonsController {
    private BorderPaneMainController borderPaneMainController;
    private UserDB onlineUser;




    @FXML
    void logout() {
        borderPaneMainController.clearBorderPane();
        borderPaneMainController.setCenter("/fxml/startStaff/BorderPaneMain.fxml");
    }

    @FXML
    void openChangePasswordScreen() {
        borderPaneMainController.clearBorderPaneRight();
        System.out.println(onlineUser);
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/adminStaff/ChangePassword.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setCenter(parent);
        ChangePasswordController changePasswordController = loader.getController();
        changePasswordController.setOnlineUser(onlineUser);
        changePasswordController.setUserLeftMenuButtonsController(this);
    }

    @FXML
    void openCalculateScreen() {
       // borderPaneMainController.clearBorderPaneRight();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/userStaff/CalculateBMR.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setCenter(parent);

        CalculateBMRController calculateBMRController = loader.getController();
        calculateBMRController.setOnlineUser(onlineUser);
       // calculateBMRController.setBorderPaneMainController(borderPaneMainController);

    }

    @FXML
    void openMesureScreen() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/userStaff/MesureTables.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setCenter(parent);

        MesureTablesController mesureTablesController = loader.getController();
        mesureTablesController.setOnlineUser(onlineUser);
       // mesureTablesController.setBorderPaneMainController(borderPaneMainController);



    }

    @FXML
    void open3() {

    }



    public BorderPaneMainController getBorderPaneMainController() {
        return borderPaneMainController;
    }

    public void setBorderPaneMainController(BorderPaneMainController borderPaneMainController) {
        this.borderPaneMainController = borderPaneMainController;
    }

    public UserDB getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(UserDB onlineUser) {
        this.onlineUser = onlineUser;
    }
}
