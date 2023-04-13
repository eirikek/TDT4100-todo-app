package kontaktlisteapp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidAdress {

    private Form  Form ;

    @BeforeEach
    public void setUp() {
        Form  = new Form () {};
    }


    @Test
    public void testIsValidAdress() {
        assertTrue(Form.isValidAdress("Samfundet"));
        assertTrue(Form.isValidAdress(" "));
        assertFalse(Form.isValidAdress("@!$&"));
        assertTrue(Form.isValidAdress("Oslogaten 234"));
        assertTrue(Form.isValidAdress("123456"));

        assertFalse(Form.isValidAdress("#123"));
    }
}