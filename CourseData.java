package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CourseData {
    private final StringProperty courseInitial = new SimpleStringProperty();
    private final StringProperty courseName = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty syllabusFilePath = new SimpleStringProperty();
    private int credit;
    private int totalSeat;

    public CourseData(String courseInitial, String courseName, String description, int credit, int totalSeat, String syllabusFilePath) {
        this.courseInitial.set(courseInitial);
        this.courseName.set(courseName);
        this.description.set(description);
        this.credit = credit;
        this.totalSeat = totalSeat;
        this.syllabusFilePath.set(syllabusFilePath);
    }

    public CourseData() {
    }

    public CourseData(String courseInitial, String courseName, String description, int credit, int totalSeat) {
        this.courseInitial.set(courseInitial);
        this.courseName.set(courseName);
        this.description.set(description);
        this.credit = credit;
        this.totalSeat = totalSeat;
    }

    // Getters and setters

    public String getCourseInitial() {
        return courseInitial.get();
    }

    public StringProperty courseInitialProperty() {
        return courseInitial;
    }

    public void setCourseInitial(String courseInitial) {
        this.courseInitial.set(courseInitial);
    }

    public String getCourseName() {
        return courseName.get();
    }

    public StringProperty courseNameProperty() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName.set(courseName);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public String getSyllabusFilePath() {
        String path = syllabusFilePath.get();
        return (path != null && !path.isEmpty()) ? "Submitted" : "N/A";
    }


    public StringProperty syllabusFilePathProperty() {
        return syllabusFilePath;
    }

    public void setSyllabusFilePath(String syllabusFilePath) {
        this.syllabusFilePath.set(syllabusFilePath);
    }
}
