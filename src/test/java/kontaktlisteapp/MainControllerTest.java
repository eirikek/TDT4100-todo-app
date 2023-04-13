package kontaktlisteapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class MainControllerTest extends NewContactController {

    private MainController mainController;
    private String testFilePath = "src/test/resources/test_contacts.txt";

    @BeforeEach
    public void setUp() {
        mainController = new MainController();
    }

    @AfterEach
    public void tearDown() {
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testWriteAndReadContacts() throws IOException {
        Contact contact1 = new Contact("Ola", "Nordmann", "Ola.Nordmann@Norge.no", LocalDate.of(1990, 1, 1), "Samfundet 123");
        Contact contact2 = new Contact("Kari", "Svenske", "KariSvenske@Sverige.no", LocalDate.of(1992, 2, 2), "456 Maple St.");

        mainController.addNewContact(contact1);
        mainController.addNewContact(contact2);
        mainController.writeContactToFile(testFilePath);

        mainController.contacts.clear();
        mainController.getContactsFromFile(testFilePath);

        assertEquals(2, mainController.contacts.size());
        assertEquals(contact1, mainController.contacts.get(0));
        assertEquals(contact2, mainController.contacts.get(1));
    }
}