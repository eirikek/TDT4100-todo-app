import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javafx.collections.ObservableList;

public class DeleteContactTest {

    private MainController mainController;

    @Before
    public void setUp() {
        mainController = new MainController();
    }

    @Test
    public void testDeleteContact() {
        Contact contact1 = new Contact("John", "Doe", "johndoe@example.com", null, null);
        Contact contact2 = new Contact("Jane", "Doe", "janedoe@example.com", null, null);
        mainController.addNewContact(contact1);
        mainController.addNewContact(contact2);
        ObservableList<Contact> contacts = mainController.getTableView().getItems();
        assertEquals(2, contacts.size());

        // Select the first contact and delete it
        mainController.getTableView().getSelectionModel().select(contact1);
        mainController.deleteContact();
        assertEquals(1, contacts.size());
        assertEquals(contact2, contacts.get(0));
    }
}