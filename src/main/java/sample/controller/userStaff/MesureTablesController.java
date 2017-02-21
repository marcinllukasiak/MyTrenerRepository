package sample.controller.userStaff;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.MesurementDBHelper;
import sample.DatabaseHibernate.UserDB;
import sample.controller.startStaff.BorderPaneMainController;
import sample.main.DateUtils;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by Marcin on 2017-02-18.
 */
public class MesureTablesController {
    private BorderPaneMainController borderPaneMainController;
    private UserDB onlineUser;



    @FXML
    private RadioButton rbMale;
    @FXML
    private RadioButton rbFemale;
    @FXML
    private ToggleGroup SexGroup;

    @FXML
    private TextField tfBodyWeight;
    @FXML
    private TextField tfSizeCm;
    @FXML
    private TextField tfCaloric;
    @FXML
    private TextField tfNeck;
    @FXML
    private TextField tfChest;
    @FXML
    private TextField tfWaist;
    @FXML
    private TextField tfHips;
    @FXML
    private TextField tfLArm;
    @FXML
    private TextField tfRArm;
    @FXML
    private TextField tfLForearm;
    @FXML
    private TextField tfRForearm;
    @FXML
    private TextField tfLThigh;
    @FXML
    private TextField tfRThigh;
    @FXML
    private TextField tfRCalf;
    @FXML
    private TextField tfLCalf;

    @FXML
    private DatePicker dpDateOfMeasurement;

    @FXML
    private Label lValidation1;
    @FXML
    private Label lValidation12;

    @FXML
    void loadDataAction() {
        lValidation1.setVisible(false);
        //inicjalizacja wartości jesli sa juz podane
        //Plec
        if(onlineUser.isMale()){
            rbMale.setSelected(true);
        }else{
            rbFemale.setSelected(true);
        }
        //MasaCiala
        tfBodyWeight.setText(String.valueOf(onlineUser.getMainMeasurementDB().getBodyWeight()));
        //wzrost
        tfSizeCm.setText(String.valueOf(onlineUser.getMainMeasurementDB().getSizeCm()));
        //Zapotrzebowanie kaloryczne
        tfCaloric.setText(String.valueOf(onlineUser.getMainMeasurementDB().getCaloric()));
    }

    @FXML
    void saveChangeProfileAction() {
        lValidation1.setVisible(false);
        // Walidacja
        if(this.validationNotEmptyFields1() && this.validationNumerics1()){
            //Update usera
            //zamiana wszystkiego w onlnie
            //plec
            if(rbMale.isSelected()){
                onlineUser.setMale(true);
            }else if(rbFemale.isSelected()){
                onlineUser.setMale(false);
            }
            //mesureMain
            onlineUser.getMainMeasurementDB().setBodyWeight(Double.valueOf(tfBodyWeight.getText()));
            onlineUser.getMainMeasurementDB().setSizeCm(Integer.valueOf(tfSizeCm.getText()));
            onlineUser.getMainMeasurementDB().setCaloric(Integer.valueOf(tfCaloric.getText()));


            DatabaseController.updateUserDBMainMesure(onlineUser.getIdUser(),onlineUser);

            this.clearField1();
        }
    }

        public void clearField1(){
            tfBodyWeight.clear();
            tfSizeCm.clear();
            tfCaloric.clear();
        }


    @FXML
    void saveMesureAction() {
        lValidation12.setVisible(false);
        // Walidacja
        if(this.validationNumerics2() && this.validationNotEmptyFields2()){
            //Utworzenie MesurementDBHelper
            MesurementDBHelper mesurementDBHelper = new MesurementDBHelper(DateUtils.asDate(dpDateOfMeasurement.getValue()),Integer.valueOf(tfNeck.getText()),
                    Integer.valueOf(tfChest.getText()),Integer.valueOf(tfWaist.getText()),Integer.valueOf(tfHips.getText()),
                    Integer.valueOf(tfLArm.getText()), Integer.valueOf(tfRArm.getText()),
                    Integer.valueOf(tfLForearm.getText()),Integer.valueOf(tfRForearm.getText()),
                    Integer.valueOf(tfLThigh.getText()),Integer.valueOf(tfRThigh.getText()),
                    Integer.valueOf(tfLCalf.getText()),Integer.valueOf(tfRCalf.getText()));
            //dodanie MesurementhHelpera do bazy
            if(DatabaseController.insertMesurementDBHelper(mesurementDBHelper)){ // jesli zstanie dodany pomiar to dzialamy

                //wyonanie polecenia updatujacego tego usera
                DatabaseController.updateUserDBMesureHelper(onlineUser.getIdUser(),mesurementDBHelper);

                DatabaseController.getAllMesureUser(onlineUser.getMainMeasurementDB().getIdMainMeasurement());
//            //Połączenie online usera z miara
//            onlineUser.getMainMeasurementDB().getMesurements().add(mesurementDBHelper);

                //czyszczenie fieldow
                dpDateOfMeasurement.setValue(null);
                this.clearField2();
            }else{
                this.clearField2();
            }





        }

    }

