import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML private Label numOfContactsLabel;
    @FXML private TableView<Contact> tableView;
    @FXML private TableColumn<Contact, String> firstNameColumn;
    @FXML private TableColumn<Contact, String> lastNameColumn;
    @FXML private Label firstNameInfo;
    @FXML private Label lastNameInfo;
    @FXML private Label emailInfo;
    @FXML private Label birthInfo;
    @FXML private Label ageInfo;
    @FXML private Label adressInfo;
    @FXML private Label personDetailsLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label emaiLabel; 
    @FXML private Label birthLabel; 
    @FXML private Label ageLabel;
    @FXML private Label adressLabel;

    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    // Åpne skjema for legge til ny kontakt
    public void newBtnClicked(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/NewContact.fxml"));
        Parent root = fxmlLoader.load();
        NewContactController newContactController = fxmlLoader.getController();
        newContactController.setMainController(this);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add new contact");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        String css = this.getClass().getResource("resources/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.showAndWait();
    }

    //Legge til kontakt
    public void addNewContact(Contact contact) {
        contacts.add(contact);
        tableView.getSelectionModel().select(contact);
    }

    //Slette kontakt
    public void deleteContact() {
        Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
        ButtonType deleteBtn = new ButtonType("Slett", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelDeleteBtn = new ButtonType("Avbryt", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på at du vil slette " + selectedContact.getFirstName() + " " + selectedContact.getLastName() + " fra kontaktlisten?", cancelDeleteBtn, deleteBtn);
        alert.setTitle("Slett kontakt");
        alert.setHeaderText("Slette " + selectedContact.getFirstName() + " " + selectedContact.getLastName());
        alert.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == deleteBtn) {
            contacts.remove(selectedContact);
            showDetails(tableView.getSelectionModel().getSelectedItem());
        }
        numOfContactsLabel.setText("Antall kontakter: " + contacts.size());
    }

    public void showDetails(Contact contact) {
        firstNameInfo.setText(contact.getFirstName());
        lastNameInfo.setText(contact.getLastName());
        emailInfo.setText(contact.getEmail());
        birthInfo.setText(contact.getBirth());
        adressInfo.setText(contact.getAddress());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getLastName()));
        tableView.setItems(contacts);

        // Vise persondetaljer når valgt kontakt i tableview
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (contacts.size() == 0) {
                showDetails(new Contact("", "", "", "", ""));
                personDetailsLabel.setText("");
                firstNameLabel.setText("");
                lastNameLabel.setText("");
                emaiLabel.setText("");
                birthLabel.setText("");
                ageLabel.setText("");
                adressLabel.setText("");
                numOfContactsLabel.setText("Antall kontakter: 0");
            }
            showDetails(newSelection);
            numOfContactsLabel.setText("Antall kontakter: " + contacts.size());
            personDetailsLabel.setText("Personopplysninger");
            firstNameLabel.setText("Fornavn:");
            lastNameLabel.setText("Etternavn:");
            emaiLabel.setText("E-post:");
            birthLabel.setText("Fødselsdato:");
            ageLabel.setText("Alder:");
            adressLabel.setText("Adresse:");
        });
    }

}
