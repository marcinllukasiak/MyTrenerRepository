package sample.controller.adminStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.UserDB;
import sample.dialogs.DialogsStaff;

/**
 * Created by Marcin on 2017-01-25.
 */
public class ChangePasswordController {
    private UserDB onlineUser;
    private AdminLeftMenuButtonsController adminLeftMenuButtonsController;

    public AdminLeftMenuButtonsController getAdminLeftMenuButtonsController() {
        return adminLeftMenuButtonsController;
    }

    public void setAdminLeftMenuButtonsController(AdminLeftMenuButtonsController adminLeftMenuButtonsController) {
        this.adminLeftMenuButtonsController = adminLeftMenuButtonsController;
    }

    public UserDB getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(UserDB onlineUser) {
        this.onlineUser = onlineUser;
    }

    @FXML
    private Label labelWrongCurrentPassword;
    @FXML
    private Label labelWrongNewPassword;
    @FXML
    private Label labelNewPassword1Empty;
    @FXML
    private Label labelNewPassword2Empty;
    @FXML
    private Label labelCurrentPasswordEmpty;


    @FXML
    private PasswordField pfCurrentPassword;

    @FXML
    private PasswordField pfNewPassword1;

    @FXML
    private PasswordField pfNewPassword2;

    @FXML
    void ChangePasswordAction() {
        System.out.println("zmiana hasła");
        this.cleaningLabelValidation();

        //sprawdzenie czy podano parametry
        if(pfCurrentPassword.getText().equals("")){
            labelCurrentPasswordEmpty.setVisible(true);
        }else if(pfNewPassword1.getText().equals("")){
            labelNewPassword1Empty.setVisible(true);
        }else if(pfNewPassword2.getText().equals("")){
            labelNewPassword2Empty.setVisible(true);
        }else if(onlineUser.getPassword().equals(pfCurrentPassword.getText())){ //podales poprawne obecne haslo
            //sprawdzenie czy nowe hasła sa takie same
            if(pfNewPassword2.getText().equals(pfNewPassword1.getText())){
                //zmiana hasłą w bazie danych i odswiezenie usera - wyslanie go do Admin Left Button
                onlineUser = DatabaseController.editPassword(onlineUser,pfNewPassword1.getText());
                adminLeftMenuButtonsController.setOnlineUser(onlineUser);
                //Wyświetlenie komunikatu o poprawnej zmianie hasłą
                DialogsStaff.changePasswordSucecessfulDialog();
                //czyszczenie fieldów
                this.cleanField();
            }else {
                labelWrongNewPassword.setVisible(true);
            }
        }else{
            labelWrongCurrentPassword.setVisible(true);
        }





    }
    private void cleaningLabelValidation(){
        labelWrongCurrentPassword.setVisible(false);
        labelWrongNewPassword.setVisible(false);
        labelCurrentPasswordEmpty.setVisible(false);
        labelNewPassword1Empty.setVisible(false);
        labelNewPassword2Empty.setVisible(false);
    }
    private void cleanField(){
        pfCurrentPassword.clear();
        pfNewPassword1.clear();
        pfNewPassword2.clear();
    }

}
