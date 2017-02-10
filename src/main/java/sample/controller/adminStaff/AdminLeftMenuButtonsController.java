package sample.controller.adminStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.DatabaseHibernate.UserDB;
import sample.controller.startStaff.BorderPaneMainController;

import java.io.IOException;

/**current password
 * Created by Marcin on 2017-01-25.
 */
public class AdminLeftMenuButtonsController {
    private BorderPaneMainController borderPaneMainController;
    private UserDB onlineUser;



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
        changePasswordController.setAdminLeftMenuButtonsController(this);

    }

    @FXML
    void logout() {
        borderPaneMainController.clearBorderPane();
        borderPaneMainController.setCenter("/fxml/startStaff/BorderPaneMain.fxml");
    }

    @FXML
    void openAdminEditScreen() {
        borderPaneMainController.clearBorderPaneRight();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/adminStaff/AdminEdit.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setCenter(parent);
        AdminEditController adminEditController = loader.getController();
        adminEditController.setOnlineUser(onlineUser);
        adminEditController.setBorderPaneMainController(borderPaneMainController);

    }


    @FXML
    void openTremomgEditScreen() {
        borderPaneMainController.clearBorderPaneRight();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/adminStaff/SingleTableTraining.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setCenter(parent);
        System.out.println("Ladowanie Trening Edit Screen");
//        AdminEditController adminEditController = loader.getController();
//        adminEditController.setOnlineUser(onlineUser);
//        adminEditController.setBorderPaneMainController(borderPaneMainController);

        // ladowanie prawej czesci
        FXMLLoader loader2 = new FXMLLoader(this.getClass().getResource("/fxml/adminStaff/RightListTraining.fxml"));
        Parent parent2 = null;
        try {
            parent2 = loader2.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setRight(parent2);
        RightTrainingController rightTrainingController = loader2.getController();
        rightTrainingController.setSingleTableTrainingController((SingleTableTrainingController) loader.getController());

    }

    @FXML
    void openUserEditScreen() {
        borderPaneMainController.clearBorderPaneRight();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/adminStaff/UserEdit.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainController.setCenter(parent);
        UserEditController userEditController = loader.getController();
        //adminEditController.setOnlineUser(onlineUser);
        //adminEditController.setBorderPaneMainController(borderPaneMainController);

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
