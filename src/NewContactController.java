import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewContactController {

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
        Contact contact = new Contact(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), birthDatePicker.getValue(), addressTextField.getText());
        mainController.addNewContact(contact);

        Stage stage = (Stage) addBtn.getScene().getWindow();
        stage.close();
    }
}

