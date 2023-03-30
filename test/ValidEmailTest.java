import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ValidEmailTest {

    private FormValidation formValidation;

    @Before
    public void setUp() {
        formValidation = new FormValidation() {};
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(formValidation.isValidEmail("test@example.com"));
        assertTrue(formValidation.isValidEmail("test+123@example.com"));
        assertTrue(formValidation.isValidEmail(""));
        assertFalse(formValidation.isValidEmail("test"));
        assertFalse(formValidation.isValidEmail("test@example"));
        assertFalse(formValidation.isValidEmail("test@.com"));
    }
}