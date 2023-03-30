import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class ValidBirthTest {

    private Form  Form ;

    @Before
    public void setUp() {
        Form  = new Form () {};
    }

    @Test
    public void testIsValidBirth() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate tomorrow = today.plusDays(1);

        assertTrue(Form .isValidBirth(yesterday));
        assertFalse(Form .isValidBirth(today));
        assertFalse(Form .isValidBirth(tomorrow));
        assertTrue(Form .isValidBirth(null));
    }
}