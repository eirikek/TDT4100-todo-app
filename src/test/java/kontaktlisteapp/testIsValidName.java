package kontaktlisteapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testIsValidName {

    private Form Form ;

    @BeforeEach
    public void setUp() {
        Form  = new Form () {};
    }

    @Test
    public void testIsValidName() {
        assertTrue(Form.isValidName("John"));
        assertTrue(Form.isValidName("Jørgen"));
        assertFalse(Form.isValidName(""));
        assertFalse(Form.isValidName("John123"));
    }
}