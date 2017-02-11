package sample.DatabaseHibernate;

import javax.persistence.*;
import java.util.*;


/**
 * Created by Marcin on 2017-01-26.
 */
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"nameTraining", "leveOfAdvancement","trainingDays","authorName"})
})
public class TreningSchemeDB {
    @Id
    @GeneratedValue
    @Column(name = "TRENING_ID")
    private long idTrening;
    private String nameTraining;
    private int leveOfAdvancement;
    private int trainingDays;  // konkatenacja liczb 1-monday, 2-tuesday, 3-wedesday
    private String authorName;

    @OneToMany
    @JoinColumn(name = "mondayWorkoutJoin")
    private List<TreiningSchemaDBHelper> mondayWorkout;

    @OneToMany
    @JoinColumn(name = "tuesdayWorkoutJoin")
    private List<TreiningSchemaDBHelper> tuesdayWorkout;

    @OneToMany
    @JoinColumn(name = "wednesdayWorkoutJoin")
    private List<TreiningSchemaDBHelper> wednesdayWorkout;

    @OneToMany
    @JoinColumn(name = "thursdayWorkoutJoin")
    private List<TreiningSchemaDBHelper> thursdayWorkout;

    @OneToMany
    @JoinColumn(name = "fridayWorkoutJoin")
    private List<TreiningSchemaDBHelper> fridayWorkout;

    @OneToMany
    @JoinColumn(name = "saturdayWorkoutJoin")
    private List<TreiningSchemaDBHelper> saturdayWorkout;

    @OneToMany
    @JoinColumn(name = "sundayWorkoutJoin")
    private List<TreiningSchemaDBHelper> sundayWorkout;



    public TreningSchemeDB(){

    }

    public TreningSchemeDB(String nameTraining, int leveOfAdvancement, int trainingDays, String authorName, ArrayList<TreiningSchemaDBHelper> mondayWorkout, ArrayList<TreiningSchemaDBHelper> tuesdayWorkout, ArrayList<TreiningSchemaDBHelper> wednesdayWorkout, ArrayList<TreiningSchemaDBHelper> thursdayWorkout, ArrayList<TreiningSchemaDBHelper> fridayWorkout, ArrayList<TreiningSchemaDBHelper> saturdayWorkout, ArrayList<TreiningSchemaDBHelper> sundayWorkout) {
        this.nameTraining = nameTraining;
        this.leveOfAdvancement = leveOfAdvancement;
        this.trainingDays = trainingDays;
        this.authorName = authorName;
        this.mondayWorkout = mondayWorkout;
        this.tuesdayWorkout = tuesdayWorkout;
        this.wednesdayWorkout = wednesdayWorkout;
        this.thursdayWorkout = thursdayWorkout;
        this.fridayWorkout = fridayWorkout;
        this.saturdayWorkout = saturdayWorkout;
        this.sundayWorkout = sundayWorkout;
    }

    public long getIdTrening() {
        return idTrening;
    }

    public void setIdTrening(long idTrening) {
        this.idTrening = idTrening;
    }

    public String getNameTraining() {
        return nameTraining;
    }

    public void setNameTraining(String nameTraining) {
        this.nameTraining = nameTraining;
    }

    public int getLeveOfAdvancement() {
        return leveOfAdvancement;
    }

    public void setLeveOfAdvancement(int leveOfAdvancement) {
        this.leveOfAdvancement = leveOfAdvancement;
    }

    public int getTrainingDays() {
        return trainingDays;
    }

    public void setTrainingDays(int trainingDays) {
        this.trainingDays = trainingDays;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<TreiningSchemaDBHelper> getMondayWorkout() {
        return mondayWorkout;
    }

    public void setMondayWorkout(List<TreiningSchemaDBHelper> mondayWorkout) {
        this.mondayWorkout = mondayWorkout;
    }

    public List<TreiningSchemaDBHelper> getTuesdayWorkout() {
        return tuesdayWorkout;
    }

    public void setTuesdayWorkout(List<TreiningSchemaDBHelper> tuesdayWorkout) {
        this.tuesdayWorkout = tuesdayWorkout;
    }

    public List<TreiningSchemaDBHelper> getWednesdayWorkout() {
        return wednesdayWorkout;
    }

    public void setWednesdayWorkout(List<TreiningSchemaDBHelper> wednesdayWorkout) {
        this.wednesdayWorkout = wednesdayWorkout;
    }

    public List<TreiningSchemaDBHelper> getThursdayWorkout() {
        return thursdayWorkout;
    }

    public void setThursdayWorkout(List<TreiningSchemaDBHelper> thursdayWorkout) {
        this.thursdayWorkout = thursdayWorkout;
    }

    public List<TreiningSchemaDBHelper> getFridayWorkout() {
        return fridayWorkout;
    }

    public void setFridayWorkout(List<TreiningSchemaDBHelper> fridayWorkout) {
        this.fridayWorkout = fridayWorkout;
    }

    public List<TreiningSchemaDBHelper> getSaturdayWorkout() {
        return saturdayWorkout;
    }

    public void setSaturdayWorkout(List<TreiningSchemaDBHelper> saturdayWorkout) {
        this.saturdayWorkout = saturdayWorkout;
    }

    public List<TreiningSchemaDBHelper> getSundayWorkout() {
        return sundayWorkout;
    }

    public void setSundayWorkout(List<TreiningSchemaDBHelper> sundayWorkout) {
        this.sundayWorkout = sundayWorkout;
    }

    @Override
    public String toString() {
        return "TreningSchemeDB{" +
                "idTrening=" + idTrening +
                ", nameTraining='" + nameTraining + '\'' +
                ", leveOfAdvancement=" + leveOfAdvancement +
                ", trainingDays=" + trainingDays +
                ", authorName='" + authorName + '\'' +
                ", mondayWorkout=" + mondayWorkout +
                ", tuesdayWorkout=" + tuesdayWorkout +
                ", wednesdayWorkout=" + wednesdayWorkout +
                ", thursdayWorkout=" + thursdayWorkout +
                ", fridayWorkout=" + fridayWorkout +
                ", saturdayWorkout=" + saturdayWorkout +
                ", sundayWorkout=" + sundayWorkout +
                '}';
    }
}



