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
import java.util.List;

/**
 * Created by Marcin on 2017-02-16.
 */
public class CalculateBMRController {
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
    private TextField tfage;
    @FXML
    private ComboBox<String> cbPhysicalActivity;
    @FXML
    private ComboBox<String> cbGoal;
    @FXML
    private TextField tfCaloric;

    @FXML
    private Label lValidation;

    @FXML
    void convertAction() {
        lValidation.setVisible(false);
        //WALIDACJA WARTOSCI
        if (validationNotEmptyFields() && validationNumerics() && validationNotEmptyComboBox()) {

            //Obliczanie
            //Aktywnosc
            double activityIndex = 0;

            if(cbPhysicalActivity.getValue().toString().equals("Leżący lub siedzący tryb życia, brak aktywności fizycznej")){
                activityIndex =1.0;
            }else if(cbPhysicalActivity.getValue().toString().equals("Praca siedząca, aktywność fizyczna na niskim poziomie")){
                activityIndex =1.2;
            }else if(cbPhysicalActivity.getValue().toString().equals("Praca niefizyczna, trening 2 razy w tygodniu")){
                activityIndex =1.4;
            }else if(cbPhysicalActivity.getValue().toString().equals("Lekka praca fizyczna, trening 3-4 razy w tygodniu")){
                activityIndex =1.6;
            }else if(cbPhysicalActivity.getValue().toString().equals("Praca fizyczna, trening 5 razy w tygodniu")){
                activityIndex =1.8;
            }else if(cbPhysicalActivity.getValue().toString().equals("Ciężka praca fizyczna, codzienny trening")){
                activityIndex =2.0;
            }else{
                activityIndex =-1.0;
            }
            //Cel
            int goal = 0;
            if(cbGoal.getValue().toString().equals("Zwiększenie Masy Ciała")){
                goal = 250;
            }else if(cbGoal.getValue().toString().equals("Zmniejszenie Masy Ciała")){
                goal = -150;
            }else if(cbGoal.getValue().toString().equals("Utrzymanie Masy Ciała")){
                goal = 0;
            }else{
                goal=-1;
            }


            Double partBMR =  (Double.valueOf(tfBodyWeight.getText())*9.9) + ( 6.25*Integer.valueOf(tfSizeCm.getText()) ) - ( 4.92*Integer.valueOf(tfage.getText()) );
            Double bmr = 0d;

            if(rbMale.isSelected()){
                bmr=partBMR-161;
            }else if(rbFemale.isSelected()){
                bmr=partBMR-5;
            }

            long caloricNumber = Math.round((bmr*activityIndex)+goal);


            //wpisywanie wartosci do fielda
            tfCaloric.setText(String.valueOf(caloricNumber));

        }




    }


    @FXML
    void saveAction() {
        lValidation.setVisible(false);

        if(validationNotEmptyFields() && validationNumerics()){
            //zamiana wszystkiego w onlnie
            //plec
            if(rbMale.isSelected()){
                onlineUser.setMale(true);
            }else if(rbFemale.isSelected()){
                onlineUser.setMale(false);
            }
            //mesureMain
            onlineUser.getMainMeasurementDB().setSizeCm(Integer.valueOf(tfSizeCm.getText()));
            onlineUser.getMainMeasurementDB().setCaloric(Integer.valueOf(tfCaloric.getText()));


            DatabaseController.updateUserDBMainMesure(onlineUser.getIdUser(),onlineUser);
        }


    }

    @FXML
    void loadDataAction() {
        lValidation.setVisible(false);
        //inicjalizacja wartości jesli sa juz podane
        //Plec
        if(onlineUser.isMale()){
            rbMale.setSelected(true);
        }else{
            rbFemale.setSelected(true);
        }
        //MasaCiala
        String bW = new String("666");
        //pobiramy liste


        try{
            List<MesurementDBHelper> mesurementList = onlineUser.getMainMeasurementDB().getMesurements();

            bW = String.valueOf(mesurementList.get(mesurementList.size()-1).getBodyWeight());

        }catch (Exception e){
            System.out.println("CalculatorBMR "+e);
            bW = "Brak Wagi w Bazie Danych";

        }




        tfBodyWeight.setText(bW);
        //wzrost
        tfSizeCm.setText(String.valueOf(onlineUser.getMainMeasurementDB().getSizeCm()));
        //wiek
        int age = Period.between(DateUtils.asLocalDate( onlineUser.getDateOfBirth() ),LocalDate.now()).getYears();
        tfage.setText(String.valueOf(age));
        //Zapotrzebowanie kaloryczne
        tfCaloric.setText(String.valueOf(onlineUser.getMainMeasurementDB().getCaloric()));
    }




