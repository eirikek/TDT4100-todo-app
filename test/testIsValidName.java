import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class testIsValidName {

    private FormValidation formValidation;

    @Before
    public void setUp() {
        formValidation = new FormValidation() {};
    }

    @Test
    public void testIsValidName() {
        assertTrue(formValidation.isValidName("John"));
        assertTrue(formValidation.isValidName("Jørgen"));
        assertFalse(formValidation.isValidName(""));
        assertFalse(formValidation.isValidName("John123"));
    }
}