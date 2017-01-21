package sample.controller.startStaff;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import sample.DatabaseHibernate.DatabaseController;

import java.io.IOException;

public class BorderPaneMainController {

    @FXML // wstrzykniety główny border pane w którym bedziemy zamieniac sceny do wyswietlania
    private BorderPane borderPaneMainId;

    @FXML//wstrzykniety fxml z left menu , referencja do kontrolera
    private LeftMenuButtonsController leftMenuButtonsIdController;

    @FXML
    private void initialize(){
        leftMenuButtonsIdController.setBorderPaneMainController(this); // przekazanie referencji głównego BorderPana do kontrolera klasy LeftMenu
    }

    //metody setCenter - dostaje sciezke do xmla który ma załadować do centralnej czesci BorderPana
    public void setCenter(Parent parent){

        borderPaneMainId.setCenter(parent);

        //System.out.println(loader.getController());
    }
    public void setCenter(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainId.setCenter(parent);

        //System.out.println(loader.getController());
    }

}
