package sample.DatabaseHibernate;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Marcin on 2017-01-08.
 */
@Entity
public class UserDB {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private long idUser;

   // @Column(name = "nick", columnDefinition = "NOT NULL")
    private boolean isMale;
    private String nick;
   // @Column(name = "password", columnDefinition = "NOT NULL")
    private String password;
   // @Column(name = "email", columnDefinition = "NOT NULL")
    private String email;
   // @Column(name = "name", columnDefinition = "NOT NULL")
    private String name;
  // @Column(name = "surname", columnDefinition = "NOT NULL")
    private String surname;
  //  @Column(name = "date Of Birth", columnDefinition = "NOT NULL")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
   // @Column(name = "isActivated", columnDefinition = "NOT NULL")
    private Boolean isActivated;
  //  @Column(name = "isAdministrator", columnDefinition = "NOT NULL")
    private Boolean isAdministrator;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private OperationDB operationDB;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private MainMeasurementDB mainMeasurementDB;



    // konstruktor

    public UserDB(){

    }


    public UserDB(Boolean isMaleA,String nick, String password, String email, String name, String surname, Date dateOfBirth, Boolean isActivated, Boolean isAdministrator, OperationDB operationDB, MainMeasurementDB mainMeasurementDB) {
        this.isMale = isMaleA;
        this.nick = nick;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.isActivated = isActivated;
        this.isAdministrator = isAdministrator;
        this.operationDB = operationDB;
        this.mainMeasurementDB = mainMeasurementDB;
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", isActivated=" + isActivated +
                ", isAdministrator=" + isAdministrator +
                ", isMale=" + isMale +
                '}';
    }
    public String toStringUserInfo() {
        return "nick='" + nick + "'\n" +
                "password='" + password + "'\n" +
                "email='" + email + "'\n" +
                "name='" + name + "'\n" +
                "surname='" + surname + "'\n" +
                "dateOfBirth='" + dateOfBirth + "'\n" +
                "isActivated=" + isActivated + "\n" +
                "isAdministrator=" + isAdministrator + "\n" +
                "isMale="+isMale;
    }


    //Setters and getters


    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public MainMeasurementDB getMainMeasurementDB() {
        return mainMeasurementDB;
    }

    public void setMainMeasurementDB(MainMeasurementDB mainMeasurementDB) {
        this.mainMeasurementDB = mainMeasurementDB;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long id) {
        this.idUser = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public Boolean getAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(Boolean administrator) {
        isAdministrator = administrator;
    }
    public OperationDB getOperationDB() {
        return operationDB;
    }

    public void setOperationDB(OperationDB operationDB) {
        this.operationDB = operationDB;
    }
}
