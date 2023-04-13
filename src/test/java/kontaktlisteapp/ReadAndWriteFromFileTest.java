package kontaktlisteapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class ReadAndWriteFromFileTest extends NewContactController {

    private MainController mainController;
    private String testFilePath = "contacts.txt";

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

        assertEquals("Ola", mainController.contacts.get(0).getFirstName());
        assertEquals("Nordmann", mainController.contacts.get(0).getLastName());
        assertEquals("Ola.Nordmann@Norge.no", mainController.contacts.get(0).getEmail());
        assertEquals(LocalDate.of(1990, 1, 1), mainController.contacts.get(0).getBirth());
        assertEquals("Samfundet 123", mainController.contacts.get(0).getAddress());

        assertEquals("Kari", mainController.contacts.get(1).getFirstName());
        assertEquals("Svenske", mainController.contacts.get(1).getLastName());
        assertEquals("KariSvenske@Sverige.no", mainController.contacts.get(1).getEmail());
        assertEquals(LocalDate.of(1992, 2, 2), mainController.contacts.get(1).getBirth());
        assertEquals("456 Maple St.", mainController.contacts.get(1).getAddress());

    }
}