package sample.controller.adminStaff;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.DatabaseHibernate.DatabaseController;

import sample.DatabaseHibernate.TreiningSchemaDBHelper;
import sample.DatabaseHibernate.TreningSchemeDB;

import java.awt.*;
import java.util.*;

/**
 * Created by Marcin on 2017-01-30.
 */
public class SingleTableTrainingController {
    private String idTreningSchemaString;

    private TreningSchemeDB loadedTrainingDB;
    private TreiningSchemaDBHelper selectedRow;

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
    private Label labelErrorId;

    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonAdd;

    public void setVisableFBoff(){ //field and button
        buttonAdd.setVisible(false);
        buttonDelete.setVisible(false);
        buttonEdit.setVisible(false);

        tfNumber.setVisible(false);
        tfNameExercise.setVisible(false);
        tfCountS.setVisible(false);
        tfCountR.setVisible(false);
    }
    public void setVisableFon(){ //field
        tfNumber.setVisible(true);
        tfNameExercise.setVisible(true);
        tfCountS.setVisible(true);
        tfCountR.setVisible(true);
    }

//pokazywanie odpowiedniego przycisku
    @FXML
    void addOptionVisableAction() {
        this.clearField();
        this.setVisableFBoff();
        buttonAdd.setVisible(true);
        this.setVisableFon();
        labelErrorId.setVisible(false);
        selectedRow = null;
    }
    @FXML
    void deleteOptionVisableAction() {
        this.clearField();
        this.setVisableFBoff();
        buttonDelete.setVisible(true);
        this.setVisableFon();
        labelErrorId.setVisible(false);
        selectedRow = null;
    }
    @FXML
    void editOptionVisableAction() {
        this.clearField();
        this.setVisableFBoff();
        buttonEdit.setVisible(true);
        this.setVisableFon();
        labelErrorId.setVisible(false);
        selectedRow = null;
    }


    public void refreshTables(){

        //odswiezenie zaladowanego treningu
        loadedTrainingDB = DatabaseController.selectTreningScheme(Long.valueOf(idTreningSchemaString));
       // System.out.println("refresh idTrening:"+loadedTrainingDB);
        //sprawdzenie dnia tygodnia
        if(tb1.isSelected()){
            this.mondayTableAction();

        }else if(tb2.isSelected()){
            this.tuesdayTableAction();

        }else if(tb3.isSelected()){
            this.wednesdayTableAction();

        }else if(tb4.isSelected()){
            this.thursdayTableAction();

        }else if(tb5.isSelected()){
            this.friadayTableAction();

        }else if(tb6.isSelected()){
            this.saturdayTableAction();

        }else if(tb7.isSelected()){
            this.sundayTableAction();
        }


    }

    @FXML
    void deleteExerciseAction() {

        //wykonanie zapytania usuwajacego wybrana wartosc
        DatabaseController.deleteTreningSchemeHelpre(selectedRow.getIdTreningHelper());

        //odswiezenie tablicy
        this.refreshTables();
        // czyszczenie Filedow
        this.clearField();





    }
    @FXML
    void editExerciseAction() {
        //SPRAWDZENIE POPRAWNOSCI WPROWADZONYCH DANYCH !!!! - dopisac
        labelErrorId.setVisible(false);
        try{
            if(!(selectedRow.equals(null))){
                if(this.validationNumerics()){
                    System.out.println( "Edit pwtla po porównaniu "+selectedRow);
                    //wykonanie zapytania zmieniajacego wybrana wartosc
                    DatabaseController.updateTreningSchemeHelpre(selectedRow.getIdTreningHelper(),Integer.valueOf(tfNumber.getText()), tfNameExercise.getText(), Integer.valueOf(tfCountS.getText()), Integer.valueOf(tfCountR.getText()));

                    //odswiezenie zaladowanego treningu
                    this.refreshTables();
                    // czyszczenie Filedow
                    this.clearField();
                }else{
                    labelErrorId.setText("Wprowadź wartość liczbową a nie text");
                    labelErrorId.setVisible(true);

                }
            }
       }catch (Exception e){
           System.out.println("editExerciseAction Exception: "+e);
           labelErrorId.setText("Nie wybrano elementu do edycji");
           labelErrorId.setVisible(true);
       }


    }
    @FXML
    void addExerciseAction() {
        //SPRAWDZENIE POPRAWNOSCI WPROWADZONYCH DANYCH !!!! - dopisac
        if(this.validationValues()){

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

            // czyszczenie Filedow
            this.clearField();

        }

    }

