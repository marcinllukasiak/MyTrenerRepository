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
//        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/adminStaff/AdminLeftMenuButtons.fxml"));
//        Parent parent = null;
//        try {
//            parent = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        borderPaneMainId.setRight(parent);
    }

    //metody setCenter - dostaje sciezke do xmla który ma załadować do centralnej czesci BorderPana
    public void setCenter(Parent parent){

        borderPaneMainId.setCenter(parent);

        //System.out.println(loader.getController());
    }
    public void setLeft(Parent parent){

        borderPaneMainId.setLeft(parent);

    }

    public void setLeft(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPaneMainId.setLeft(parent);

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

    public Object getController(String fxmlPath){

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Object o = null;
        try {
            o = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

     return o;
    }
    public void clearBorderPane(){
        borderPaneMainId.setCenter(null);
        borderPaneMainId.setLeft(null);
        borderPaneMainId.setRight(null);
        borderPaneMainId.setBottom(null);
        borderPaneMainId.setTop(null);

    }

}
