package team.serenity.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import team.serenity.commons.core.Messages;
import team.serenity.commons.core.index.Index;
import team.serenity.logic.commands.exceptions.CommandException;
import team.serenity.model.Model;
import team.serenity.model.group.Lesson;
import team.serenity.model.group.Question;
import team.serenity.model.util.UniqueList;

/**
 * Deletes a question identified using it's displayed index from the specified group's lesson in Serenity.
 */
public class DelQnCommand extends Command {

    public static final String COMMAND_WORD = "delqn";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the question identified by the index number used in the displayed question list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_QUESTION_SUCCESS = "[%1$s %2$s] Deleted Question: %3$s";

    private final Index targetIndex;

    public DelQnCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getFilteredGroupList().size() != 1) {
            throw new CommandException(Messages.MESSAGE_NOT_VIEWING_A_GROUP);
        }

        if (model.getFilteredLessonList().size() != 1) {
            throw new CommandException(Messages.MESSAGE_NOT_VIEWING_A_LESSON);
        }

        Lesson uniqueLesson = model.getFilteredLessonList().get(0);
        UniqueList<Question> uniqueQuestionList = uniqueLesson.getQuestionList();
        ObservableList<Question> lastViewedQuestionList = uniqueQuestionList.asUnmodifiableObservableList();

        if (this.targetIndex.getZeroBased() >= lastViewedQuestionList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
        }

        Question questionToDelete = lastViewedQuestionList.get(this.targetIndex.getZeroBased());
        uniqueQuestionList.remove(questionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_QUESTION_SUCCESS,
                model.getFilteredGroupList().get(0),
                uniqueLesson, questionToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DelQnCommand // instanceof handles nulls
                && this.targetIndex.equals(((DelQnCommand) other).targetIndex)); // state check
    }

}