    @FXML
    void initialize() {
        assert tabkeTraining != null : "fx:id=\"tabkeTraining\" was not injected: check your FXML file 'SingleTableTrainingController.fxml'.";
        assert nrColum != null : "fx:id=\"nrColum\" was not injected: check your FXML file 'SingleTableTrainingController.fxml'.";
        assert nameWorkColumn != null : "fx:id=\"nameWorkColumn\" was not injected: check your FXML file 'SingleTableTrainingController.fxml'.";
        assert countSColumn != null : "fx:id=\"countSColumn\" was not injected: check your FXML file 'SingleTableTrainingController.fxml'.";
        assert countRColumn != null : "fx:id=\"countRColumn\" was not injected: check your FXML file 'SingleTableTrainingController.fxml'.";
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


        //klikanie myszka w wiersz
        tabkeTraining.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try{
                    selectedRow = tabkeTraining.getSelectionModel().getSelectedItem();
                    tfNumber.setText(String.valueOf(selectedRow.getNumberOfExecises()));
                    tfNameExercise.setText(selectedRow.getNameOfExercises());
                    tfCountS.setText(String.valueOf(selectedRow.getNumberOfSeries()));
                    tfCountR.setText(String.valueOf(selectedRow.getNumberOfRepetitions()));
                }catch (Exception e){
                    System.err.println("Initial Single Training "+e);
                }

            }
        });

    }

    @FXML
    void friadayTableAction() {
        selectedRow = null;
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getFridayWorkout()));
    }

    @FXML
    void mondayTableAction() {
        //inicjalizacjia tebeli danymi z ponniedziałku , pobiera nazwe treningu
        selectedRow = null;
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getMondayWorkout()));
    }

    @FXML
    void saturdayTableAction() {
        selectedRow = null;
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getSaturdayWorkout()));
    }

    @FXML
    void sundayTableAction() {
        selectedRow = null;
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getSundayWorkout()));
    }

    @FXML
    void thursdayTableAction() {
        selectedRow = null;
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getThursdayWorkout()));
    }

    @FXML
    void tuesdayTableAction() {
        selectedRow = null;
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getTuesdayWorkout()));
    }

    @FXML
    void wednesdayTableAction() {
        selectedRow = null;
        tabkeTraining.setItems(FXCollections.observableArrayList(loadedTrainingDB.getWednesdayWorkout()));
    }

    //getters and setters
    public TreningSchemeDB getLoadedTrainingDB() {
        return loadedTrainingDB;
    }

    private void setLoadedTrainingDB(TreningSchemeDB loadedTrainingDB) {
        this.loadedTrainingDB = loadedTrainingDB;
    }

    public void clearField(){
        tfNumber.clear();
        tfNameExercise.clear();
        tfCountS.clear();
        tfCountR.clear();
    }



    private Boolean validationNumerics(){  //tfNumber.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))
        boolean returne = true;
        if (tfNumber.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
        } else {
            returne = false;
        }
        if (tfNumber.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
        } else {
            returne = false;
        }
        if (tfNumber.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
        } else {
            returne = false;
        }

        return returne;
    }

    private boolean validationValues(){

        labelErrorId.setVisible(false);
        boolean notEmptyField = true;
        if(this.validationNumerics()){
            // sprawdzenie czy fieldy sa pelne
            if(tfNumber.getText().equals("")){
                labelErrorId.setText("Numer ćwiczenia nie został wprowadzony");
                labelErrorId.setVisible(true);
                notEmptyField = false;
            }else if(tfNameExercise.getText().equals("")){
                labelErrorId.setText("Nazwa ćwiczenia nie został wprowadzony");
                labelErrorId.setVisible(true);
                notEmptyField = false;
            }else if(tfCountS.getText().equals("")){
                labelErrorId.setText("Liczba serii nie został wprowadzony");
                labelErrorId.setVisible(true);
                notEmptyField = false;
            }else if(tfCountR.getText().equals("")){
                labelErrorId.setText("Nazwa ćwiczenia nie został wprowadzony");
                labelErrorId.setVisible(true);
                notEmptyField = false;
            }

            if(notEmptyField) {

                java.util.List<TreiningSchemaDBHelper> list = null;
                //wczytywanie odpowiednich wartosci do listy
                if (tb1.isSelected()) {
                    list = loadedTrainingDB.getMondayWorkout();
                } else if (tb2.isSelected()) {
                    list = loadedTrainingDB.getTuesdayWorkout();

                } else if (tb3.isSelected()) {
                    list = loadedTrainingDB.getWednesdayWorkout();

                } else if (tb4.isSelected()) {
                    list = loadedTrainingDB.getThursdayWorkout();

                } else if (tb5.isSelected()) {
                    list = loadedTrainingDB.getFridayWorkout();

                } else if (tb6.isSelected()) {
                    list = loadedTrainingDB.getSaturdayWorkout();

                } else if (tb7.isSelected()) {
                    list = loadedTrainingDB.getSundayWorkout();
                }

                //sprawdzenie numeru czy juz nie istnieje taki
                int i = 0;

                while (i < list.size()) {
                    if(Integer.valueOf(tfNumber.getText()).equals(list.get(i).getNumberOfExecises())){
                        labelErrorId.setText("Podana wartość Numeru ćwiczenia jest niedozwolona");
                        labelErrorId.setVisible(true);
                        notEmptyField = false;
                    }else if(tfNameExercise.getText().equals(list.get(i).getNameOfExercises())){
                        labelErrorId.setText("Podana wartość Nazwy ćwiczenia jest niedozwolona");
                        labelErrorId.setVisible(true);
                        notEmptyField = false;
                    }

                    i++;
                }
            }
        }else{
            labelErrorId.setText("Wprowadź wartość liczbową a nie text");
            labelErrorId.setVisible(true);
            notEmptyField = false;
        }



        return notEmptyField;
    }



    public String getIdTreningSchema() {
        return idTreningSchemaString;
    }

    public void setIdTreningSchema(String idTreningSchema) {
        this.idTreningSchemaString = idTreningSchema;
    }
}
