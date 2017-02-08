package sample.DatabaseHibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Marcin on 2017-02-05.
 */
@Entity
public class TreiningSchemaDBHelper {
    @Id
    @GeneratedValue
    private long idTreningHelper;

    private int numberOfExecises;
    private String nameOfExercises;
    private int numberOfSeries;
    private int numberOfRepetitions;

    public TreiningSchemaDBHelper(int numberOfExecises, String nameOfExercises, int numberOfSeries, int numberOfRepetitions) {
        this.numberOfExecises = numberOfExecises;
        this.nameOfExercises = nameOfExercises;
        this.numberOfSeries = numberOfSeries;
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public TreiningSchemaDBHelper() {
    }

    public long getIdTreningHelper() {
        return idTreningHelper;
    }

    public void setIdTreningHelper(long idTreningHelper) {
        this.idTreningHelper = idTreningHelper;
    }

    public String getNameOfExercises() {
        return nameOfExercises;
    }

    public void setNameOfExercises(String nameOfExercises) {
        this.nameOfExercises = nameOfExercises;
    }

    public int getNumberOfSeries() {
        return numberOfSeries;
    }

    public void setNumberOfSeries(int numberOfSeries) {
        this.numberOfSeries = numberOfSeries;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public void setNumberOfRepetitions(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public int getNumberOfExecises() {
        return numberOfExecises;
    }

    public void setNumberOfExecises(int numberOfExecises) {
        this.numberOfExecises = numberOfExecises;
    }

}
