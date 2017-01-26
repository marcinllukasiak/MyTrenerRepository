package sample.controller.startStaff;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.UserDB;
import sample.dialogs.DialogsStaff;
import sample.main.SendAttachmentInEmail;


/**
 * Created by Marcin on 2017-01-24.
 */
public class RecoveryPasswordController {

    @FXML // fx:id="tfNickRecovery"
    private TextField tfNickRecovery;

    @FXML
    void RecoveryPasswordAction() {
        UserDB userDB = null;
        userDB= DatabaseController.selectUser(tfNickRecovery.getText());
        try {
            if(!(userDB.equals(null))) { // user istnieje
                //wyslanie emaila na powiazany z nickiem email
                SendAttachmentInEmail.sendRecoveryPassword(userDB.getNick(), userDB.getPassword(), userDB.getEmail());
                //wyswietleni okna informujacego o wyslaniu emaila
                DialogsStaff.infoSendRecoveryPassword();
                tfNickRecovery.clear();
            }
        }catch (Exception e){
            System.err.println(e);
            DialogsStaff.infoWrongNick();
        }



    }

}
