package sample.controller.userStaff;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.MesurementDBHelper;
import sample.DatabaseHibernate.UserDB;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marcin on 2017-02-21.
 */
public class HistoryMesureTablesController {
    private UserDB onlineUser;
    private DateFormat format;
    private Date date;
    private long selectedIdMesurementDBHelper;

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
    private TextField tfBodyWeight;


    @FXML
    private TextField tfPath;
    @FXML
    private ListView<String> lvMesurements;

    @FXML
    private Label lValidation1;
    @FXML
    private Label lValidation12;



    @FXML
    void choosePathAction( ) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory =
                directoryChooser.showDialog(null);

        if(selectedDirectory == null){
            tfPath.setText("No Directory selected");
        }else{
            tfPath.setText(selectedDirectory.getAbsolutePath());
        }

    }

    @FXML
    void deleteMesurement() {
        lValidation1.setVisible(false);

        if(lvMesurements.getSelectionModel().getSelectedItem()!= null){
            //usuniecie z bazy danych
            DatabaseController.deleteMesurementDBHelper(selectedIdMesurementDBHelper);
            //odswiezenie listy
            lvMesurements.setItems(DatabaseController.getAllMesurementUser(onlineUser.getMainMeasurementDB().getIdMainMeasurement()));

        }else{
            lValidation1.setText("Nie wybrano Pomiaru do usunięcia");
        }


    }

    @FXML
    void savechartAction() {

        Parent root;
        try {
            lValidation1.setVisible(false);

            if((!tfPath.getText().equals("No Directory selected")) && (!tfPath.getText().equals(""))){


                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/userStaff/LineChartSizeToDate.fxml"));
                root = loader.load();

                LineChartSizeToDateController lineChartSizeToDateController = loader.getController();
                lineChartSizeToDateController.setOnlineUser(onlineUser);
                lineChartSizeToDateController.initialChart();

                Scene scenaa = new Scene(root);
                lineChartSizeToDateController.exportChartToFile(scenaa,tfPath.getText()+"\\chart12.png");

            }else{
                lValidation1.setText("Nie podano ścieżki");
                lValidation1.setVisible(true);

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void chartPreviewAction() {

        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/userStaff/LineChartSizeToDate.fxml"));
            root = loader.load();

            LineChartSizeToDateController lineChartSizeToDateController = loader.getController();
            lineChartSizeToDateController.setOnlineUser(onlineUser);
            lineChartSizeToDateController.initialChart();

            Stage stage = new Stage();
            stage.setTitle("Okno Podglądu wykresu");
            stage.setScene(new Scene(root, 700, 550));
            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void saveMesureAction() {
        lValidation12.setVisible(false);
        if(this.validationNotEmptyFields() && this.validationNumerics()){ // jesli wartoscia sa dobre nastepuje update
            //tworzenie obiektu do zamiany
            MesurementDBHelper updateMesurementDBHelper = new MesurementDBHelper(date,Double.valueOf(tfBodyWeight.getText()),
                    Integer.valueOf(tfNeck.getText()),Integer.valueOf(tfChest.getText()),Integer.valueOf(tfWaist.getText()),
                    Integer.valueOf(tfHips.getText()),Integer.valueOf(tfLArm.getText()),Integer.valueOf(tfRArm.getText()),
                    Integer.valueOf(tfLForearm.getText()),Integer.valueOf(tfRForearm.getText()),
                    Integer.valueOf(tfLThigh.getText()), Integer.valueOf(tfRThigh.getText()),
                    Integer.valueOf(tfLCalf.getText()),Integer.valueOf(tfRCalf.getText()));
            //wywolanie zamiany obiektow
            DatabaseController.updateMesurementDBHelper(selectedIdMesurementDBHelper,updateMesurementDBHelper);
            onlineUser = DatabaseController.selectUser(onlineUser.getNick());
        }

    }

    @FXML
    void initialize() {
                lvMesurements.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try{
                    System.out.println("Choose: "+lvMesurements.getSelectionModel().getSelectedItem());

                    //tworzenie obiektu date
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    date = format.parse(lvMesurements.getSelectionModel().getSelectedItem());

                    for (MesurementDBHelper mh :onlineUser.getMainMeasurementDB().getMesurements()) {
                        if(mh.getDateOfMesurement().equals(date)){
                            //ladowanie treningu
                    tfNeck.setText(String.valueOf(mh.getNeckSize()));
                    tfChest.setText(String.valueOf(mh.getChestSize()));
                    tfWaist.setText(String.valueOf(mh.getWaistSize()));
                    tfHips.setText(String.valueOf(mh.getHipsSize()));
                    tfLArm.setText(String.valueOf(mh.getlArmSize()));
                    tfRArm.setText(String.valueOf(mh.getrArmSize()));
                    tfLForearm.setText(String.valueOf(mh.getlForearmSize()));
                    tfRForearm.setText(String.valueOf(mh.getrForearmSize()));
                    tfLThigh.setText(String.valueOf(mh.getlThighSize()));
                    tfRThigh.setText(String.valueOf(mh.getrThighSize()));
                    tfRCalf.setText(String.valueOf(mh.getrCalfSize()));
                    tfLCalf.setText(String.valueOf(mh.getlCalfSize()));
                    tfBodyWeight.setText(String.valueOf(mh.getBodyWeight()));

                    selectedIdMesurementDBHelper = mh.getIdMesurement();
                        }

                    }
                }catch (Exception e ){
                    System.out.println("Nie ma nic w liscie ");
                }

            }
        });

    }


    private Boolean validationNumerics(){ // tfage.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))
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
        }else if (!tfBodyWeight.getText().matches("(-|\\+)?[0-9]+")) {
            lValidation12.setText("Wartość 'Masa Ciała'-błąd");
            lValidation12.setVisible(true);
            returne = false;
        }



        return returne;
    }

    private Boolean validationNotEmptyFields(){
        boolean returne = true;
        //sprawdzanie radioboxow czy wybrane
        if(tfNeck.getText().equals("")) {//sprawdzenie wartosci czy wpisane
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
        }else if(tfBodyWeight.getText().equals("")) {//sprawdzenie wartosci czy wpisane
            lValidation12.setText("Nie podano 'Masy Ciała'");
            lValidation12.setVisible(true);
            returne = false;
        }

        return returne;
    }


    public UserDB getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUserAndInitialList(UserDB onlineUser) {
        this.onlineUser = onlineUser;
        //inicjalizacja listy
        System.out.println("inicjalizacja Listy");
        onlineUser = DatabaseController.selectUser(onlineUser.getNick());
        lvMesurements.setItems(DatabaseController.getAllMesurementUser(onlineUser.getMainMeasurementDB().getIdMainMeasurement()));
    }
}
