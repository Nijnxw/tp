package team.serenity.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import team.serenity.logic.commands.CommandTestUtil;
import team.serenity.model.Serenity;
import team.serenity.model.group.Group;
import team.serenity.model.group.Student;
import team.serenity.model.managers.GroupManager;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Group GROUP_C = new GroupBuilder().withName("G06")
        .withStudents(
            new Student("Jeffery", "e0000000"),
            new Student("Luna", "e0111111"),
            new Student("Queenie", "e0222222")
        ).withClasses("4-2", "5-1")
        .build();

    public static final Group GROUP_D = new GroupBuilder().withName("G07")
        .withStudents(
            new Student("Freddie", "e0000000"),
            new Student("June", "e0101011")
        ).withClasses("4-2", "5-1", "5-2", "6-1")
        .build();

    // Manually added - Group's details found in {@code CommandTestUtil}
    public static final Group GROUP_A = new GroupBuilder().withName(CommandTestUtil.VALID_GRP_GROUP_A)
        .withFilePath(CommandTestUtil.VALID_PATH_GROUP_A).build();
    //    public static final Group GROUP_B = new GroupBuilder().withName(CommandTestUtil.VALID_GRP_GROUP_B)
    //        .withFilePath(CommandTestUtil.VALID_PATH_GROUP_B).build();

    private TypicalGroups() {
    } // prevents instantiation

    /**
     * Returns an {@code Serenity} with all the typical groups.
     */
    public static Serenity getTypicalSerenity() {
        Serenity serenity = new Serenity();
        for (Group group : getTypicalGroups()) {
            serenity.addGroup(group);
        }
        return serenity;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(GROUP_A, GROUP_C, GROUP_D));
    }

    public static GroupManager getTypicalGroupManager() {
        GroupManager groupManager = new GroupManager();
        groupManager.addGroup(GROUP_C);
        groupManager.addGroup(GROUP_D);
        return groupManager;
    }
}
