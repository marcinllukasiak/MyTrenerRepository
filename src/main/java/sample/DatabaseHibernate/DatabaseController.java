package sample.DatabaseHibernate;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import sample.dialogs.DialogsStaff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marcin on 2017-01-08.
 */
public class DatabaseController { //kazda funkcja pobiera sessionFactory i potrzebne parametry i wykonuje zadane zadanie i zamyka sesje

    private static SessionFactory sessionFactory;

    public DatabaseController() {
        this.sessionFactory =  new Configuration()
                .configure().addAnnotatedClass(UserDB.class) // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
    }


//#########################   USER DB  -  START    ##########################

    public static boolean insertUser(UserDB userDB){
        boolean returne = false;

        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            session.save(userDB);

            session.getTransaction().commit();
            session.close();

            returne = true;

        }catch (Exception e ){
            returne = false;
        }

        return returne;
    }

        public static UserDB selectUser (String nickA){
            UserDB userDB = null;
            Session session = sessionFactory.openSession();
        try{
        session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u where u.nick = :nick");
            query.setParameter("nick",nickA);

            userDB = (UserDB) query.uniqueResult();
            System.out.println(userDB.getMainMeasurementDB().getMesurements());

        session.getTransaction().commit();
        session.close();

    }catch (Exception e ){
        System.err.println("Exception My: "+e);
       // userDB=null;
    }
        return userDB;
    }

        public static void activateUser(UserDB userDB){

        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            UserDB userDB1 = session.get(UserDB.class, userDB.getIdUser());
            userDB1.setActivated(true);

            session.getTransaction().commit();
            session.close();


        }catch (Exception e ){
            System.err.println(e);
        }

    }

    public static void takeNullOperationRow(UserDB userDB){

        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            UserDB userDB1 = session.get(UserDB.class, userDB.getIdUser());
            userDB1.getOperationDB().setOperationKod(null);

            session.getTransaction().commit();
            session.close();


        }catch (Exception e ){
            System.err.println(e);
        }

    }

    public static UserDB editPassword(UserDB userDB, String password){

        Session session = sessionFactory.openSession();
        UserDB userDB1=null;
        try{
            session.beginTransaction();

            userDB1 = session.get(UserDB.class, userDB.getIdUser());
            userDB1.setPassword(password);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println(e);
        }
        return userDB1;

    }

    ///////// Admin Edit Controller ////

    public static ObservableList<String> getAllAdminList(){

        Session session = sessionFactory.openSession();
        ObservableList<String> adminList = null;
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u.nick FROM UserDB u where u.isAdministrator = true");
            adminList = FXCollections.observableArrayList(query.list());

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println(e);
        }
        return adminList;

    }

    public static ObservableList<String> getAllUser(){

        Session session = sessionFactory.openSession();
        ObservableList<String> userList = null;
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u.nick FROM UserDB u where u.isAdministrator = false");
            userList = FXCollections.observableArrayList(query.list());

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println(e);
        }
        return userList;

    }

    public static void makeAdmin (String nickA){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u where u.nick = :nick");
            query.setParameter("nick",nickA);

            userDB = (UserDB) query.uniqueResult();
            userDB.setAdministrator(true);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }

    public static void dropAdmin (String nickA){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u where u.nick = :nick");
            query.setParameter("nick",nickA);

            userDB = (UserDB) query.uniqueResult();
            userDB.setAdministrator(false);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }
    //////////// EDIT USER ///////////

    public static void changePassword (String nickA, String password ){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u where u.nick = :nick");
            query.setParameter("nick",nickA);

            userDB = (UserDB) query.uniqueResult();
            userDB.setPassword(password);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }

    public static void changeName (String nickA, String name){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u where u.nick = :nick");
            query.setParameter("nick",nickA);

            userDB = (UserDB) query.uniqueResult();
            userDB.setName(name);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }
    public static void changeSurname (String nickA, String surname){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u where u.nick = :nick");
            query.setParameter("nick",nickA);

            userDB = (UserDB) query.uniqueResult();
            userDB.setSurname(surname);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }
    public static void changeEmail (String nickA, String email ){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u where u.nick = :nick");
            query.setParameter("nick",nickA);

            userDB = (UserDB) query.uniqueResult();
            userDB.setEmail(email);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }
    public static void changeIsActivate (String nickA, Boolean isActivated){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u where u.nick = :nick");
            query.setParameter("nick",nickA);

            userDB = (UserDB) query.uniqueResult();
            userDB.setActivated(isActivated);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }

    public static void changeIsMale (String nickA, Boolean isMale){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u where u.nick = :nick");
            query.setParameter("nick",nickA);

            userDB = (UserDB) query.uniqueResult();
            userDB.setMale(isMale);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }


    //#########################   USER DB  -  END    ##########################
    //                      VV          VV          VV          VV
    //#########################   TRENING SCHEME DB  -  START    ##########################

    public static boolean insertTreningScheme(TreningSchemeDB treningSchemeDB){
        boolean returne = false;

        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            session.save(treningSchemeDB);

            session.getTransaction().commit();
            session.close();

            returne = true;

        }catch (Exception e ){
            returne = false;
        }

        return returne;
    }

    public static TreningSchemeDB selectTreningScheme (long idTrening){
        TreningSchemeDB treningSchemeDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT t FROM TreningSchemeDB t where t.idTrening = :idTrening");
            query.setParameter("idTrening",idTrening);

            treningSchemeDB = (TreningSchemeDB) query.uniqueResult();
            System.out.println(treningSchemeDB);


            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
        return treningSchemeDB;
    }

    public static TreningSchemeDB selectTreningScheme (String nameTreaining){
        TreningSchemeDB treningSchemeDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT t FROM TreningSchemeDB t where t.nameTraining = :idNameTraining");
            query.setParameter("idNameTraining",nameTreaining);

            treningSchemeDB = (TreningSchemeDB) query.uniqueResult();
            System.out.println(treningSchemeDB);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
        return treningSchemeDB;
    }

    public static TreningSchemeDB selectTreningScheme (int leveOfAdvancement, int trainingDays){
        TreningSchemeDB treningSchemeDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT t FROM TreningSchemeDB t " +
                    "where t.leveOfAdvancement = :leveOfAdvancement AND t.trainingDays = :trainingDays");
            query.setParameter("leveOfAdvancement",leveOfAdvancement);
            query.setParameter("trainingDays",trainingDays);

            treningSchemeDB = (TreningSchemeDB) query.uniqueResult();
            System.out.println(treningSchemeDB);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
        return treningSchemeDB;
    }

    public static boolean insertTreningSchemeHelper(TreiningSchemaDBHelper treningSchemeHelperDB){
        boolean returne = false;

        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            session.save(treningSchemeHelperDB);

            session.getTransaction().commit();
            session.close();

            returne = true;

        }catch (Exception e ){
            returne = false;
        }

        return returne;
    }

    public static void updateTreningScheme (long idTrening , TreiningSchemaDBHelper treiningSchemaDBHelper, int day){
        TreningSchemeDB treningSchemeDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT t FROM TreningSchemeDB t where t.idTrening = :idTrening");
            query.setParameter("idTrening",idTrening);

            treningSchemeDB = (TreningSchemeDB) query.uniqueResult();
            System.out.println(treningSchemeDB);
            session.save(treiningSchemaDBHelper);

            switch (day) {
                case 1:
                    treningSchemeDB.getMondayWorkout().add(treiningSchemaDBHelper);
                    break;
                case 2:
                    treningSchemeDB.getTuesdayWorkout().add(treiningSchemaDBHelper);
                    break;
                case 3:
                    treningSchemeDB.getWednesdayWorkout().add(treiningSchemaDBHelper);
                    break;
                case 4:
                    treningSchemeDB.getThursdayWorkout().add(treiningSchemaDBHelper);
                    break;
                case 5:
                    treningSchemeDB.getFridayWorkout().add(treiningSchemaDBHelper);
                    break;
                case 6:
                    treningSchemeDB.getSaturdayWorkout().add(treiningSchemaDBHelper);
                    break;
                case 7:
                    treningSchemeDB.getSundayWorkout().add(treiningSchemaDBHelper);
                    break;
            }

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }

    }



    public static void updateTreningSchemeHelpre (long idTreningHelperA, int numberOfExecisesA,String nameOfExercisesA,int numberOfSeriesA,int numberOfRepetitionsA){
        TreiningSchemaDBHelper treningSchemeDBhelper = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT t FROM TreiningSchemaDBHelper t " +
                    "where t.idTreningHelper = :idTreningHelper ");
            query.setParameter("idTreningHelper",idTreningHelperA);

            treningSchemeDBhelper = (TreiningSchemaDBHelper) query.uniqueResult();
            System.out.println(treningSchemeDBhelper);

            treningSchemeDBhelper.setNumberOfExecises(numberOfExecisesA);
            treningSchemeDBhelper.setNameOfExercises(nameOfExercisesA);
            treningSchemeDBhelper.setNumberOfSeries(numberOfSeriesA);
            treningSchemeDBhelper.setNumberOfRepetitions(numberOfRepetitionsA);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }

    public static void deleteTreningSchemeHelpre (long idTreningHelperA){
        TreiningSchemaDBHelper treningSchemeDBhelper = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT t FROM TreiningSchemaDBHelper t " +
                    "where t.idTreningHelper = :idTreningHelper ");
            query.setParameter("idTreningHelper",idTreningHelperA);
            treningSchemeDBhelper = (TreiningSchemaDBHelper) query.uniqueResult();
            System.out.println(treningSchemeDBhelper);

            session.delete(treningSchemeDBhelper);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }

    }


    public static ObservableList<String> getAllTreningShemaName(){

        Session session = sessionFactory.openSession();
        ObservableList<String> trainingList = null;
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT ts.idTrening,ts.nameTraining,ts.leveOfAdvancement,ts.trainingDays FROM TreningSchemeDB ts");
            List<Object[]> listResultO = query.list();
            List<String> listResultS = new ArrayList<String>();
            String treningSignature;

            for (Object[] aRow : listResultO) {
                treningSignature = String.valueOf(aRow[0])+" | " + (String) aRow[1]+" L:"+String.valueOf(aRow[2])+" D:"+String.valueOf(aRow[3]);
                listResultS.add(treningSignature);
            }
            trainingList = FXCollections.observableArrayList(listResultS);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println(e);
        }
        return trainingList;

    }


    public static TreningSchemeDB deleteTreningScheme (long idTreningA){
        TreningSchemeDB treningSchemeDB = null;
        deleteTreningSchemeHelpreTS(idTreningA);
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT t FROM TreningSchemeDB t where t.idTrening = :idTrening");
            query.setParameter("idTrening",idTreningA);

            treningSchemeDB = (TreningSchemeDB) query.uniqueResult();
            System.out.println(treningSchemeDB);

            session.delete(treningSchemeDB);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
        return treningSchemeDB;
    }

    private static void deleteTreningSchemeHelpreTS (long idTreningShemaA){
        List<TreiningSchemaDBHelper> treiningSchemaDBHelperList = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT t.fridayWorkout FROM TreningSchemeDB t " +
                    "where t.idTrening = :idTreningHelper ");
            query.setParameter("idTreningHelper",idTreningShemaA);
            treiningSchemaDBHelperList =query.list();

            for (TreiningSchemaDBHelper tSH:treiningSchemaDBHelperList) {
                session.delete(tSH);
            }

            query = session.createQuery("SELECT t.mondayWorkout FROM TreningSchemeDB t " +
                    "where t.idTrening = :idTreningHelper ");
            query.setParameter("idTreningHelper",idTreningShemaA);
            treiningSchemaDBHelperList =query.list();

            for (TreiningSchemaDBHelper tSH:treiningSchemaDBHelperList) {
                session.delete(tSH);
            }

            query = session.createQuery("SELECT t.saturdayWorkout FROM TreningSchemeDB t " +
                    "where t.idTrening = :idTreningHelper ");
            query.setParameter("idTreningHelper",idTreningShemaA);
            treiningSchemaDBHelperList =query.list();

            for (TreiningSchemaDBHelper tSH:treiningSchemaDBHelperList) {
                session.delete(tSH);
            }
            query = session.createQuery("SELECT t.sundayWorkout FROM TreningSchemeDB t " +
                    "where t.idTrening = :idTreningHelper ");
            query.setParameter("idTreningHelper",idTreningShemaA);
            treiningSchemaDBHelperList =query.list();

            for (TreiningSchemaDBHelper tSH:treiningSchemaDBHelperList) {
                session.delete(tSH);
            }

            query = session.createQuery("SELECT t.thursdayWorkout FROM TreningSchemeDB t " +
                    "where t.idTrening = :idTreningHelper ");
            query.setParameter("idTreningHelper",idTreningShemaA);
            treiningSchemaDBHelperList =query.list();

            for (TreiningSchemaDBHelper tSH:treiningSchemaDBHelperList) {
                session.delete(tSH);
            }
            query = session.createQuery("SELECT t.tuesdayWorkout FROM TreningSchemeDB t " +
                    "where t.idTrening = :idTreningHelper ");
            query.setParameter("idTreningHelper",idTreningShemaA);
            treiningSchemaDBHelperList =query.list();

            for (TreiningSchemaDBHelper tSH:treiningSchemaDBHelperList) {
                session.delete(tSH);
            }

            query = session.createQuery("SELECT t.wednesdayWorkout FROM TreningSchemeDB t " +
                    "where t.idTrening = :idTreningHelper ");
            query.setParameter("idTreningHelper",idTreningShemaA);
            treiningSchemaDBHelperList =query.list();

            for (TreiningSchemaDBHelper tSH:treiningSchemaDBHelperList) {
                session.delete(tSH);
            }


            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }

    }

    //#########################   TRENING SCHEME DB  -  END    ##########################





    //#########################   MAIN MESUREMENT DB  -  START    ##########################
    public static void updateUserDBMainMesure (long idUserDBA,UserDB updateUserDB){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u " +
                    "where u.idUser = :idUserDB ");
            query.setParameter("idUserDB",idUserDBA);

            userDB = (UserDB) query.uniqueResult();
            System.out.println(userDB);

            userDB.setMale(updateUserDB.isMale());

            userDB.getMainMeasurementDB().setSizeCm(updateUserDB.getMainMeasurementDB().getSizeCm());
            userDB.getMainMeasurementDB().setCaloric(updateUserDB.getMainMeasurementDB().getCaloric());


            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }



    public static boolean insertMesurementDBHelper(MesurementDBHelper mesurementDBHelper){
        boolean returne = false;

        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            session.save(mesurementDBHelper);

            session.getTransaction().commit();
            session.close();

            returne = true;

        }catch (Exception e ){
            returne = false;
            DialogsStaff.infoWrongData();
        }

        return returne;
    }

    public static void updateUserDBMesureHelper (long idUserDBA,MesurementDBHelper mesurementDBHelper){
        UserDB userDB = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT u FROM UserDB u " +
                    "where u.idUser = :idUserDB ");
            query.setParameter("idUserDB",idUserDBA);

            userDB = (UserDB) query.uniqueResult();
            System.out.println(userDB);

            userDB.getMainMeasurementDB().getMesurements().add(mesurementDBHelper);


            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }
    }




    //#########################   MAIN MESUREMENT DB  -  END      ##########################

    //#########################   MESUREMENT DB HLEPER  -  START     ##########################

    public static ObservableList<String> getAllMesurementUser(long idMainMesurementA){

        Session session = sessionFactory.openSession();
        ObservableList<String> mesurementList = null;
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT mm.mesurements FROM MainMeasurementDB mm WHERE mm.idMainMeasurement = :mesurementIdA");
            query.setParameter("mesurementIdA",idMainMesurementA);


            List<MesurementDBHelper> listResultO = query.list();
            //sortowanie
            Collections.sort(listResultO);

            List<String> listResultS = new ArrayList<String>();
            String mesurementDate;

            for (MesurementDBHelper aRow : listResultO) {
                mesurementDate = aRow.getDateOfMesurement().toString();
                listResultS.add(mesurementDate);
            }
            mesurementList = FXCollections.observableArrayList(listResultS);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println(e);
        }
        return mesurementList;

    }

    public static void deleteMesurementDBHelper (long idMesurementHelperA){
        MesurementDBHelper mesurementDBHelper = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT mh FROM MesurementDBHelper mh " +
                    "where mh.idMesurement = :idMesurementHelper ");
            query.setParameter("idMesurementHelper",idMesurementHelperA);
            mesurementDBHelper = (MesurementDBHelper) query.uniqueResult();
            System.out.println(mesurementDBHelper);

            session.delete(mesurementDBHelper);

            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }

    }

    public static void updateMesurementDBHelper (long idMesurementDBHelperA , MesurementDBHelper mesurementDBHelperA){
        MesurementDBHelper mesurementDBHelper = null;
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();

            Query query = session.createQuery("SELECT mh FROM MesurementDBHelper mh where mh.idMesurement = :idMesurementHelper");
            query.setParameter("idMesurementHelper",idMesurementDBHelperA);

            mesurementDBHelper = (MesurementDBHelper) query.uniqueResult();
            System.out.println(mesurementDBHelper);

            //zamiana
            mesurementDBHelper.setBodyWeight(mesurementDBHelperA.getBodyWeight());
            mesurementDBHelper.setNeckSize(mesurementDBHelperA.getNeckSize());
            mesurementDBHelper.setChestSize(mesurementDBHelperA.getChestSize());
            mesurementDBHelper.setWaistSize(mesurementDBHelperA.getWaistSize());
            mesurementDBHelper.setHipsSize(mesurementDBHelperA.getHipsSize());
            mesurementDBHelper.setlArmSize(mesurementDBHelperA.getlArmSize());
            mesurementDBHelper.setrArmSize(mesurementDBHelperA.getrArmSize());
            mesurementDBHelper.setlForearmSize(mesurementDBHelperA.getlForearmSize());
            mesurementDBHelper.setrForearmSize(mesurementDBHelperA.getrForearmSize());
            mesurementDBHelper.setlThighSize(mesurementDBHelperA.getlThighSize());
            mesurementDBHelper.setrThighSize(mesurementDBHelperA.getrThighSize());
            mesurementDBHelper.setlCalfSize(mesurementDBHelperA.getlCalfSize());
            mesurementDBHelper.setrCalfSize(mesurementDBHelperA.getrCalfSize());



            session.getTransaction().commit();
            session.close();

        }catch (Exception e ){
            System.err.println("Exception My: "+e);
        }

    }


//#########################   MESUREMENT DB HLEPER  -  END     ##########################


//SHEMAT
//    private static typ nazwa(){
//        Session session = sessionFactory.openSession();
//        try{
//        session.beginTransaction();
//
//
//        session.getTransaction().commit();
//        session.close();
//
//
//    }catch (Exception e ){
//        System.err.println("Exception");
//    }

//    }




}
