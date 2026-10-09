package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input and creates a new {@code ShowCommand}.
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    private static final String ID_OPTION = "--id";

    /**
     * Parses a displayed-list index in either {@code --id INDEX} or legacy {@code INDEX} form.
     *
     * @param args arguments supplied after the {@code show} command word
     * @return a command for the specified displayed-list index
     * @throws ParseException if the index or option is invalid
     */
    @Override
    public ShowCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        try {
            Index index = parseIndex(trimmedArgs);
            return new ShowCommand(index);
        } catch (ParseException exception) {
            String message = exception.getMessage() + System.lineSeparator()
                    + String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE);
            throw new ParseException(message, exception);
        }
    }

    private Index parseIndex(String trimmedArgs) throws ParseException {
        if (!trimmedArgs.startsWith("--")) {
            return ParserUtil.parseIndex(trimmedArgs);
        }

        String[] optionAndValue = trimmedArgs.split("\\s+", 2);
        if (optionAndValue.length != 2 || !ID_OPTION.equals(optionAndValue[0])) {
            throw new ParseException("Expected the --id option followed by a positive integer.");
        }

        return ParserUtil.parseIndex(optionAndValue[1]);
    }
}
