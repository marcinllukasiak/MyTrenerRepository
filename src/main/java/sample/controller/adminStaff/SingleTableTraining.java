package sample.controller.adminStaff;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.DatabaseHibernate.DatabaseController;

import sample.DatabaseHibernate.TreiningSchemaDBHelper;
import sample.DatabaseHibernate.TreningSchemeDB;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Marcin on 2017-01-30.
 */
public class SingleTableTraining {

    private TreningSchemeDB loadedTrainingDB;


    @FXML
    private TableView<TreiningSchemaDBHelper> tabkeTraining;

    @FXML
    private TableColumn<TreiningSchemaDBHelper, Integer> nrColum;

    @FXML
    private TableColumn<TreiningSchemaDBHelper, String> nameWorkColumn;

    @FXML
    private TableColumn<TreiningSchemaDBHelper, Integer> countSColumn;

    @FXML
    private TableColumn<TreiningSchemaDBHelper, Integer> countRColumn;

    @FXML
    private TextField tfNameExercise;

    @FXML
    private TextField tfCountS;

    @FXML
    private TextField tfCountR;

    @FXML
    private TextField tfNumber;

    @FXML
    private ToggleGroup daysGroup;
    @FXML
    private ToggleButton tb1;

    @FXML
    private ToggleButton tb2;

    @FXML
    private ToggleButton tb3;

    @FXML
    private ToggleButton tb4;

    @FXML
    private ToggleButton tb5;

    @FXML
    private ToggleButton tb6;

    @FXML
    private ToggleButton tb7;


    @FXML
    void addExerciseAction() {
        //sprawdzenie dnia tygodnia
            if(tb1.isSelected()){
                //utworzenie obiektu helper
                TreiningSchemaDBHelper treiningSchemaDBHelper = new TreiningSchemaDBHelper(Integer.valueOf(tfNumber.getText()), tfNameExercise.getText(), Integer.valueOf(tfCountS.getText()), Integer.valueOf(tfCountR.getText()));
                //dodajemy do listy
                loadedTrainingDB.getMondayWorkout().add(treiningSchemaDBHelper);
                //aktualizujemy w db trening schema (musimy dodat helpera i zaktualizowac liste)
                DatabaseController.updateTreningScheme(loadedTrainingDB.getIdTrening(),treiningSchemaDBHelper,1);
                //odswiezenie widoku
                this.mondayTableAction();

            }else if(tb2.isSelected()){
                //utworzenie obiektu helper
                TreiningSchemaDBHelper treiningSchemaDBHelper = new TreiningSchemaDBHelper(Integer.valueOf(tfNumber.getText()), tfNameExercise.getText(), Integer.valueOf(tfCountS.getText()), Integer.valueOf(tfCountR.getText()));
                //dodajemy do listy
                loadedTrainingDB.getTuesdayWorkout().add(treiningSchemaDBHelper);
                //aktualizujemy w db trening schema (musimy dodat helpera i zaktualizowac liste)
                DatabaseController.updateTreningScheme(loadedTrainingDB.getIdTrening(),treiningSchemaDBHelper,2);
                //odswiezenie widoku
                this.tuesdayTableAction();

            }else if(tb3.isSelected()){
                //utworzenie obiektu helper
                TreiningSchemaDBHelper treiningSchemaDBHelper = new TreiningSchemaDBHelper(Integer.valueOf(tfNumber.getText()), tfNameExercise.getText(), Integer.valueOf(tfCountS.getText()), Integer.valueOf(tfCountR.getText()));
                //dodajemy do listy
                loadedTrainingDB.getWednesdayWorkout().add(treiningSchemaDBHelper);
                //aktualizujemy w db trening schema (musimy dodat helpera i zaktualizowac liste)
                DatabaseController.updateTreningScheme(loadedTrainingDB.getIdTrening(),treiningSchemaDBHelper,3);
                //odswiezenie widoku
                this.wednesdayTableAction();

            }else if(tb4.isSelected()){
                //utworzenie obiektu helper
                TreiningSchemaDBHelper treiningSchemaDBHelper = new TreiningSchemaDBHelper(Integer.valueOf(tfNumber.getText()), tfNameExercise.getText(), Integer.valueOf(tfCountS.getText()), Integer.valueOf(tfCountR.getText()));
                //dodajemy do listy
                loadedTrainingDB.getThursdayWorkout().add(treiningSchemaDBHelper);
                //aktualizujemy w db trening schema (musimy dodat helpera i zaktualizowac liste)
                DatabaseController.updateTreningScheme(loadedTrainingDB.getIdTrening(),treiningSchemaDBHelper,4);
                //odswiezenie widoku
                this.thursdayTableAction();

            }else if(tb5.isSelected()){
                //utworzenie obiektu helper
                TreiningSchemaDBHelper treiningSchemaDBHelper = new TreiningSchemaDBHelper(Integer.valueOf(tfNumber.getText()), tfNameExercise.getText(), Integer.valueOf(tfCountS.getText()), Integer.valueOf(tfCountR.getText()));
                //dodajemy do listy
                loadedTrainingDB.getFridayWorkout().add(treiningSchemaDBHelper);
                //aktualizujemy w db trening schema (musimy dodat helpera i zaktualizowac liste)
                DatabaseController.updateTreningScheme(loadedTrainingDB.getIdTrening(),treiningSchemaDBHelper,5);
                //odswiezenie widoku
                this.friadayTableAction();

            }else if(tb6.isSelected()){
                //utworzenie obiektu helper
                TreiningSchemaDBHelper treiningSchemaDBHelper = new TreiningSchemaDBHelper(Integer.valueOf(tfNumber.getText()), tfNameExercise.getText(), Integer.valueOf(tfCountS.getText()), Integer.valueOf(tfCountR.getText()));
                //dodajemy do listy
                loadedTrainingDB.getSaturdayWorkout().add(treiningSchemaDBHelper);
                //aktualizujemy w db trening schema (musimy dodat helpera i zaktualizowac liste)
                DatabaseController.updateTreningScheme(loadedTrainingDB.getIdTrening(),treiningSchemaDBHelper,6);
                //odswiezenie widoku
                this.saturdayTableAction();

            }else if(tb7.isSelected()){
                //utworzenie obiektu helper
                TreiningSchemaDBHelper treiningSchemaDBHelper = new TreiningSchemaDBHelper(Integer.valueOf(tfNumber.getText()), tfNameExercise.getText(), Integer.valueOf(tfCountS.getText()), Integer.valueOf(tfCountR.getText()));
                //dodajemy do listy
                loadedTrainingDB.getSundayWorkout().add(treiningSchemaDBHelper);
                //aktualizujemy w db trening schema (musimy dodat helpera i zaktualizowac liste)
                DatabaseController.updateTreningScheme(loadedTrainingDB.getIdTrening(),treiningSchemaDBHelper,7);
                //odswiezenie widoku
                this.sundayTableAction();

            }
        //utworzenie obiektu trening shema helper
        //dodanie do listy w trning shema
        //aktualizacja w db
        //odswiezenie widoku (sprawdzenie ktuury dzien tygodnia jest aktywny i zaladowanie)

    }

