package team.serenity.model.managers;

import static team.serenity.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Optional;

import team.serenity.model.group.Group;
import team.serenity.model.group.Lesson;
import team.serenity.model.util.UniqueList;

public class LessonManager {
    private final HashMap<Group, UniqueList<Lesson>> mapToListOfLessons;

    public LessonManager() {
        this.mapToListOfLessons = new HashMap<>();
    }

    /**
     * Adds a given Lesson to a Group
     * @param group
     * @param lesson
     */
    public void addLessonToGroup(Group group, Lesson lesson) {
        requireAllNonNull(group, lesson);
        UniqueList<Lesson> lessonList = this.mapToListOfLessons.get(group);
        if (lessonList != null) {
            lessonList.add(lesson);
        }
    }

    /**
     * Replaces the lessons of {@code group} with {@code lessons}
     * @param group
     * @param listOfLessons
     */
    public void setListOfLessonsToGroup(Group group, UniqueList<Lesson> listOfLessons) {
        requireAllNonNull(group, listOfLessons);
        this.mapToListOfLessons.put(group, listOfLessons);
    }

    public Optional<UniqueList<Lesson>> getListOfLessonsFromGroup(Group group) {
        return Optional.ofNullable(this.mapToListOfLessons.get(group));
    }

}
