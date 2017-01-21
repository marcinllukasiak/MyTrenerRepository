package sample.DatabaseHibernate;

import javax.persistence.*;

/**
 * Created by Marcin on 2017-01-21.
 */
@Entity
public class OperationDB {
    @Id
    @GeneratedValue
    @Column(name = "OPERATION_ID")
    private long idOperation;
    private String operationKod;
    @OneToOne(mappedBy = "operationDB")
    private UserDB userDB;

    public OperationDB(String operationKod) {
        this.operationKod = operationKod;
    }
    public OperationDB() {
    }


    public long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(long idOperation) {
        this.idOperation = idOperation;
    }

    public String getOperationKod() {
        return operationKod;
    }

    public void setOperationKod(String operationKod) {
        this.operationKod = operationKod;
    }

    public UserDB getUserDB() {
        return userDB;
    }

    public void setUserDB(UserDB userDB) {
        this.userDB = userDB;
    }
}
