import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ValidBirthTest {

    private FormValidation formValidation;

    @Before
    public void setUp() {
        formValidation = new FormValidation() {};
    }

    @Test
    public void testIsValidBirth() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate tomorrow = today.plusDays(1);

        assertTrue(formValidation.isValidBirth(yesterday));
        assertFalse(formValidation.isValidBirth(today));
        assertFalse(formValidation.isValidBirth(tomorrow));
        assertTrue(formValidation.isValidBirth(null));
    }
}