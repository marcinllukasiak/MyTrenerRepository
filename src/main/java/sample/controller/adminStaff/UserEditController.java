package sample.controller.adminStaff;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import sample.DatabaseHibernate.DatabaseController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Marcin on 2017-01-26.
 */
public class UserEditController {
    @FXML
    private ListView<String> lvUser;

    @FXML
    private TextField tfNewValue;

    @FXML
    private Label labelTextInfo;

    @FXML
    private Label labelInfoUser;
    
    @FXML
    private Label labelNotSelectUser;

    @FXML
    private Label labelWrongNewValue;

    @FXML
    void changeEmailAction() {
        this.labelVisableoff();
        if(lvUser.getSelectionModel().isEmpty()){
            labelNotSelectUser.setVisible(true);
            labelInfoUser.setVisible(false);
            labelTextInfo.setVisible(false);
        }else{
            if(tfNewValue.getText().equals("")){
                labelWrongNewValue.setVisible(true);
            }else{ // jest wartosc
                //sprawdzenie czy email jest poprawny
                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                Matcher m = p.matcher(tfNewValue.getText());
                boolean matchFound = m.matches();

                if (matchFound) {
                    System.out.println("email OK");
                    //zamiana w bazie
                    DatabaseController.changeEmail(lvUser.getSelectionModel().getSelectedItem(),tfNewValue.getText());
                    //odswiezenie wartosci
                    labelInfoUser.setText(DatabaseController.selectUser(lvUser.getSelectionModel().getSelectedItem()).toStringUserInfo());

                } else {
                    System.out.println("zły adres email");
                    //komunikat ze mail tak nie wyglada
                    labelWrongNewValue.setVisible(true);

                }
            }
        }


    }



    @FXML
    void changeIsActivationAction() {
        this.labelVisableoff();
        if(lvUser.getSelectionModel().isEmpty()){
            labelNotSelectUser.setVisible(true);
            labelInfoUser.setVisible(false);
            labelTextInfo.setVisible(false);
        }else{
            if(tfNewValue.getText().equals("")){
                labelWrongNewValue.setVisible(true);
            }else{ // jest wartosc
                if( (tfNewValue.getText().equals("1")) || (tfNewValue.getText().toLowerCase().equals("true")) ){
                    DatabaseController.changeIsActivate(lvUser.getSelectionModel().getSelectedItem(),true);
                    //odswiezenie wartosci
                    labelInfoUser.setText(DatabaseController.selectUser(lvUser.getSelectionModel().getSelectedItem()).toStringUserInfo());
                }else if((tfNewValue.getText().equals("0")) || (tfNewValue.getText().toLowerCase().equals("false"))){
                    DatabaseController.changeIsActivate(lvUser.getSelectionModel().getSelectedItem(),false);
                    //odswiezenie wartosci
                    labelInfoUser.setText(DatabaseController.selectUser(lvUser.getSelectionModel().getSelectedItem()).toStringUserInfo());
                }else{
                    // zła wartość
                    labelWrongNewValue.setVisible(true);
                }
            }
        }
    }

    @FXML
    void changeSexAction() {
        this.labelVisableoff();
        if(lvUser.getSelectionModel().isEmpty()){
            labelNotSelectUser.setVisible(true);
            labelInfoUser.setVisible(false);
            labelTextInfo.setVisible(false);
        }else{
            if(tfNewValue.getText().equals("")){
                labelWrongNewValue.setVisible(true);
            }else{ // jest wartosc
                if( (tfNewValue.getText().equals("1")) || (tfNewValue.getText().toLowerCase().equals("true")) ){
                    DatabaseController.changeIsMale(lvUser.getSelectionModel().getSelectedItem(),true);
                    //odswiezenie wartosci
                    labelInfoUser.setText(DatabaseController.selectUser(lvUser.getSelectionModel().getSelectedItem()).toStringUserInfo());
                }else if((tfNewValue.getText().equals("0")) || (tfNewValue.getText().toLowerCase().equals("false"))){
                    DatabaseController.changeIsMale(lvUser.getSelectionModel().getSelectedItem(),false);
                    //odswiezenie wartosci
                    labelInfoUser.setText(DatabaseController.selectUser(lvUser.getSelectionModel().getSelectedItem()).toStringUserInfo());
                }else{
                    // zła wartość
                    labelWrongNewValue.setVisible(true);
                }
            }
        }
    }


    @FXML
    void changeNameAction() {
        this.labelVisableoff();
        if(lvUser.getSelectionModel().isEmpty()){
            labelNotSelectUser.setVisible(true);
            labelInfoUser.setVisible(false);
            labelTextInfo.setVisible(false);
        }else{
            if(tfNewValue.getText().equals("")){
                labelWrongNewValue.setVisible(true);
            }else { // jest wartosc
                DatabaseController.changeName(lvUser.getSelectionModel().getSelectedItem(),tfNewValue.getText());
                //odswiezenie wartosci
                labelInfoUser.setText(DatabaseController.selectUser(lvUser.getSelectionModel().getSelectedItem()).toStringUserInfo());
            }
        }
    }

    @FXML
    void changePasswordAction() {
        this.labelVisableoff();
        if(lvUser.getSelectionModel().isEmpty()){
            labelNotSelectUser.setVisible(true);
            labelInfoUser.setVisible(false);
            labelTextInfo.setVisible(false);
        }else{
            if(tfNewValue.getText().equals("")){
                labelWrongNewValue.setVisible(true);
            }else { // jest wartosc
                DatabaseController.changePassword(lvUser.getSelectionModel().getSelectedItem(),tfNewValue.getText());
                //odswiezenie wartosci
                labelInfoUser.setText(DatabaseController.selectUser(lvUser.getSelectionModel().getSelectedItem()).toStringUserInfo());
            }
        }
    }

    @FXML
    void changeSurnameAction() {
        this.labelVisableoff();
        if(lvUser.getSelectionModel().isEmpty()){
            labelNotSelectUser.setVisible(true);
            labelInfoUser.setVisible(false);
            labelTextInfo.setVisible(false);
        }else{
            if(tfNewValue.getText().equals("")){
                labelWrongNewValue.setVisible(true);
            }else { // jest wartosc
                DatabaseController.changeSurname(lvUser.getSelectionModel().getSelectedItem(),tfNewValue.getText());
                //odswiezenie wartosci
                labelInfoUser.setText(DatabaseController.selectUser(lvUser.getSelectionModel().getSelectedItem()).toStringUserInfo());
            }
        }
    }





    @FXML
    private void initialize(){
        lvUser.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                labelNotSelectUser.setVisible(false);
                labelWrongNewValue.setVisible(false);
                //ustawienie tekstu w labelu

                labelInfoUser.setText(DatabaseController.selectUser(lvUser.getSelectionModel().getSelectedItem()).toStringUserInfo());
                labelInfoUser.setVisible(true);
                labelTextInfo.setVisible(true);


            }
        });

        // pobieranie listy userów
        //inicjalizowanie listyView Userów
        lvUser.setItems(DatabaseController.getAllUser());
    }


    private void labelVisableoff(){
        labelNotSelectUser.setVisible(false);
        labelWrongNewValue.setVisible(false);

    }
}
