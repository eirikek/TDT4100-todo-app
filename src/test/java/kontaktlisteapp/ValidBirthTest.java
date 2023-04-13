package kontaktlisteapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidBirthTest {

    private Form  Form ;

    @BeforeEach
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