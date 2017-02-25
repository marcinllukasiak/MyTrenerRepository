package sample.DatabaseHibernate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Marcin on 2017-02-18.
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"dateOfMesurement"})
})
public class MesurementDBHelper implements Comparable<MesurementDBHelper> {
    @Id
    @GeneratedValue
    @Column(name = "Mesurement_ID")
    private long idMesurement;

    @Temporal(TemporalType.DATE)
    private Date dateOfMesurement;
    private double BodyWeight;
    private int neckSize;
    private int chestSize;
    private int waistSize;
    private int hipsSize;

    private int lArmSize;
    private int rArmSize;

    private int lForearmSize;
    private int rForearmSize;

    private int lThighSize;
    private int rThighSize;

    private int lCalfSize;
    private int rCalfSize;


    @Override
    public int compareTo(MesurementDBHelper mho) {
//        return this.getDateOfMesurement().compareTo(mho.getDateOfMesurement());
        return mho.getDateOfMesurement().compareTo(this.getDateOfMesurement());
    }

    public MesurementDBHelper() {

    }

    public MesurementDBHelper(Date dateOfMesurement,double BodyWeightA, int neckSize, int chestSize, int waistSize, int hipsSize, int lArmSize, int rArmSize, int lForearmSize, int rForearmSize, int lThighSize, int rThighSize, int lCalfSize, int rCalfSize) {
        this.dateOfMesurement = dateOfMesurement;
        this.neckSize = neckSize;
        this.chestSize = chestSize;
        this.waistSize = waistSize;
        this.hipsSize = hipsSize;
        this.lArmSize = lArmSize;
        this.rArmSize = rArmSize;
        this.lForearmSize = lForearmSize;
        this.rForearmSize = rForearmSize;
        this.lThighSize = lThighSize;
        this.rThighSize = rThighSize;
        this.lCalfSize = lCalfSize;
        this.rCalfSize = rCalfSize;
        this.BodyWeight = BodyWeightA;
    }

    public double getBodyWeight() {
        return BodyWeight;
    }

    public void setBodyWeight(double bodyWeight) {
        BodyWeight = bodyWeight;
    }

    public long getIdMesurement() {
        return idMesurement;
    }

    public void setIdMesurement(long idMesurement) {
        this.idMesurement = idMesurement;
    }

    public Date getDateOfMesurement() {
        return dateOfMesurement;
    }

    public void setDateOfMesurement(Date dateOfMesurement) {
        this.dateOfMesurement = dateOfMesurement;
    }

    public int getNeckSize() {
        return neckSize;
    }

    public void setNeckSize(int neckSize) {
        this.neckSize = neckSize;
    }

    public int getChestSize() {
        return chestSize;
    }

    public void setChestSize(int chestSize) {
        this.chestSize = chestSize;
    }

    public int getWaistSize() {
        return waistSize;
    }

    public void setWaistSize(int waistSize) {
        this.waistSize = waistSize;
    }

    public int getHipsSize() {
        return hipsSize;
    }

    public void setHipsSize(int hipsSize) {
        this.hipsSize = hipsSize;
    }

    public int getlArmSize() {
        return lArmSize;
    }

    public void setlArmSize(int lArmSize) {
        this.lArmSize = lArmSize;
    }

    public int getrArmSize() {
        return rArmSize;
    }

    public void setrArmSize(int rArmSize) {
        this.rArmSize = rArmSize;
    }

    public int getlForearmSize() {
        return lForearmSize;
    }

    public void setlForearmSize(int lForearmSize) {
        this.lForearmSize = lForearmSize;
    }

    public int getrForearmSize() {
        return rForearmSize;
    }

    public void setrForearmSize(int rForearmSize) {
        this.rForearmSize = rForearmSize;
    }

    public int getlThighSize() {
        return lThighSize;
    }

    public void setlThighSize(int lThighSize) {
        this.lThighSize = lThighSize;
    }

    public int getrThighSize() {
        return rThighSize;
    }

    public void setrThighSize(int rThighSize) {
        this.rThighSize = rThighSize;
    }

    public int getlCalfSize() {
        return lCalfSize;
    }

    public void setlCalfSize(int lCalfSize) {
        this.lCalfSize = lCalfSize;
    }

    public int getrCalfSize() {
        return rCalfSize;
    }

    public void setrCalfSize(int rCalfSize) {
        this.rCalfSize = rCalfSize;
    }
}
