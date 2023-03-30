import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
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

public class MainController implements Initializable{

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

    public TableView<Contact> getTableView() {
        return tableView;
    }

    //Skrive kontaktlisten til fil
    public void writeContactToFile(String filename) throws IOException {
        PrintWriter writer = new PrintWriter(filename);
        for (Contact contact : contacts) {
        writer.println(contact.getFirstName() + "," + contact.getLastName() + "," + contact.getEmail() + "," + contact.getBirth() + "," + contact.getAddress());
        }
        writer.flush();
        writer.close();
    }

    //Lese kontakter fra fil
    public void getContactsFromFile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineInfo = line.split(",");
            LocalDate birth;
            String adress = "";

            String firstName = lineInfo[0];
            String lastName = lineInfo[1];
            String email = lineInfo[2];
            String birthValue = lineInfo[3];
            if (birthValue.equals("null")) {
                birth = null;
            } else {
                birth = LocalDate.parse(birthValue);
            }
            if (lineInfo.length == 5) {
                adress = lineInfo[4];
            }

            Contact contact = new Contact(firstName, lastName, email, birth, adress);
            try {
                addNewContact(contact);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    // Åpne skjema for legge til ny kontakt
    public void newBtnClicked(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/NewContact.fxml"));
        Parent root = fxmlLoader.load();
        NewContactController newContactController = fxmlLoader.getController();
        newContactController.setMainController(this);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Legg til kontakt");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        String css = this.getClass().getResource("resources/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.showAndWait();
    }

     // Åpne skjema for å endre kontakt
     public void editBtnClicked(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/EditContact.fxml"));
        Parent root = fxmlLoader.load();
        EditContactController editContactController = fxmlLoader.getController();
        editContactController.setMainController(this);

        Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
        editContactController.setSelectedContact(selectedContact);

        editContactController.getFirstNameTextField().setText(selectedContact.getFirstName());
        editContactController.getLastNameTextField().setText(selectedContact.getLastName());
        editContactController.getEmailTextField().setText(selectedContact.getEmail());
        editContactController.getBirthDatePicker().setValue(selectedContact.getBirth());
        editContactController.getAddressTextField().setText(selectedContact.getAddress());

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Endre kontakt");
        stage.setResizable(false);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        String css = this.getClass().getResource("resources/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.showAndWait();
    }

    //Slette kontakt
    public void deleteContact() throws IOException {
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
            writeContactToFile("src/resources/contacts.txt");
            if (contacts.size() != 0) {
                showDetails(tableView.getSelectionModel().getSelectedItem());
                numOfContactsLabel.setText("Antall kontakter: " + contacts.size());
            }
        }
    }

     //Legge til kontakt
     public void addNewContact(Contact contact) throws IOException {
        contacts.add(contact);
        tableView.getSelectionModel().select(contact);
        writeContactToFile("src/resources/contacts.txt");
    }

    //Vise personopplysninger
    public void showDetails(Contact contact) {
        firstNameInfo.setText(contact.getFirstName());
        lastNameInfo.setText(contact.getLastName());
        emailInfo.setText(contact.getEmail());
        adressInfo.setText(contact.getAddress());
        if (contact.getBirth() != null) {
            birthInfo.setText(contact.getBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            ageInfo.setText(String.valueOf(contact.getAge()));
        } else {
            birthInfo.setText("");
            ageInfo.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getLastName()));

        //Henter kontakter fra fil slik at de allerede er i listen når man starter applikasjonen
        try {
            getContactsFromFile("src/resources/contacts.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        tableView.setItems(contacts);
        tableView.setPlaceholder(new Label("Ingen kontakter"));

        // Vise persondetaljer når valgt kontakt i tableview
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            //Skal ikke vise persondetaljer hvis kontaktlisten er tom
            if (contacts.size() == 0) {
                firstNameInfo.setText("");
                lastNameInfo.setText("");
                emailInfo.setText("");
                birthInfo.setText("");
                adressInfo.setText("");
                ageInfo.setText("");

                personDetailsLabel.setText("");
                firstNameLabel.setText("");
                lastNameLabel.setText("");
                emaiLabel.setText("");
                birthLabel.setText("");
                ageLabel.setText("");
                adressLabel.setText("");

                numOfContactsLabel.setText("Antall kontakter: 0");
            } else {
                showDetails(newSelection);
                numOfContactsLabel.setText("Antall kontakter: " + contacts.size());
                personDetailsLabel.setText("Personopplysninger");
                firstNameLabel.setText("Fornavn:");
                lastNameLabel.setText("Etternavn:");
                emaiLabel.setText("E-post:");
                birthLabel.setText("Fødselsdato:");
                ageLabel.setText("Alder:");
                adressLabel.setText("Adresse:");
            }
        });
    }
}
