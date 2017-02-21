package sample.DatabaseHibernate;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marcin on 2017-02-15.
 */
@Entity
public class MainMeasurementDB {
    @Id
    @GeneratedValue
    @Column(name = "MAINMESSURE_ID")
    private long idMainMeasurement;

    private int sizeCm;
    private int caloric;

    @OneToOne(mappedBy = "mainMeasurementDB")
    private UserDB userDB;

    @OneToMany
    @JoinColumn(name = "mesurementHelper")
    private List<MesurementDBHelper> mesurements;



    public MainMeasurementDB() {

    }

    public List<MesurementDBHelper> getMesurements() {
        return mesurements;
    }

    public void setMesurements(List<MesurementDBHelper> mesurements) {
        this.mesurements = mesurements;
    }
    public long getIdMainMeasurement() {
        return idMainMeasurement;
    }

    public void setIdMainMeasurement(long idMainMeasurement) {
        this.idMainMeasurement = idMainMeasurement;
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
