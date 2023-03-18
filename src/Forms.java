import java.time.LocalDate;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class Forms {

    public boolean isValidText(String textField) {
        if (textField.isBlank() || textField == null) {
            return true;
        }
        return textField.matches("[a-zA-Z]+");
    }

    public boolean isValidEmail(String emailTextField) {
        if (emailTextField.isBlank() || emailTextField == null) {
            return true;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(emailTextField).matches();
    }

    public boolean isValidBirth(LocalDate birthDatePicker) {
        LocalDate today = LocalDate.now();
        if (birthDatePicker == null) {
            return true;
        }
        return birthDatePicker.isBefore(today);
    }

    public void invalidAlert(String text) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Ugyldig " + text);
        alert.setHeaderText("Ugyldig " + text);
        alert.setContentText("");
        alert.showAndWait();
    }

    public boolean validInputs(String firstName, String lastName, String email, LocalDate birth, String adress) {
        return isValidText(firstName) && isValidText(lastName) && isValidEmail(email) && isValidBirth(birth) && isValidText(adress);
    }

    public void createAlertBox(String firstName, String lastName, String email, LocalDate birth, String adress) {
        if (!isValidText(firstName)) {
            invalidAlert("fornavn");
        }
        if (!isValidText(lastName)) {
            invalidAlert("etternavn");
        }
        if (!isValidEmail(email)) {
            invalidAlert("email");
        }
        if (!isValidBirth(birth)) {
            invalidAlert("fødselsdato");
        }
        if (!isValidText(adress)) {
            invalidAlert("adresse");
        }
    }

    
}
