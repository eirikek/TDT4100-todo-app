import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ValidEmailTest {

    private Form Form ;

    @Before
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