    private void clearField2(){
        tfNeck.clear();
        tfChest.clear();
        tfWaist.clear();
        tfHips.clear();
        tfLArm.clear(); tfRArm.clear();
        tfLForearm.clear(); tfRForearm.clear();
        tfLThigh.clear(); tfRThigh.clear();
        tfLCalf.clear(); tfRCalf.clear();
    }


    private Boolean validationNumerics2(){ // tfage.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))
        boolean returne = true;
        if (!tfNeck.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Kark'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        } else if (!tfChest.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Klatka'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfWaist.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Talia'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfHips.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Biodra'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfLArm.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Ramie L'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfRArm.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Ramie R'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfLForearm.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Przedramię L'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfRForearm.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Przedramię R'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfLThigh.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Udo L'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfRThigh.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Udo R'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfLCalf.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Lydka L'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }else if (!tfRCalf.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Lydka R'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }



        return returne;
    }

    private Boolean validationNotEmptyFields2(){
        boolean returne = true;
        //sprawdzanie radioboxow czy wybrane
        if(dpDateOfMeasurement.getValue().equals(null)){
            lValidation12.setText("Nie podano 'Daty'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfNeck.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Kark'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfChest.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Klatka'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfWaist.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Talia'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfHips.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Biodra'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfLArm.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Ramie L'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfRArm.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Ramie R'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfLForearm.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Przedramie L'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfRForearm.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Przedramie R'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfLThigh.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Udo L'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfRThigh.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Udo R'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfLCalf.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Lydka L'");
            lValidation12.setVisible(true);
            returne = false;
        }else if(tfRCalf.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Lydka R'");
            lValidation12.setVisible(true);
            returne = false;
        }

        return returne;
    }


    private Boolean validationNotEmptyFields1(){
        boolean returne = true;
        //sprawdzanie radioboxow czy wybrane
        if(SexGroup.getSelectedToggle() == null){
            lValidation1.setText("Nie podano 'Płci'");
            lValidation1.setVisible(true);
            returne = false;
        }else if(tfBodyWeight.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation1.setText("Nie podano 'Masy Ciała'");
            lValidation1.setVisible(true);
            returne = false;
        }else if(tfSizeCm.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation1.setText("Nie podano 'Wzrostu'");
            lValidation1.setVisible(true);
            returne = false;
        }else if(tfCaloric.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation1.setText("Nie podano 'Zapotrzebowania'");
            lValidation1.setVisible(true);
            returne = false;
        }

        return returne;
    }

    private Boolean validationNumerics1(){ // tfage.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))
        boolean returne = true;
        if (tfBodyWeight.getText().matches("([0-9]*)\\.([0-9]*)")) {
        } else {
            lValidation1.setText("Wartość 'Masa Ciała'-błąd");
            lValidation1.setVisible(true);
            returne = false;
        }
        if (tfSizeCm.getText().matches("(-|\\+)?[0-9]+")) {

        } else {
            lValidation1.setText("Wartość 'Wzrost'-błąd");
            lValidation1.setVisible(true);
            returne = false;
        }

        if (tfCaloric.getText().matches("(-|\\+)?[0-9]+")) {
        } else {
            lValidation1.setText("Wartość 'Zapotrzebowanie'-błąd");
            lValidation1.setVisible(true);
            returne = false;
        }

        return returne;
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
