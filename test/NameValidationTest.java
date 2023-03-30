import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class NameValidationTest extends FormValidation{


    private String firstName = ""; //Setter navnet til å være en tom streng

    Contact Contact = new Contact(this.firstName,"Nordmann", null, null, null);

    private boolean ExpectedValue = isValidName(Contact.getFirstName()); // Lager en boolean som returnerer en True eller False verdi, basert på hvorvidt navnet er gyldig


    private String firstName2 = "Ola";
    Contact Contact2 = new Contact(this.firstName2,"Nordmann", null, null, null);
    private boolean ExpectedValue2 = isValidName(Contact2.getFirstName());


    @Test
    public void testIsValidName() throws Exception {

        assertEquals(ExpectedValue, false); //Sjekker om navnet er ugyldig
        assertEquals(ExpectedValue2, true); //Sjekker om navnet er gyldig

    }
}
