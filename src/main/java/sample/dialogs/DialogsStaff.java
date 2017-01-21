package sample.dialogs;

import javafx.scene.control.Alert;

/**
 * Created by Marcin on 2017-01-10.
 */
public class DialogsStaff {

    public static void signUpSuccessfulDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Okno informujące o pomyślnej rejestracji");
        alert.setHeaderText(null);
        alert.setContentText("Twoja rejestracja przebiegła pomyślnie." +
                " Teraz pozostało już tylko aktywować konto otrzymanym na podany email kodem weryfikacyjnym");

        alert.showAndWait();
    }

}