    @FXML
    void initialize() {
        assert tabkeTraining != null : "fx:id=\"tabkeTraining\" was not injected: check your FXML file 'SingleTableTraining.fxml'.";
        assert nrColum != null : "fx:id=\"nrColum\" was not injected: check your FXML file 'SingleTableTraining.fxml'.";
        assert nameWorkColumn != null : "fx:id=\"nameWorkColumn\" was not injected: check your FXML file 'SingleTableTraining.fxml'.";
        assert countSColumn != null : "fx:id=\"countSColumn\" was not injected: check your FXML file 'SingleTableTraining.fxml'.";
        assert countRColumn != null : "fx:id=\"countRColumn\" was not injected: check your FXML file 'SingleTableTraining.fxml'.";
        // ladowanie przykladowego obiektu na którym bedziemy testowac
        loadedTrainingDB = DatabaseController.selectTreningScheme(5,135); //testowy
        System.out.println("loadedTrainingDB = " + loadedTrainingDB);
        //Umozliwanie edycji po kliknieciu
//        tabkeTraining.setEditable(true);
//        Callback<TableColumn, TableCell> cellFactory =
//                new Callback<TableColumn, TableCell>() {
//                    public TableCell call(TableColumn p) {
//                        return new EditingCell();
//                    }
//                };

        //polaczenie z klasa kolumn
        nrColum.setCellValueFactory(new PropertyValueFactory<TreiningSchemaDBHelper,Integer>("numberOfExecises"));
        nameWorkColumn.setCellValueFactory(new PropertyValueFactory<TreiningSchemaDBHelper,String>("nameOfExercises"));
        countSColumn.setCellValueFactory(new PropertyValueFactory<TreiningSchemaDBHelper,Integer>("numberOfSeries"));
        countRColumn.setCellValueFactory(new PropertyValueFactory<TreiningSchemaDBHelper,Integer>("numberOfRepetitions"));
        System.out.println("Trening Edit Screen");


    }

    @FXML
    void friadayTableAction() {
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getFridayWorkout()));
    }

    @FXML
    void mondayTableAction() {
        //inicjalizacjia tebeli danymi z ponniedziałku , pobiera nazwe treningu
        System.out.println("loadedTrainingDB = "+loadedTrainingDB);
        System.out.println(loadedTrainingDB.getMondayWorkout());

        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getMondayWorkout()));
    }

    @FXML
    void saturdayTableAction() {
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getSaturdayWorkout()));
    }

    @FXML
    void sundayTableAction() {
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getSundayWorkout()));
    }

    @FXML
    void thursdayTableAction() {
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getThursdayWorkout()));
    }

    @FXML
    void tuesdayTableAction() {
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getTuesdayWorkout()));
    }

    @FXML
    void wednesdayTableAction() {
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getWednesdayWorkout()));
    }

    //getters and setters
    public TreningSchemeDB getLoadedTrainingDB() {
        return loadedTrainingDB;
    }

    public void setLoadedTrainingDB(TreningSchemeDB loadedTrainingDB) {
        this.loadedTrainingDB = loadedTrainingDB;
    }
}
