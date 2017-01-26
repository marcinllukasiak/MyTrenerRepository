package sample.DatabaseHibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Marcin on 2017-01-26.
 */
@Entity
public class TreningSchemeDB {
    @Id
    @GeneratedValue
    @Column(name = "TRENING_ID")
    private long idUser;
    private String authorName;
    private LinkedList<ArrayList<S>>


}