    @FXML
    void initialize() {
        assert rbMale != null : "fx:id=\"rbMale\" was not injected: check your FXML file 'CalculateBMR.fxml'.";
        assert SexGroup != null : "fx:id=\"SexGroup\" was not injected: check your FXML file 'CalculateBMR.fxml'.";
        assert rbFemale != null : "fx:id=\"rbFemale\" was not injected: check your FXML file 'CalculateBMR.fxml'.";
        assert tfBodyWeight != null : "fx:id=\"tfBodyWeight\" was not injected: check your FXML file 'CalculateBMR.fxml'.";
        assert tfSizeCm != null : "fx:id=\"tfSizeCm\" was not injected: check your FXML file 'CalculateBMR.fxml'.";
        assert tfage != null : "fx:id=\"tfage\" was not injected: check your FXML file 'CalculateBMR.fxml'.";
        assert cbPhysicalActivity != null : "fx:id=\"cbPhysicalActivity\" was not injected: check your FXML file 'CalculateBMR.fxml'.";
        assert cbGoal != null : "fx:id=\"cbGoal\" was not injected: check your FXML file 'CalculateBMR.fxml'.";
        assert tfCaloric != null : "fx:id=\"tfCaloric\" was not injected: check your FXML file 'CalculateBMR.fxml'.";

        //inicjalizacja Combo boxaów
        cbPhysicalActivity.getItems().addAll(
                "Leżący lub siedzący tryb życia, brak aktywności fizycznej",
                "Praca siedząca, aktywność fizyczna na niskim poziomie",
                "Praca niefizyczna, trening 2 razy w tygodniu",
                "Lekka praca fizyczna, trening 3-4 razy w tygodniu",
                "Praca fizyczna, trening 5 razy w tygodniu",
                "Ciężka praca fizyczna, codzienny trening"
        );
        cbGoal.getItems().addAll(
                "Zwiększenie Masy Ciała",
                "Zmniejszenie Masy Ciała",
                "Utrzymanie Masy Ciała"
        );



    }

    private Boolean validationNotEmptyFields(){
        boolean returne = true;
        //sprawdzanie radioboxow czy wybrane
        if(SexGroup.getSelectedToggle() == null){
            lValidation.setText("Nie podano 'Płci'");
            lValidation.setVisible(true);
            returne = false;
        }else if(tfBodyWeight.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation.setText("Nie podano 'Masy Ciała'");
            lValidation.setVisible(true);
            returne = false;
        }else if(tfSizeCm.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation.setText("Nie podano 'Wzrostu'");
            lValidation.setVisible(true);
            returne = false;
        }else if(tfage.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation.setText("Nie podano 'Wieku'");
            lValidation.setVisible(true);
            returne = false;
        }else if(tfCaloric.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation.setText("Nie podano 'Zapotrzebowania'");
            lValidation.setVisible(true);
            returne = false;
        }

        return returne;
    }
    private Boolean validationNotEmptyComboBox(){
        boolean returne = true;
        if (cbPhysicalActivity.getValue() == null || cbPhysicalActivity.getValue().toString().isEmpty()){
            lValidation.setText("Nie wybrano Aktywnosci Fizycznej");
            lValidation.setVisible(true);
            returne = false;
        }else if (cbGoal.getValue() == null || cbGoal.getValue().toString().isEmpty()){
            lValidation.setText("Nie wybrano Celu");
            lValidation.setVisible(true);
            returne = false;
        }
        return returne;
    }
    private Boolean validationNumerics(){ // tfage.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))
        boolean returne = true;
        if (tfBodyWeight.getText().matches("(-|\\+)?[0-9]+")) {
        } else {
            lValidation.setText("Wartość podana w 'Masa Ciała' jest błędna");
            lValidation.setVisible(true);
            returne = false;
        }
        if (tfSizeCm.getText().matches("(-|\\+)?[0-9]+")) {

        } else {
            lValidation.setText("Wartość podana we 'Wzrost' jest błędna");
            lValidation.setVisible(true);
            returne = false;
        }
        if (tfage.getText().matches("(-|\\+)?[0-9]+")) {
        } else {
            lValidation.setText("Wartość podana w 'Wiek' jest błędna");
            lValidation.setVisible(true);
            returne = false;
        }
        if (tfCaloric.getText().matches("(-|\\+)?[0-9]+")) {
        } else {
            lValidation.setText("Wartość podana w 'Zapotrzebowanie' jest błędna");
            lValidation.setVisible(true);
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
