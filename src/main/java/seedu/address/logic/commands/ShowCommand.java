package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Shows a detailed view of a single debtor
 * 
 * ShowCommand
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the person identified by the index number used in the displayed person list.\n"
            + "Parameters: --id INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " --id 1\n"
            + "Legacy syntax: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_SUCCESS = "Debtor profile:\n%s";
    public static final String MESSAGE_DEBTOR_NOT_FOUND =
            "No debtor is shown at index %1$d. It may have been deleted.";

    private final Index targetIndex;

    /**
     * Creates a new ShowCommand with the specified index
     *
     * @param targetIndex
     */
    public ShowCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**{@inheritDoc}*/
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_DEBTOR_NOT_FOUND, targetIndex.getOneBased()));
        }

        Person targetPerson = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SHOW_SUCCESS, Messages.formatProfile(targetPerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowCommand otherShowCommand)) {
            return false;
        }

        return targetIndex.equals(otherShowCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
