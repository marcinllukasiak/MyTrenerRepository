package sample.dialogs;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import sample.DatabaseHibernate.DatabaseController;
import sample.DatabaseHibernate.UserDB;

import java.util.Optional;

/**
 * Created by Marcin on 2017-01-10.
 */
public class DialogsStaff {

    public static void signUpSuccessfulDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Okno informujące o pomyślnej rejestracji");
        alert.setHeaderText(null);
        alert.setContentText("Twoja rejestracja przebiegła pomyślnie." +
                " Teraz pozostało już tylko aktywować konto otrzymanym na podany email kodem weryfikacyjnym.");

        alert.showAndWait();
    }

    public static void changePasswordSucecessfulDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Okno informujące o pomyślnej zmianie hasłą");
        alert.setHeaderText(null);
        alert.setContentText("Zmiana hasła przebiegła pomyślnie.");

        alert.showAndWait();
    }

    public static void wrongActiwateCod() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Okno informujące nieudanej próbie aktywacji konta");
        alert.setHeaderText(null);
        alert.setContentText("Wprowadzony kod aktywacyjny jest niepoprawny");

        alert.showAndWait();
    }


    public static void activateDialog(UserDB userDB) {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Aktywacja Konta");
        dialog.setHeaderText("Twoje konto nie jest jeszcze aktywowane.");
        dialog.setContentText("Wprowadź kod aktywacyjny:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("wprowadziles: " + result.get());
            if(userDB.getOperationDB().getOperationKod().equals(result.get())){ //jesli podany kod jest zgodny
                DatabaseController.activateUser(userDB);//aktywacja konta
                DatabaseController.takeNullOperationRow(userDB);//usuwanie kodu do aktywacji - wrzucanie null
            }else{
                //wyswietlenie komunikatu o blednym kodzie
                wrongActiwateCod();
            }
        }
    }

    public static void infoSendRecoveryPassword() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Okno informujące o pomyślnym procesie odzyskiwania hasła");
        alert.setHeaderText(null);
        alert.setContentText("Na powiązany z nickiem email zostało wysłane aktualne hasło");

        alert.showAndWait();
    }
    public static void infoWrongNick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Okno informujące o błednie podanym nicku");
        alert.setHeaderText(null);
        alert.setContentText("Niestety nie udało się odnaleść użytkownika o podanym nicku");

        alert.showAndWait();
    }

    public static void infoWrongData() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Okno informujące o Wystąpieniu juz uzytej daty");
        alert.setHeaderText(null);
        alert.setContentText("Dodałeś już pomiar w tym dniu, nie można dodać więcej niz jednego pomiaru danego dnia, możesz przejść do Histori i edytować ten pomiar");

        alert.showAndWait();
    }


}
