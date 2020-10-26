package team.serenity.model.managers;

import static java.util.Objects.requireNonNull;
import static team.serenity.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import team.serenity.model.group.GroupLessonKey;
import team.serenity.model.group.StudentInfo;
import team.serenity.model.group.exceptions.GroupLessonPairNotFoundException;
import team.serenity.model.util.UniqueList;

public class StudentInfoManager implements ReadOnlyStudentInfoManager {

    private final Map<GroupLessonKey, UniqueList<StudentInfo>> mapToListOfStudentsInfo;

    /**
     * Instantiates a new StudentInfoManager
     */
    public StudentInfoManager() {
        this.mapToListOfStudentsInfo = new HashMap<>();
    }

    /**
     * Creates a StudentInfoManager using the StudentInfo in the {@code toBeCopied}
     */
    public StudentInfoManager(ReadOnlyStudentInfoManager toBeCopied) {
        this.mapToListOfStudentsInfo = new HashMap<>();
        resetData(toBeCopied);
    }

    // Methods that overrides the whole StudentInfo Map

    /**
     * Replaces the contentws of the studentInfo map with {@code newStudentInfoMap}.
     * {@code newStudentInfoMap} must not contain duplicate students.
     */
    public void setStudentInfo(Map<GroupLessonKey, UniqueList<StudentInfo>> newStudentInfoMap) {
        this.mapToListOfStudentsInfo.clear();
        this.mapToListOfStudentsInfo.putAll(newStudentInfoMap);
    }

    /**
     * Resets existing data of this {@code StudentInfoManager} with {@code newData}
     */
    public void resetData(ReadOnlyStudentInfoManager newData) {
        requireNonNull(newData);
        setStudentInfo(newData.getStudentInfoMap());
    }

    @Override
    public Map<GroupLessonKey, UniqueList<StudentInfo>> getStudentInfoMap() {
        return this.mapToListOfStudentsInfo;
    }

    // StudentInfo-level operations

    /**
     * Adds the specified {@code StudentInfo} to the Specified {@code Group}.
     * @param key
     * @param studentInfo
     */
    public void addStudentInfoToKey(GroupLessonKey key, StudentInfo studentInfo)
            throws GroupLessonPairNotFoundException {
        requireAllNonNull(key, studentInfo);
        Optional<UniqueList<StudentInfo>> studentInfos = Optional.ofNullable(this.mapToListOfStudentsInfo.get(key));
        if (studentInfos.isPresent()) {
            studentInfos.get().add(studentInfo);
        } else {
            throw new GroupLessonPairNotFoundException();
        }
    }

    /**
     * Replaces listOfStudentsInfo stored at {@code key} with {@code newListOfStudentsInfo}.
     * @param key
     * @param newListOfStudentsInfo
     */
    public void setListOfStudentsInfoToGroupLessonKey(GroupLessonKey key,
                                                      UniqueList<StudentInfo> newListOfStudentsInfo) {
        requireAllNonNull(key, newListOfStudentsInfo);
        this.mapToListOfStudentsInfo.put(key, newListOfStudentsInfo);
    }

    /**
     * Checks if the specified {@code StudentInfo} exists in the group lesson pair.
     * @param key
     * @param studentInfo
     * @return whether StudentInfo exists in the group
     */
    public boolean checkIfStudentInfoExist(GroupLessonKey key, StudentInfo studentInfo)
            throws GroupLessonPairNotFoundException {
        requireAllNonNull(key, studentInfo);
        Optional<UniqueList<StudentInfo>> studentInfos = Optional.ofNullable(this.mapToListOfStudentsInfo.get(key));
        if (studentInfos.isPresent()) {
            return studentInfos.get().contains(studentInfo);
        } else {
            throw new GroupLessonPairNotFoundException();
        }
    }

    /**
     * Gets ListOfStudentInfo from a particular {@code GroupLessonKey}
     * @param key
     * @return All StudentInfo from a particular group lesson pair
     */
    public UniqueList<StudentInfo> getListOfStudentsInfoFromGroupLessonKey(GroupLessonKey key)
            throws GroupLessonPairNotFoundException {
        requireNonNull(key);
        Optional<UniqueList<StudentInfo>> studentInfos = Optional.ofNullable(this.mapToListOfStudentsInfo.get(key));
        if (studentInfos.isPresent()) {
            return studentInfos.get();
        } else {
            throw new GroupLessonPairNotFoundException();
        }
    }

    /**
     * @param key
     * @param studentInfo
     * @throws GroupLessonPairNotFoundException
     */
    public void deleteStudentInfoFromGroupLessonKey(GroupLessonKey key, StudentInfo studentInfo)
            throws GroupLessonPairNotFoundException {
        requireAllNonNull(key, studentInfo);
        Optional<UniqueList<StudentInfo>> studentInfos = Optional.ofNullable(this.mapToListOfStudentsInfo.get(key));
        if (studentInfos.isPresent()) {
            studentInfos.get().remove(studentInfo);
        } else {
            throw new GroupLessonPairNotFoundException();
        }
    }

    //util methods

    @Override
    public int hashCode() {
        return this.mapToListOfStudentsInfo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this //short Circuit if same object
                || (obj instanceof StudentInfoManager
                && this.mapToListOfStudentsInfo.equals(((StudentInfoManager) obj).mapToListOfStudentsInfo));
    }

    @Override
    public String toString() {
        return "StudentInfoManager : \n";
    }
}
