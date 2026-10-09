package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests for {@code ShowCommand}.
 */
public class ShowCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_eachValidIndexUnfilteredList_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            Index targetIndex = Index.fromZeroBased(i);
            Person targetPerson = model.getFilteredPersonList().get(i);
            ShowCommand showCommand = new ShowCommand(targetIndex);
            String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_SUCCESS,
                    Messages.formatProfile(targetPerson));

            assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
        }
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(ShowCommand.MESSAGE_SHOW_SUCCESS,
                Messages.formatProfile(targetPerson));
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(showCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_outOfRangeIndex_throwsCommandException() {
        Index outOfRangeIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ShowCommand showCommand = new ShowCommand(outOfRangeIndex);

        assertCommandFailure(showCommand, model, String.format(ShowCommand.MESSAGE_DEBTOR_NOT_FOUND,
                outOfRangeIndex.getOneBased()));
    }

    @Test
    public void execute_deletedLastIndex_throwsCommandException() {
        int lastIndex = model.getFilteredPersonList().size();
        Person lastPerson = model.getFilteredPersonList().get(lastIndex - 1);
        model.deletePerson(lastPerson);
        ShowCommand showCommand = new ShowCommand(Index.fromOneBased(lastIndex));

        assertCommandFailure(showCommand, model, String.format(ShowCommand.MESSAGE_DEBTOR_NOT_FOUND, lastIndex));
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        ShowCommand showCommand = new ShowCommand(INDEX_SECOND_PERSON);

        assertCommandFailure(showCommand, model, String.format(ShowCommand.MESSAGE_DEBTOR_NOT_FOUND,
                INDEX_SECOND_PERSON.getOneBased()));
    }

    @Test
    public void toStringMethod() {
        ShowCommand showCommand = new ShowCommand(INDEX_FIRST_PERSON);
        String expected = ShowCommand.class.getCanonicalName()
                + "{targetIndex=" + INDEX_FIRST_PERSON + "}";

        assertEquals(expected, showCommand.toString());
    }
}
