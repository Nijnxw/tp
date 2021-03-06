package team.serenity.logic.commands.studentinfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static team.serenity.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static team.serenity.commons.core.Messages.MESSAGE_NOT_VIEWING_A_GROUP;
import static team.serenity.commons.core.Messages.MESSAGE_NOT_VIEWING_A_LESSON;
import static team.serenity.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static team.serenity.testutil.Assert.assertThrows;
import static team.serenity.testutil.TypicalIndexes.INDEX_FIRST;
import static team.serenity.testutil.TypicalIndexes.INDEX_SECOND;
import static team.serenity.testutil.TypicalStudent.AARON;
import static team.serenity.testutil.TypicalStudent.GEORGE;

import org.junit.jupiter.api.Test;

import team.serenity.commons.core.index.Index;
import team.serenity.logic.commands.CommandResult;
import team.serenity.logic.commands.exceptions.CommandException;
import team.serenity.model.group.student.Student;
import team.serenity.testutil.StudentBuilder;

class MarkPresentCommandTest {

    @Test
    public void constructor_nullParameter_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkPresentCommand((Index) null));
        assertThrows(NullPointerException.class, () -> new MarkPresentCommand((Student) null));
    }

    @Test
    public void execute_markStudent_success() throws CommandException {
        ModelStubWithStudentsAbsent modelStub = new ModelStubWithStudentsAbsent();
        Student toMarkPresent = new StudentBuilder().build();

        CommandResult commandResult = new MarkPresentCommand(toMarkPresent).execute(modelStub);
        assertEquals(String.format(MarkPresentCommand.MESSAGE_SUCCESS, toMarkPresent),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_wrongName_throwsCommandException() throws CommandException {
        ModelStubWithStudentsAbsent modelStub = new ModelStubWithStudentsAbsent();
        Student wrongNameOne = new StudentBuilder().withName("Aaron").build();
        Student wrongNameTwo = new StudentBuilder().withName("Betty Tan").build();
        MarkPresentCommand markPresentCommandOne = new MarkPresentCommand(wrongNameOne);
        MarkPresentCommand markPresentCommandTwo = new MarkPresentCommand(wrongNameTwo);

        assertThrows(CommandException.class,
                String.format(MESSAGE_STUDENT_NOT_FOUND, wrongNameOne), () -> markPresentCommandOne.execute(modelStub));
        assertThrows(CommandException.class,
                String.format(MESSAGE_STUDENT_NOT_FOUND, wrongNameTwo), () -> markPresentCommandTwo.execute(modelStub));
    }

    @Test
    public void execute_wrongStudentNumber_throwsCommandException() {
        ModelStubWithStudentsAbsent modelStub = new ModelStubWithStudentsAbsent();
        Student wrongNumber = new StudentBuilder().withId("A0000000U").build();
        MarkPresentCommand markPresentCommand = new MarkPresentCommand(wrongNumber);

        assertThrows(CommandException.class,
                String.format(MESSAGE_STUDENT_NOT_FOUND, wrongNumber), () -> markPresentCommand.execute(modelStub));
    }

    @Test
    public void execute_notInGroup_throwsCommandException() {
        ModelStubWithNoGroup modelStub = new ModelStubWithNoGroup();
        Student toMarkPresent = new StudentBuilder().build();
        MarkPresentCommand markPresentCommand = new MarkPresentCommand(toMarkPresent);

        assertThrows(CommandException.class, MESSAGE_NOT_VIEWING_A_GROUP, () -> markPresentCommand.execute(modelStub));
    }

    @Test
    public void execute_notInLesson_throwsCommandException() {
        ModelStubWithNoLesson modelStub = new ModelStubWithNoLesson();
        Student toMarkPresent = new StudentBuilder().build();
        MarkPresentCommand markPresentCommand = new MarkPresentCommand(toMarkPresent);

        assertThrows(CommandException.class, MESSAGE_NOT_VIEWING_A_LESSON, () -> markPresentCommand.execute(modelStub));
    }

    @Test
    public void execute_markIndex_success() throws CommandException {
        ModelStubWithIndexAbsent modelStub = new ModelStubWithIndexAbsent();
        Student toMarkPresent = new StudentBuilder().build();

        CommandResult commandResult = new MarkPresentCommand(INDEX_FIRST).execute(modelStub);
        assertEquals(String.format(MarkPresentCommand.MESSAGE_SUCCESS, toMarkPresent),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_wrongIndex_throwsCommandException() {
        ModelStubWithIndexAbsent modelStub = new ModelStubWithIndexAbsent();
        MarkPresentCommand markPresentCommand = new MarkPresentCommand(INDEX_SECOND);

        assertThrows(CommandException.class,
                String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX,
                        INDEX_SECOND.getOneBased()), () -> markPresentCommand.execute(modelStub));
    }

    @Test
    public void execute_markAllStudent_success() throws CommandException {
        ModelStubWithStudentsAbsent modelStub = new ModelStubWithStudentsAbsent();

        CommandResult commandResult = new MarkPresentCommand().execute(modelStub);
        assertEquals(String.format(MarkPresentCommand.MESSAGE_ALL_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        MarkPresentCommand markAllPresentCommandA = new MarkPresentCommand();
        MarkPresentCommand markStudentPresentCommandA = new MarkPresentCommand(AARON);
        MarkPresentCommand copyMarkStudentPresentCommandA = new MarkPresentCommand(AARON);
        MarkPresentCommand markStudentPresentCommandB = new MarkPresentCommand(GEORGE);
        MarkPresentCommand markIndexPresentCommandA = new MarkPresentCommand(INDEX_FIRST);
        MarkPresentCommand copyMarkIndexPresentCommandA = new MarkPresentCommand(INDEX_FIRST);
        MarkPresentCommand markIndexPresentCommandB = new MarkPresentCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(markAllPresentCommandA.equals(markAllPresentCommandA));
        assertTrue(markStudentPresentCommandA.equals(markStudentPresentCommandA));
        assertTrue(markIndexPresentCommandA.equals(markIndexPresentCommandA));

        // same values -> return true
        assertTrue(markStudentPresentCommandA.equals(copyMarkStudentPresentCommandA));
        assertTrue(markIndexPresentCommandA.equals(copyMarkIndexPresentCommandA));

        // different types -> return false
        assertFalse(markStudentPresentCommandA.equals(markIndexPresentCommandA));

        // different values -> return different
        assertFalse(markStudentPresentCommandA.equals(markStudentPresentCommandB));
        assertFalse(markIndexPresentCommandA.equals(markIndexPresentCommandB));
    }
}

