package sample.controller.adminStaff;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.TreningSchemeDB;

/**
 * Created by Marcin on 2017-02-09.
 */
public class RightTrainingController {
    private SingleTableTrainingController singleTableTrainingController;
    private String idTreing ;
    @FXML
    private ListView<String> lvTrenings;

    @FXML
    private TextField tfNewTreningName;

    @FXML
    private TextField tfNewAutor;

    @FXML
    private TextField tfNewLevel;

    @FXML
    private TextField tfNewDays;
    @FXML
    private Label labelWarning;

    @FXML
    void addTreningSchema() {
        labelWarning.setVisible(false);
        try{
            //walidacja numerów
            if(Integer.valueOf(tfNewLevel.getText())>=0 ){
                if (tfNewDays.getText().matches("(()?[0-7]+()?)+")) {
                    //dodanie do bazy danych
                    if(!DatabaseController.insertTreningScheme(new TreningSchemeDB(tfNewTreningName.getText(),Integer.valueOf(tfNewLevel.getText()),Integer.valueOf(tfNewDays.getText()),tfNewAutor.getText(),null,null,null,null,null,null,null))){
                        labelWarning.setText("Podany trening juz istnieje");
                        labelWarning.setVisible(true);
                    }
                    // odswiezenie
                    lvTrenings.setItems(DatabaseController.getAllTreningShemaName());
                } else {
                    //wyswietlenie komunikatu o zlych danych
                    labelWarning.setText("Pole DNI posiada błąd");
                    labelWarning.setVisible(true);
                }

            }else{
                labelWarning.setText("Pole POZIOM musi być >0");
                labelWarning.setVisible(true);
            }
        }catch (Exception e){
            labelWarning.setText("Pole POZIOM posiada błąd");
            labelWarning.setVisible(true);
        }





    }

    @FXML
    void deleteTreningSchema() {
        //wykonanie zapytania usuwajacego
        DatabaseController.deleteTreningScheme(Long.valueOf(idTreing));
        //odswiezenie center
        singleTableTrainingController.refreshTablesNull();
        //odswiezenie listy right
        lvTrenings.setItems(DatabaseController.getAllTreningShemaName());
    }

    public SingleTableTrainingController getSingleTableTrainingController() {
        return singleTableTrainingController;
    }

    public void setSingleTableTrainingController(SingleTableTrainingController singleTableTrainingController) {
        this.singleTableTrainingController = singleTableTrainingController;
    }

    @FXML
    void initialize() {
        labelWarning.setVisible(false);

        lvTrenings.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try{
                    System.out.println("Choose: "+lvTrenings.getSelectionModel().getSelectedItem());

                    //wydobycie id treningu ze stringa
                    int iend = lvTrenings.getSelectionModel().getSelectedItem().indexOf("|");
                    idTreing= lvTrenings.getSelectionModel().getSelectedItem().substring(0 , iend-1);
                    System.out.println(idTreing);
                    //wysylanie nazwy treningu
                    singleTableTrainingController.setIdTreningSchema(idTreing);
                    singleTableTrainingController.refreshTables();
                }catch (Exception e ){
                    System.out.println("Nie ma nic w liscie ");
                }


            }
        });

        // pobieranie listy userów
        //inicjalizowanie listyView TreningSchema
        lvTrenings.setItems(DatabaseController.getAllTreningShemaName());

    }

}
