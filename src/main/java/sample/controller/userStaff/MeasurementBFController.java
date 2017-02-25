package sample.controller.userStaff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import sample.DatabaseHibernate.MesurementDBHelper;
import sample.DatabaseHibernate.UserDB;

import java.util.Collections;
import java.util.List;

/**
 * Created by Marcin on 2017-02-25.
 */
public class MeasurementBFController {
    UserDB onlineUser;

    @FXML
    private ToggleGroup SexGroup;
    @FXML
    private RadioButton rbMale;
    @FXML
    private RadioButton rbFemale;

    @FXML
    private TextField tfBodyWeight;
    @FXML
    private TextField tfWaist;

    @FXML
    private Label lValidation1;
    @FXML
    private Label labelLvlBF;




    @FXML
    void calculateBFAction() {
        labelLvlBF.setVisible(false);
        lValidation1.setVisible(false);
        //walidacja
        if(this.validationNotEmptyFields() && this.validationNumerics()){
            // przeliczenie
            double a = 0;
            double b = 0;
            double c = 0;
            double d = 0;
            double e = 0;
            double result = 0;

            a=Integer.valueOf(tfWaist.getText())*4.15;
            b=a/2.54;
            c=0.082*Double.valueOf(tfBodyWeight.getText())*2.2;
            if(rbMale.isSelected()){
                d= b - c - 98.42;
            }else {
                d= b - c - 76.76;
            }
            e=Double.valueOf(tfBodyWeight.getText())*2.2;
            result = Math.round(d/e*100);

            labelLvlBF.setText("Poziom BF : "+String.valueOf(result));
            labelLvlBF.setVisible(true);
        }


    }

    public UserDB getOnlineUser() {
        return onlineUser;
    }
    public void setOnlineUser(UserDB onlineUser) {
        this.onlineUser = onlineUser;
    }
    public void initializationField() {

        lValidation1.setVisible(false);
        //inicjalizacja wartości jesli sa juz podane
        //Plec
        if(onlineUser.isMale()){
            rbMale.setSelected(true);
        }else{
            rbFemale.setSelected(true);
        }

        //sortowanie pomiarow aby wybrac najaktualnijszy
        List<MesurementDBHelper> mesurementsList = onlineUser.getMainMeasurementDB().getMesurements();
        Collections.sort(mesurementsList);

        //wzrost
        tfBodyWeight.setText(String.valueOf(mesurementsList.get(0).getBodyWeight()));
        //Zapotrzebowanie kaloryczne
        tfWaist.setText(String.valueOf(mesurementsList.get(0).getWaistSize()));

    }


    private Boolean validationNumerics(){ // tfage.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))
        boolean returne = true;

        if (!tfBodyWeight.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation1.setText("Wartość 'Masa Ciała'-błąd");
            lValidation1.setVisible(true);
            returne = false;
        }else if (!tfWaist.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation1.setText("Wartość 'Talia'-błąd");
            lValidation1.setVisible(true);
            returne = false;
        }



        return returne;
    }

    private Boolean validationNotEmptyFields(){
        boolean returne = true;

        //sprawdzanie radioboxow czy wybrane
        if(SexGroup.getSelectedToggle() == null){
            lValidation1.setText("Nie podano 'Płci'");
            lValidation1.setVisible(true);
            returne = false;
        }else if(tfWaist.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation1.setText("Nie podano 'Talia'");
            lValidation1.setVisible(true);
            returne = false;
        }else if(tfBodyWeight.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation1.setText("Nie podano 'Masy Ciała'");
            lValidation1.setVisible(true);
            returne = false;
        }


        return returne;
    }




}
