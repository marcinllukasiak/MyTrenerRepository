package sample.controller.adminStaff;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.DatabaseHibernate.DatabaseController;

/**
 * Created by Marcin on 2017-02-09.
 */
public class RightTrainingController {
    private SingleTableTrainingController singleTableTrainingController;

    @FXML
    private ListView<String> lvTrenings;

    @FXML
    private TextField tfNewTreningName;

    @FXML
    void addTreningSchema() {

    }

    @FXML
    void deleteTreningSchema() {

    }

    public SingleTableTrainingController getSingleTableTrainingController() {
        return singleTableTrainingController;
    }

    public void setSingleTableTrainingController(SingleTableTrainingController singleTableTrainingController) {
        this.singleTableTrainingController = singleTableTrainingController;
    }

    @FXML
    void initialize() {


        lvTrenings.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Choose: "+lvTrenings.getSelectionModel().getSelectedItem());

                //wydobycie id treningu ze stringa
                int iend = lvTrenings.getSelectionModel().getSelectedItem().indexOf("|");
                String idTreing= lvTrenings.getSelectionModel().getSelectedItem().substring(0 , iend-1);
                System.out.println(idTreing);
                //wysylanie nazwy treningu
                singleTableTrainingController.setIdTreningSchema(idTreing);
                singleTableTrainingController.refreshTables();

            }
        });

        // pobieranie listy userów
        //inicjalizowanie listyView TreningSchema
        lvTrenings.setItems(DatabaseController.getAllTreningShemaName());

    }

}
