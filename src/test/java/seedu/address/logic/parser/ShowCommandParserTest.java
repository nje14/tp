package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowCommand;

/**
 * Tests for {@code ShowCommandParser}.
 */
public class ShowCommandParserTest {

    private static final String INVALID_INDEX_MESSAGE = ParserUtil.MESSAGE_INVALID_INDEX
            + System.lineSeparator() + "Invalid command format!\n" + ShowCommand.MESSAGE_USAGE;
    private static final String INVALID_OPTION_MESSAGE = "Expected the --id option followed by a positive integer."
            + System.lineSeparator() + "Invalid command format!\n" + ShowCommand.MESSAGE_USAGE;

    private final ShowCommandParser parser = new ShowCommandParser();

    @Test
    public void parse_validArgs_returnsShowCommand() {
        assertParseSuccess(parser, "--id 1", new ShowCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, "1", new ShowCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidIds_throwsParseException() {
        assertParseFailure(parser, "--id not-a-number", INVALID_INDEX_MESSAGE);
        assertParseFailure(parser, "--id 0", INVALID_INDEX_MESSAGE);
        assertParseFailure(parser, "--id -1", INVALID_INDEX_MESSAGE);
        assertParseFailure(parser, "--id 999999999999999999999999", INVALID_INDEX_MESSAGE);
    }

    @Test
    public void parse_invalidOptionOrMissingId_throwsParseException() {
        assertParseFailure(parser, "--id", INVALID_OPTION_MESSAGE);
        assertParseFailure(parser, "--name 1", INVALID_OPTION_MESSAGE);
        assertParseFailure(parser, "--id 1 extra", INVALID_INDEX_MESSAGE);
    }
}
