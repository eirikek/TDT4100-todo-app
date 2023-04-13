package kontaktlisteapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidEmailTest {

    private Form Form ;

    @BeforeEach
    public void setUp() {
        Form  = new Form () {};
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(Form.isValidEmail("test@example.com"));
        assertTrue(Form.isValidEmail("test+123@example.com"));
        assertTrue(Form.isValidEmail(""));
        assertFalse(Form.isValidEmail("test"));
        assertFalse(Form.isValidEmail("test@example"));
        assertFalse(Form.isValidEmail("test@.com"));
    }
}