package sample.DatabaseHibernate;



import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
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


        session.getTransaction().commit();
        session.close();

    }catch (Exception e ){
        System.err.println("Exception My: "+e);
       // userDB=null;
    }
        return userDB;
    }

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

    //STARA WERSJA
//    public static final String DRIVER = "com.mysql.jdbc.Driver";
//    public static final String DB_URL = "jdbc:mysql://localhost:3306/mytrenerdb";
//    public static final String USER = "root";
//    public static final String PASS = "";
//
//    private Connection conn;
//    private Statement stat;
//
//    public DatabaseController() {
//
//    }
//
//    public boolean insertUser(String nick, String password, String email, String name, String surname, LocalDate dateOfBirth, Boolean isActivated, Boolean isAdministrator) {
//        this.createConnection();
//        try {
//            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
//            int Vauxiliary = -1;
//
//            PreparedStatement prepStmt = conn.prepareStatement(
//                    "insert into users (`nick`,`password`,`email`,`name`,`surname`,`dateOfBirth`,`isActivated`,`isAdministrator`)" +
//                            " values (?,?,?,?,?,?,?,?);");
//            prepStmt.setString(1, nick);
//            prepStmt.setString(2, password);
//            prepStmt.setString(3, email);
//            prepStmt.setString(4, name);
//            prepStmt.setString(5, surname);
//            prepStmt.setString(6, String.valueOf(dateOfBirth));
//           // prepStmt.setString(6, formatter.format(dateOfBirth));
//            if (isActivated) Vauxiliary = 1;
//            else Vauxiliary = 0;
//            prepStmt.setString(7, String.valueOf(Vauxiliary));
//            if (isAdministrator) Vauxiliary = 1;
//            else Vauxiliary = 0;
//            prepStmt.setString(8, String.valueOf(Vauxiliary));
//
//
//            System.out.println(prepStmt);
//            prepStmt.execute();
//        } catch (SQLException e) {
//            System.err.println("Blad przy wstawianiu");
//            this.closeConnection();
//            return false;
//        }
//        this.closeConnection();
//        return true;
//
//    }
//
//    public UserDB selectUser(String nickA) {
//        this.createConnection();
//        UserDB user = null;
//        try {
//            ResultSet result = stat.executeQuery("SELECT * FROM users WHERE nick= \""+nickA+"\"");
//            String nick;
//            String password;
//            String email;
//            String name;
//            String surname;
//            String dateOfBirth;
//            Boolean isActivated;
//            Boolean isAdministrator;
//
//            while(result.next()) {
//                nick = result.getString("nick");
//                password = result.getString("password");
//                email = result.getString("email");
//                name = result.getString("name");
//                surname = result.getString("surname");
//                dateOfBirth = result.getString("dateOfBirth");
//                isActivated = result.getBoolean("isActivated");
//                isAdministrator = result.getBoolean("isAdministrator");
//                user = new UserDB(nick, password, email, name , surname, dateOfBirth, isActivated, isAdministrator);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            this.closeConnection();
//            return null;
//        }
//        this.closeConnection();
//        return user;
//
//    }
//
//    public List<UserDB> selectUsers() {
//        this.createConnection();
//        List<UserDB> usersList = new LinkedList<UserDB>();
//        try {
//            ResultSet result = stat.executeQuery("SELECT * FROM users");
//            String nick;
//            String password;
//            String email;
//            String name;
//            String surname;
//            String dateOfBirth;
//            Boolean isActivated;
//            Boolean isAdministrator;
//
//            while(result.next()) {
//                nick = result.getString("nick");
//                password = result.getString("password");
//                email = result.getString("email");
//                name = result.getString("name");
//                surname = result.getString("surname");
//                dateOfBirth = result.getString("dateOfBirth");
//                isActivated = result.getBoolean("isActivated");
//                isAdministrator = result.getBoolean("isAdministrator");
//                usersList.add(new UserDB(nick, password, email, name , surname, dateOfBirth, isActivated, isAdministrator));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            this.closeConnection();
//            return null;
//        }
//        this.closeConnection();
//        return usersList;
//    }
//
//
//    // open and close connection
//    public void closeConnection(){
//        try {
//            conn.close();
//        } catch (SQLException e) {
//            System.err.println("Blad przy zamykaniu polaczenia");
//            e.printStackTrace();
//        }
//    }
//    public void createConnection(){
//
//        try {
//            Class.forName(DRIVER);
//        } catch (ClassNotFoundException e) {
//            System.err.println("Brak sterownika JDBC");
//            e.printStackTrace();
//        }
//
//        try {
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            stat = conn.createStatement();
//        } catch (SQLException e) {
//            System.err.println("Problem z otwarciem polaczenia");
//            e.printStackTrace();
//        }
//        System.out.println("Polaczenie z baza danych nawiazane");
//
//    }

}
