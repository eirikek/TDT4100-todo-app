package kontaktlisteapp;

import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditContactController extends Form implements Controller{
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private DatePicker birthDatePicker;
    @FXML private TextField addressTextField;

    private MainController mainController;
    private Contact selectedContact;

    public TextField getFirstNameTextField() {
        return firstNameTextField;
    }

    public TextField getLastNameTextField() {
        return lastNameTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public DatePicker getBirthDatePicker() {
        return birthDatePicker;
    }

    public TextField getAddressTextField() {
        return addressTextField;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setSelectedContact(Contact selectedContact) {
        this.selectedContact = selectedContact;
    }

    public void cancel(ActionEvent e) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void saveChanges(ActionEvent e) throws IOException{
        TableView<Contact> tableView = mainController.getTableView();
        String firstname = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        LocalDate birth = birthDatePicker.getValue();
        String adress = addressTextField.getText();

        if (!validInputs(firstname, lastName, email, birth, adress)) {
            createAlertBox(firstname, lastName, email, birth, adress);
        } else {
            //Gjøre første bokstav uppercase
            firstname = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            if (!(adress == null || adress.isBlank())) adress = adress.substring(0, 1).toUpperCase() + adress.substring(1);

            selectedContact.setFirstName(firstname);
            selectedContact.setLastName(lastName);
            selectedContact.setEmail(email);
            selectedContact.setBirth(birth);
            selectedContact.setAddress(adress);
            if (birth != null) {
                selectedContact.setAge();
            }
            tableView.refresh();
            mainController.showDetails(selectedContact);
            mainController.writeContactToFile("src/main/resources/contacts.txt");

            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        }
    }
}
