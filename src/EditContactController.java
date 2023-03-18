import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditContactController extends Forms{
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

    public void cancelChanges(ActionEvent e) {
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
            selectedContact.setFirstName(firstNameTextField.getText());
            selectedContact.setLastName(lastNameTextField.getText());
            selectedContact.setEmail(emailTextField.getText());
            selectedContact.setBirth(birthDatePicker.getValue());
            selectedContact.setAddress(addressTextField.getText());
            if (birth != null) {
                selectedContact.setAge();
            }
            tableView.refresh();
            mainController.showDetails(selectedContact);

            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        }
    }
}
