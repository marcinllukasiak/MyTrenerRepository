package sample.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.UserDB;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/startStaff/BorderPaneMain.fxml"));
        primaryStage.setTitle("My Trainer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        DatabaseController databaseController = new DatabaseController();

    }


    public static void main(String[] args) {

        launch(args);
//        DatabaseController databaseController = new DatabaseController();
//        UserDB userDB = databaseController.selectUser("user1");
//        System.out.println(userDB);


        System.out.println("cos się stalo ?");
//
    }



}
