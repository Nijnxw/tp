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
     * The application should add group buttons to the button bar.
     */
    private final boolean isAddGrp;

    /**
     * The application should delete group buttons from the button bar.
     */
    private final boolean isDelGrp;

    /**
     * The application should select attendanceTab in group data screen.
     */
    private final boolean isViewAtt;

    /**
     * The application should select participationTab in group data screen.
     */
    private final boolean isViewScore;

    /**
     * The application should select flaggedAttendanceTab in serenity data screen.
     */
    private final boolean isFlagAtt;

    /**
     * The application should select questionTab in serenity data screen.
     */
    private final boolean isViewQn;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, boolean viewLsn, boolean viewGrp,
        boolean addGrp, boolean delGrp, boolean viewAtt, boolean viewScore, boolean flagAtt, boolean viewQn) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.isViewLsn = viewLsn;
        this.isViewGrp = viewGrp;
        this.isAddGrp = addGrp;
        this.isDelGrp = delGrp;
        this.isViewAtt = viewAtt;
        this.isViewScore = viewScore;
        this.isFlagAtt = flagAtt;
        this.isViewQn = viewQn;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false, false, false, false, false);
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

    public boolean isAddGrp() {
        return this.isAddGrp;
    }

    public boolean isDelGrp() {
        return isDelGrp;
    }

    public boolean isViewAtt() {
        return this.isViewAtt;
    }

    public boolean isViewScore() {
        return isViewScore;
    }

    public boolean isFlagAtt() {
        return isFlagAtt;
    }

    public boolean isViewQn() {
        return isViewQn;
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
            && this.isViewLsn == otherCommandResult.isViewLsn
            && this.isAddGrp == otherCommandResult.isAddGrp
            && this.isDelGrp == otherCommandResult.isDelGrp
            && this.isViewAtt == otherCommandResult.isViewAtt
            && this.isViewScore == otherCommandResult.isViewScore
            && this.isFlagAtt == otherCommandResult.isFlagAtt
            && this.isViewQn == otherCommandResult.isViewQn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.feedbackToUser, this.showHelp, this.exit, this.isViewGrp, this.isViewLsn,
            this.isAddGrp, this.isDelGrp, this.isViewAtt, this.isViewScore, this.isFlagAtt, this.isViewQn);
    }
}
