package team.serenity.ui.groupdata;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import team.serenity.commons.core.LogsCenter;
import team.serenity.model.group.Lesson;
import team.serenity.model.group.Student;
import team.serenity.ui.DataPanel;

public class GroupDataPanel extends DataPanel {
    private static final String FXML = "GroupDataPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GroupDataPanel.class);

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab attendanceTab;

    @FXML
    private Tab participationTab;

    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

    @FXML
    private ListView<Student> studentListView;

    @FXML
    private ListView<Lesson> lessonListView;

    @FXML
    private TableView<Lesson> attendanceTableView;

    @FXML
    private TableView<Lesson> participationTableView;

    @FXML
    private TableColumn<Lesson, String> nameColumn;

    @FXML
    private TableColumn<Lesson, String> studentNoColumn;

    /**
     * Constructor for panel to display tutorial group data.
     */
    public GroupDataPanel(ObservableList<Lesson> lessonList, ObservableList<Student> studentList) {
        super(FXML);
        this.studentListView.setItems(studentList);
        this.studentListView.setCellFactory(listView -> new StudentListViewCell());
        this.lessonListView.setItems(lessonList);
        this.lessonListView.setCellFactory(listView -> new LessonListViewCell());

        this.attendanceTableView.setItems(lessonList);
        this.participationTableView.setItems(lessonList);

        List<TableColumn<Lesson, String>> columns = new ArrayList<>();
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        columns.add(nameColumn);
        this.studentNoColumn.setCellValueFactory(new PropertyValueFactory<>("studentNo"));
        columns.add(studentNoColumn);

        for (Lesson lesson : lessonList) {
            TableColumn<Lesson, String> lessonColumn = new TableColumn<>();
            lessonColumn.setText(lesson.getName());
            lessonColumn.setCellValueFactory(new PropertyValueFactory<>(lesson.getName()));
            columns.add(lessonColumn);
        }

        this.attendanceTableView.getColumns().setAll(columns);
        this.participationTableView.getColumns().setAll(columns);
    }

    /**
     * Switch tabs.
     */
    public void changeTab(String tabName) {
        if (tabName.equals("attendanceTab")) {
            selectionModel.select(2);
        } else if (tabName.equals("participationTab")) {
            selectionModel.select(3);
        } else {
            // error
        }
    }

    class LessonListViewCell extends ListCell<Lesson> {

        @Override
        protected void updateItem(Lesson lesson, boolean empty) {
            super.updateItem(lesson, empty);

            if (empty || lesson == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LessonCard(lesson, getIndex() + 1).getRoot());
            }
        }
    }

    class StudentListViewCell extends ListCell<Student> {

        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
            }
        }
    }
}
