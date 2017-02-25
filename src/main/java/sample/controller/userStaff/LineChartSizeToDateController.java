package sample.controller.userStaff;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import sample.DatabaseHibernate.MesurementDBHelper;
import sample.DatabaseHibernate.UserDB;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Marcin on 2017-02-24.
 */
public class LineChartSizeToDateController {
    private UserDB onlineUser;
    @FXML
    private LineChart<String, Number> chartSizetoDate;

    //serie:
    private XYChart.Series<String, Number> seriesBodyWeight = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesWaist = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesNeckSize = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesChestSize = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesHipsSize = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesArmSize = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesForearmSize = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesThighSize = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> seriesCalfSize = new XYChart.Series<String, Number>();



    @FXML
    void initialize() {


    }

    public UserDB getOnlineUser() {
        return onlineUser;
    }

    public void setOnlineUser(UserDB onlineUser) {
        this.onlineUser = onlineUser;

    }

    public void initialChart() {

        List<MesurementDBHelper> mesurementsList = this.onlineUser.getMainMeasurementDB().getMesurements();
        Collections.sort(mesurementsList);


        //defining a series
        seriesBodyWeight.setName("Waga Ciała");
        for (MesurementDBHelper mh: mesurementsList ) {
            seriesBodyWeight.getData().add(new XYChart.Data<String, Number>(String.valueOf(mh.getDateOfMesurement()), mh.getBodyWeight()));
        }
        chartSizetoDate.getData().add(seriesBodyWeight);

        seriesWaist.setName("Talia");
        for (MesurementDBHelper mh: mesurementsList ) {
            seriesWaist.getData().add(new XYChart.Data<String, Number>(String.valueOf(mh.getDateOfMesurement()), mh.getWaistSize()));
        }
        chartSizetoDate.getData().add(seriesWaist);

        seriesNeckSize.setName("Kark");
        for (MesurementDBHelper mh: mesurementsList ) {
            seriesNeckSize.getData().add(new XYChart.Data<String, Number>(String.valueOf(mh.getDateOfMesurement()), mh.getNeckSize()));
        }
        chartSizetoDate.getData().add(seriesNeckSize);

        seriesChestSize.setName("Klatka Piersiowa");
        for (MesurementDBHelper mh: mesurementsList ) {
            seriesChestSize.getData().add(new XYChart.Data<String, Number>(String.valueOf(mh.getDateOfMesurement()), mh.getChestSize()));
        }
        chartSizetoDate.getData().add(seriesChestSize);

        seriesHipsSize.setName("Biodra");
        for (MesurementDBHelper mh: mesurementsList ) {
            seriesHipsSize.getData().add(new XYChart.Data<String, Number>(String.valueOf(mh.getDateOfMesurement()), mh.getHipsSize()));
        }
        chartSizetoDate.getData().add(seriesHipsSize);

        seriesArmSize.setName("Ramię");
        for (MesurementDBHelper mh: mesurementsList ) {
            seriesArmSize.getData().add(new XYChart.Data<String, Number>(String.valueOf(mh.getDateOfMesurement()), mh.getlArmSize()));
        }
        chartSizetoDate.getData().add(seriesArmSize);

        seriesForearmSize.setName("Przedramię");
        for (MesurementDBHelper mh: mesurementsList ) {
            seriesForearmSize.getData().add(new XYChart.Data<String, Number>(String.valueOf(mh.getDateOfMesurement()), mh.getlForearmSize()));
        }
        chartSizetoDate.getData().add(seriesForearmSize);

        seriesThighSize.setName("Udo");
        for (MesurementDBHelper mh: mesurementsList ) {
            seriesThighSize.getData().add(new XYChart.Data<String, Number>(String.valueOf(mh.getDateOfMesurement()), mh.getlThighSize()));
        }
        chartSizetoDate.getData().add(seriesThighSize);

        seriesCalfSize.setName("Lydka");
        for (MesurementDBHelper mh: mesurementsList ) {
            seriesCalfSize.getData().add(new XYChart.Data<String, Number>(String.valueOf(mh.getDateOfMesurement()), mh.getlCalfSize()));
        }
        chartSizetoDate.getData().add(seriesCalfSize);

    }

    public void exportChartToFile(Scene scene,String path) {
        chartSizetoDate.setAnimated(false);
        WritableImage image = scene.snapshot(null);


        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            System.out.println("Zapisano");
        } catch (IOException ex) {
            System.out.println("Error");
        }

//        WritableImage image = new WritableImage((int) chartSizetoDate.getWidth(),(int) chartSizetoDate.getHeight());
//
//        image = chartSizetoDate.snapshot(new SnapshotParameters(), null);
//
//        // TODO: probably use a file chooser here
//        File file = new File("chart1.png");
//
//        try {
//            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
//            System.out.println("Niby zapisane");
//        } catch (IOException e) {
//            // TODO: handle exception here
//        }


//        chartSizetoDate.setAnimated(false);
//        System.out.println("Saving . . .");
//        WritableImage image = chartSizetoDate.snapshot(new SnapshotParameters(), null);
//        File file = new File("chartTest.png");
//
//        try {
//            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
//        } catch (IOException e) {
//            System.out.println("Error");
//        }

    }

}
