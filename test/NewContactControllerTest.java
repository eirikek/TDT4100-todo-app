import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NewContactControllerTest {

    private NewContactController controller;

    @BeforeEach
    void setUp() {
        controller = new NewContactController();
    }

    @Test
    void testGetFirstNameTextField() {
        TextField textField = mock(TextField.class);
        controller.firstNameTextField = textField;
        assertEquals(textField, controller.getFirstNameTextField());
    }

    @Test
    void testGetLastNameTextField() {
        TextField textField = mock(TextField.class);
        controller.lastNameTextField = textField;
        assertEquals(textField, controller.getLastNameTextField());
    }

}