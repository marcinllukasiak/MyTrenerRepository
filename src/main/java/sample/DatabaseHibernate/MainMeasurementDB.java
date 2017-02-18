package sample.DatabaseHibernate;

import javax.persistence.*;

/**
 * Created by Marcin on 2017-02-15.
 */
@Entity
public class MainMeasurementDB {
    @Id
    @GeneratedValue
    @Column(name = "MAINMESSURE_ID")
    private long idMainMeasurement;

    private double BodyWeight;
    private int sizeCm;
    private int caloric;

    @OneToOne(mappedBy = "mainMeasurementDB")
    private UserDB userDB;



    public MainMeasurementDB() {

    }


    public long getIdMainMeasurement() {
        return idMainMeasurement;
    }

    public void setIdMainMeasurement(long idMainMeasurement) {
        this.idMainMeasurement = idMainMeasurement;
    }

    public double getBodyWeight() {
        return BodyWeight;
    }

    public void setBodyWeight(double bodyWeight) {
        BodyWeight = bodyWeight;
    }

    public int getSizeCm() {
        return sizeCm;
    }

    public void setSizeCm(int sizeCm) {
        this.sizeCm = sizeCm;
    }

    public int getCaloric() {
        return caloric;
    }

    public void setCaloric(int caloric) {
        this.caloric = caloric;
    }

    public UserDB getUserDB() {
        return userDB;
    }

    public void setUserDB(UserDB userDB) {
        this.userDB = userDB;
    }
}
