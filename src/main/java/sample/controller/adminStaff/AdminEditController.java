package sample.controller.adminStaff;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.UserDB;
import sample.controller.startStaff.BorderPaneMainController;
import sample.controller.startStaff.LeftMenuButtonsController;
import sample.main.Main;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Marcin on 2017-01-25.
 */
public class AdminEditController {
    private UserDB onlineUser;
    private BorderPaneMainController borderPaneMainController;


    @FXML
    private Label labelNotChooseU;
    @FXML
    private Label labelNotChooseA;
    @FXML
    private Label labelSuperAdmin;

    @FXML
    private ListView<String> lvAdminList;
    @FXML
    private ListView<String> lvUserList;
    @FXML
    private void initialize(){
        // pobieranie listy userów
        //inicjalizowanie listyView Userów
        lvUserList.setItems(DatabaseController.getAllUser());
        // pobieranie listy Adminów
        //inicjalizowanie listyView Adminów
        lvAdminList.setItems(DatabaseController.getAllAdminList());
    }



    @FXML
    void addAdminPermissionAction() {
        this.LabelVisableoff();
        if(lvUserList.getSelectionModel().isEmpty()){ // nie wybrano nic z listy
            labelNotChooseU.setVisible(true);
        }else{
            //zmiana statusu
            DatabaseController.makeAdmin(lvUserList.getSelectionModel().getSelectedItem());
            //odswiezenie list
            lvUserList.setItems(DatabaseController.getAllUser());
            lvAdminList.setItems(DatabaseController.getAllAdminList());

        }

    }

    @FXML
    void deleteAdminPermissionAction() {
        this.LabelVisableoff();
        if(lvAdminList.getSelectionModel().isEmpty()){ // nie wybrano nic z listy
            labelNotChooseA.setVisible(true);
        }else{
            //sprawdzenie czy nie ususwa "super admina"
            if(lvAdminList.getSelectionModel().getSelectedItem().equals("admin")){
                // komunikat ze nie mozesz usunac tego "super admina"
                labelSuperAdmin.setVisible(true);
            }else{
                //zmiana statusu
                DatabaseController.dropAdmin(lvAdminList.getSelectionModel().getSelectedItem());
                if(lvAdminList.getSelectionModel().getSelectedItem().equals(onlineUser.getNick())){
                    //wylogowanie
                    this.logout();
                }else{
                    //odswiezenie list
                    lvUserList.setItems(DatabaseController.getAllUser());
                    lvAdminList.setItems(DatabaseController.getAllAdminList());
                }
            }
        }

    }


    private void LabelVisableoff(){
        labelNotChooseU.setVisible(false);
        labelNotChooseA.setVisible(false);
        labelSuperAdmin.setVisible(false);

    }

    private void logout(){

        borderPaneMainController.clearBorderPane();
        borderPaneMainController.setCenter("/fxml/startStaff/BorderPaneMain.fxml");

    }

    public void setOnlineUser(UserDB onlineUser) {
        this.onlineUser = onlineUser;
    }
    public void setBorderPaneMainController(BorderPaneMainController borderPaneMainController) {
        this.borderPaneMainController = borderPaneMainController;
    }
}
