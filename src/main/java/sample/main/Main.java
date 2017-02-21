package sample.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DatabaseHibernate.*;

import java.util.ArrayList;
import java.util.Date;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/startStaff/BorderPaneMain.fxml"));
        primaryStage.setTitle("My Trainer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        DatabaseController databaseController = new DatabaseController();
        this.testValuesDB();



    }

    //pakiet startowy do testów
    public static void testValuesDB(){
//        OperationDB operationDB = new OperationDB();
//        UserDB userAdmin = new UserDB("admin","admin","marcinllukasiak@gmail.com","Marcin","Lukasiak",new Date(),true,true,new OperationDB());


        ArrayList<TreiningSchemaDBHelper> testListPoniedzialek = new ArrayList<TreiningSchemaDBHelper>();
        TreiningSchemaDBHelper testHelperP1 = new TreiningSchemaDBHelper(1,"jedenCwiczenieP",3,8);
        TreiningSchemaDBHelper testHeleprP2 = new TreiningSchemaDBHelper(2,"dwaCwiczenieP",4,10);
        testListPoniedzialek.add(testHelperP1);
        testListPoniedzialek.add(testHeleprP2);

        ArrayList<TreiningSchemaDBHelper> testListWtorek = new ArrayList<TreiningSchemaDBHelper>();
        TreiningSchemaDBHelper testHelperW1 = new TreiningSchemaDBHelper(1,"jedenCwiczenieWt",3,8);
        TreiningSchemaDBHelper testHeleprW2 = new TreiningSchemaDBHelper(2,"dwaCwiczenieWt",4,10);
        testListWtorek.add(testHelperW1);
        testListWtorek.add(testHeleprW2);

        ArrayList<TreiningSchemaDBHelper> testListSroda = new ArrayList<TreiningSchemaDBHelper>();
        TreiningSchemaDBHelper testHelperSr1 = new TreiningSchemaDBHelper(1,"jedenCwiczenieSr",3,8);
        TreiningSchemaDBHelper testHeleprSr2 = new TreiningSchemaDBHelper(2,"dwaCwiczenieSr",4,10);
        testListSroda.add(testHelperSr1);
        testListSroda.add(testHeleprSr2);

        ArrayList<TreiningSchemaDBHelper> testListCzwartek = new ArrayList<TreiningSchemaDBHelper>();
        TreiningSchemaDBHelper testHelperCz1 = new TreiningSchemaDBHelper(1,"jedenCwiczenieCz",3,8);
        TreiningSchemaDBHelper testHeleprCz2 = new TreiningSchemaDBHelper(2,"dwaCwiczenieCz",4,10);
        testListCzwartek.add(testHelperCz1);
        testListCzwartek.add(testHeleprCz2);

        ArrayList<TreiningSchemaDBHelper> testListPiatek = new ArrayList<TreiningSchemaDBHelper>();
        TreiningSchemaDBHelper testHelperPt1 = new TreiningSchemaDBHelper(1,"jedenCwiczeniePt",3,8);
        TreiningSchemaDBHelper testHeleprPt2 = new TreiningSchemaDBHelper(2,"dwaCwiczeniePt",4,10);
        testListPiatek.add(testHelperPt1);
        testListPiatek.add(testHeleprPt2);

        ArrayList<TreiningSchemaDBHelper> testListSobota = new ArrayList<TreiningSchemaDBHelper>();
        TreiningSchemaDBHelper testHelperSb1 = new TreiningSchemaDBHelper(1,"jedenCwiczenieSb",3,8);
        TreiningSchemaDBHelper testHeleprSb2 = new TreiningSchemaDBHelper(2,"dwaCwiczenieSb",4,10);
        testListSobota.add(testHelperSb1);
        testListSobota.add(testHeleprSb2);

        ArrayList<TreiningSchemaDBHelper> testListNiedziela = new ArrayList<TreiningSchemaDBHelper>();
        TreiningSchemaDBHelper testHelperN1 = new TreiningSchemaDBHelper(1,"jedenCwiczenieN",3,8);
        TreiningSchemaDBHelper testHeleprN2 = new TreiningSchemaDBHelper(2,"dwaCwiczenieN",4,10);
        testListNiedziela.add(testHelperN1);
        testListNiedziela.add(testHeleprN2);

        //DatabaseController.insertTreningScheme(new TreningSchemeDB("testTreningNull",1, 135,"marcin", testListPoniedzialek, null, null, null,null,null, null));

        DatabaseController.insertTreningSchemeHelper(testHelperP1);
        DatabaseController.insertTreningSchemeHelper(testHeleprP2);
        DatabaseController.insertTreningSchemeHelper(testHelperW1);
        DatabaseController.insertTreningSchemeHelper(testHeleprW2);
        DatabaseController.insertTreningSchemeHelper(testHelperSr1);
        DatabaseController.insertTreningSchemeHelper(testHeleprSr2);
        DatabaseController.insertTreningSchemeHelper(testHelperCz1);
        DatabaseController.insertTreningSchemeHelper(testHeleprCz2);
        DatabaseController.insertTreningSchemeHelper(testHelperPt1);
        DatabaseController.insertTreningSchemeHelper(testHeleprPt2);
        DatabaseController.insertTreningSchemeHelper(testHelperSb1);
        DatabaseController.insertTreningSchemeHelper(testHeleprSb2);
        DatabaseController.insertTreningSchemeHelper(testHelperN1);
        DatabaseController.insertTreningSchemeHelper(testHeleprN2);


                DatabaseController.insertTreningScheme(new TreningSchemeDB("testTrening", 5, 135, "MarcinLukasiak",
                        testListPoniedzialek,
                        testListWtorek,
                        testListSroda,
                        testListCzwartek,
                        testListPiatek,
                        testListSobota,
                        testListNiedziela));

        DatabaseController.insertUser(new UserDB(true,"admin","admin","marcinllukasiak@gmail.com","Marcin","Lukasiak",new Date(),true,true,new OperationDB(),new MainMeasurementDB()));
        DatabaseController.insertUser(new UserDB(true,"user1","user1","marcinllukasiak@gmail.com","user1Name","user1Surname",new Date(),true,false,new OperationDB(),new MainMeasurementDB()));
        DatabaseController.insertUser(new UserDB(false,"user2","user2","marcinllukasiak@gmail.com","user1Name","user1Surname",new Date(),true,false,new OperationDB(),new MainMeasurementDB()));


        System.out.println("Sprawdzenie");
        TreningSchemeDB treningSchemeDB = DatabaseController.selectTreningScheme(5, 135);
        System.out.println("Ponownie");
        System.out.println(treningSchemeDB);
    }





    public static void main(String[] args) {

       launch(args);
//        DatabaseController databaseController = new DatabaseController();
//        testValuesDB();


        System.out.println("cos się stalo ?");
//
    }



}
