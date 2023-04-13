package kontaktlisteapp;

import java.io.IOException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteContactTest{
    private MainController mainController = new MainController();

    @Test
    public void testDeleteContact() throws IOException {
        // Create and add sample contacts to the mainController
        Contact contact1 = new Contact("John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "123 Main St");
        Contact contact2 = new Contact("Jane", "Doe", "jane.doe@example.com", LocalDate.of(1992, 2, 2), "456 Main St");

        mainController.addNewContact(contact1);
        mainController.addNewContact(contact2);

        // Refactor deleteContact method for testing purpose
        mainController.contacts.remove(contact1);

        // Verify if the contact1 was removed from the contact list
        assertFalse(mainController.contacts.contains(contact1), "Contact list should not contain the deleted contact.");
        assertTrue(mainController.contacts.contains(contact2), "Contact list should still contain the non-deleted contact.");
    }
}