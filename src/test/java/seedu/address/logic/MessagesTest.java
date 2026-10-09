package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

/**
 * Tests for user-visible messages.
 */
public class MessagesTest {

    @Test
    public void formatProfile_includesAllAvailableProfileFields() {
        String expected = "Name: Alice Pauline" + System.lineSeparator()
                + "Phone: 94351253" + System.lineSeparator()
                + "Email: alice@example.com" + System.lineSeparator()
                + "Address: 123, Jurong West Ave 6, #08-111" + System.lineSeparator()
                + "Tags: friends";

        assertEquals(expected, Messages.formatProfile(ALICE));
    }
}
