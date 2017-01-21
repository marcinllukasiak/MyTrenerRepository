package sample.controller.startStaff;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.OperationDB;
import sample.DatabaseHibernate.UserDB;
import sample.dialogs.DialogsStaff;
import sample.main.DateUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Marcin on 2017-01-10.
 */
public class SignUpController {
    //DatabaseController databaseController = new DatabaseController(); // polaczenie z baza danych

    @FXML
    private TextField tfNickId;
    @FXML
    private TextField tfPasswordId;
    @FXML
    private TextField tfEmailId;
    @FXML
    private TextField tfNameId;
    @FXML
    private TextField tfSurnameId;
    @FXML
    private DatePicker dpDateOfBirthID;
    @FXML
    private Label lNickValidationId;
    @FXML
    private Label lPasswordValidationId;
    @FXML
    private Label lEmailValidationId;
    @FXML
    private Label lNameValidationId;
    @FXML
    private Label lSurnameValidationId;
    @FXML
    private Label lDataValidationId;
    @FXML
    private Label lUniqueNickValidationId;
    @FXML
    private Label lIncorrectEmailId;

    @FXML
    void signUpAction() {
        Boolean successfulValidation =false;
//        System.out.println(tfNickId.getText());
//        System.out.println(tfPasswordId.getText());
//        System.out.println(tfEmailId.getText());
//        System.out.println(tfNameId.getText());
//        System.out.println(tfSurnameId.getText());
//        System.out.println(dpDateOfBirthID.getValue());

        //Walidacja
        //1.Sprawdzanie pustych fieldtextów

            //Wylaczenie widocznosci wszystkich ostrzezen
            this.cleaningLabelValidation();


            if(tfNickId.getText().equals("")){
                System.out.println("Nie podałęś Nicku");
                lNickValidationId.setVisible(true);
            }else if(tfPasswordId.getText().equals("")){
                System.out.println("Nie podałęś Password");
                lPasswordValidationId.setVisible(true);
            }else if(tfEmailId.getText().equals("")){
                System.out.println("Nie podałęś Emaila");
                lEmailValidationId.setVisible(true);
            }else if(tfNameId.getText().equals("")){
                System.out.println("Nie podałęś Name");
                lNameValidationId.setVisible(true);
            }else if(tfSurnameId.getText().equals("")){
                System.out.println("Nie podałęś Surname");
                lSurnameValidationId.setVisible(true);
            }else{
                System.out.println(dpDateOfBirthID.getValue());
                try{
                    if(!dpDateOfBirthID.getValue().equals(null)) { // jak nie jest nullem czyli data jest poprawna to tylko sprawdzi meila i koniec walidacji

                            System.out.println("Podales email - sprawdzmy go");
                            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                            Matcher m = p.matcher(tfEmailId.getText());
                            boolean matchFound = m.matches();

                            if (matchFound) {
                                System.out.println("email OK");
                                System.out.println("Validacja przebiegla pomyslnie");
                                successfulValidation = true;
                            } else {
                                System.out.println("zły adres email");
                                lIncorrectEmailId.setVisible(true);
                            }
                        
                    }
                } catch (Exception e){
                    System.out.println("Nie podałęś Daty Catch");
                    lDataValidationId.setVisible(true);
                }

            }


        //Dodawanie do bazy danych jesli walidacja przebiegla poprawnie
        if(successfulValidation.equals(true)) {
            //nawiazanie polaczenia z baza danych
           // databaseController = new DatabaseController();
            OperationDB operationDB = new OperationDB("kodzikWeryfikacyjny1");
            boolean unigueNick = DatabaseController.insertUser(new UserDB(tfNickId.getText(), tfPasswordId.getText(), tfEmailId.getText(), tfNameId.getText(), tfSurnameId.getText(), DateUtils.asDate(dpDateOfBirthID.getValue()), false, false,operationDB));
            System.out.println(unigueNick);
                if(unigueNick){
                    DialogsStaff.signUpSuccessfulDialog(); //wyswietlenie komunikatu
                    this.cleaningTextField(); // czyszczenie formularza
                                                //wysylanie emaila

                }else{
                    lUniqueNickValidationId.setVisible(true);
                }
        }

    }


    private void cleaningLabelValidation(){
        lNickValidationId.setVisible(false);
        lPasswordValidationId.setVisible(false);
        lEmailValidationId.setVisible(false);
        lNameValidationId.setVisible(false);
        lSurnameValidationId.setVisible(false);
        lDataValidationId.setVisible(false);
        lUniqueNickValidationId.setVisible(false);
        lIncorrectEmailId.setVisible(false);
    }

    private void cleaningTextField(){
        tfNickId.clear();
        tfPasswordId.clear();
        tfEmailId.clear();
        tfNameId.clear();
        tfSurnameId.clear();
        dpDateOfBirthID.getEditor().clear();;
    }

}
