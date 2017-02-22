package sample.controller.userStaff;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.UserDB;

/**
 * Created by Marcin on 2017-02-21.
 */
public class HistoryMesureTablesController {
    private UserDB onlineUser;

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
    private TextField tfPath;
    @FXML
    private TextField tfBodyWeight;

    @FXML
    private DatePicker dpDateOfMeasurement;

    @FXML
    private ListView<String> lvMesurements;

    @FXML
    private Label lValidation1;
    @FXML
    private Label lValidation12;


    @FXML
    void loadListAction( ) {
        System.out.println("inicjalizacja Listy");
        lvMesurements.setItems(DatabaseController.getAllMesurementUser(onlineUser.getMainMeasurementDB().getIdMainMeasurement()));
    }

    @FXML
    void choosePathAction( ) {

    }

    @FXML
    void deleteMesurement( ) {


    }

    @FXML
    void generateChart( ) {

    }

    @FXML
    void saveMesureAction( ) {

    }

    @FXML
    void initialize() {
                lvMesurements.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try{
                    System.out.println("Choose: "+lvMesurements.getSelectionModel().getSelectedItem());

                    //szukanie wybranej daty

                    //ladowanie treningu

                }catch (Exception e ){
                    System.out.println("Nie ma nic w liscie ");
                }


            }
        });

        // pobieranie listy pomistow
        //inicjalizowanie listyView Pomisroe
        try{
            System.out.println(onlineUser); // <<<<< ONLINEUSER jest jeszcze nullem
            lvMesurements.setItems(DatabaseController.getAllMesurementUser(onlineUser.getMainMeasurementDB().getIdMainMeasurement()));

        }catch (Exception e){
            lvMesurements.setItems(null);

        }


    }


    public UserDB getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(UserDB onlineUser) {
        this.onlineUser = onlineUser;
    }
}
