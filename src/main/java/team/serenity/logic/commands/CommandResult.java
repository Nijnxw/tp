package team.serenity.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Help information should be shown to the user.
     */
    private final boolean showHelp;

    /**
     * The application should exit.
     */
    private final boolean exit;

    /**
     * The application should toggle to lesson data screen.
     */
    private final boolean isViewLsn;

    /**
     * The application should toggle to group data screen.
     */
    private final boolean isViewGrp;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean viewLsn, boolean viewGrp) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isViewLsn = viewLsn;
        this.isViewGrp = viewGrp;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return this.feedbackToUser;
    }

    public boolean isShowHelp() {
        return this.showHelp;
    }

    public boolean isExit() {
        return this.exit;
    }

    public boolean isToggleLsnView() {
        return this.isViewLsn;
    }

    public boolean isToggleGrpView() {
        return this.isViewGrp;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return this.feedbackToUser.equals(otherCommandResult.feedbackToUser)
            && this.showHelp == otherCommandResult.showHelp
            && this.exit == otherCommandResult.exit
            && this.isViewGrp == otherCommandResult.isViewGrp
            && this.isViewLsn == otherCommandResult.isViewLsn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.feedbackToUser, this.showHelp, this.exit, this.isViewGrp, this.isViewLsn);
    }
}
