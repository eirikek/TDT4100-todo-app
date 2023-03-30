import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class testIsValidName {

    private Form Form ;

    @Before
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