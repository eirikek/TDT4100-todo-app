import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewContactController extends Forms{

    @FXML private Button cancelBtn;
    @FXML private Button addBtn;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private DatePicker birthDatePicker;
    @FXML private TextField addressTextField;

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void cancelNewContact(ActionEvent e) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void addNewContact(ActionEvent e) throws IOException {
        String firstname = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        LocalDate birth = birthDatePicker.getValue();
        String adress = addressTextField.getText();

        if (!validInputs(firstname, lastName, email, birth, adress)) {
            createAlertBox(firstname, lastName, email, birth, adress);
        } else {
            //Gjør første bokstav uppercase
            firstname = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            if (!(adress == null || adress.isBlank())) adress = adress.substring(0, 1).toUpperCase() + adress.substring(1);

            Contact contact = new Contact(firstname, lastName, email, birth, adress);
            mainController.addNewContact(contact);

            Stage stage = (Stage) addBtn.getScene().getWindow();
            stage.close();
        }
    }
}

