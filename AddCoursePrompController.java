package controllers;

import application.CourseData;
import application.UserData;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class AddCoursePrompController implements Initializable {

    @FXML
    private MFXTextField courseNameField;

    @FXML
    private MFXTextField courseInitialField;

    @FXML
    private MFXTextField creditField;

    @FXML
    private MFXTextField totalSeatField;

    @FXML
    private MFXTextField descriptionField;

    @FXML
    private MFXButton addButton;

    @FXML
    private MFXButton uploadButton;

    @FXML
    private MFXButton cancelButton;

    @FXML
    private Label titleLabel;
    private TableView<CourseData> tableView;
    private File syllabusFolder;
    private CourseManagementController courseManagementController;
    private ObservableList<CourseData> courseDataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tableView = new TableView<>();
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        String courseInitial = courseInitialField.getText();
        String courseName = courseNameField.getText();
        String creditText = creditField.getText();
        String totalSeatText = totalSeatField.getText();
        String description = descriptionField.getText();

        if (courseName.isEmpty() || courseInitial.isEmpty() || creditText.isEmpty() || totalSeatText.isEmpty() || description.isEmpty()) {
            // Show an alert indicating that all fields are required
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields are required!");
            alert.showAndWait();
            return;
        }
        int credit;
        int totalSeat;
        try {
            credit = Integer.parseInt(creditText);
            totalSeat = Integer.parseInt(totalSeatText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Credit and Total Seat must be integers!");
            alert.showAndWait();
            return;
        }

        if (credit <= 0 || credit > 50 || totalSeat <= 0 || totalSeat > 50) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Credit and Total Seat must be between 1 and 50!");
            alert.showAndWait();
            return;
        }

        String courseData = courseInitial + "," + courseName + "," + credit + "," + totalSeat + "," + description + "\n";


        try (FileWriter writer = new FileWriter("course_data.txt", true)) {
            writer.write(courseData);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Course added successfully!");
            alert.showAndWait();
            Stage stage = (Stage) addButton.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while adding the course.");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleUploadFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Syllabus");
        File selectedFile = fileChooser.showOpenDialog(null);

        String courseInitial = courseInitialField.getText();
        File syllabusFolder = new File("Syllabus");

        // Create the "Syllabus" folder if it doesn't exist
        if (!syllabusFolder.exists()) {
            syllabusFolder.mkdir();
        }

        if (selectedFile != null) {
            File destFile = new File(syllabusFolder, courseInitial + ".pdf");

            try {
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Syllabus uploaded successfully!");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                // Show error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error uploading syllabus.");
                alert.showAndWait();
            }
        } else {
            // Show message indicating no file was selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No File Selected");
            alert.setHeaderText(null);
            alert.setContentText("No syllabus file selected. Course will be added without a syllabus.");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    private Window getWindow(ActionEvent event) {
        return ((Stage) ((MFXButton) event.getSource()).getScene().getWindow());
    }

    @FXML
    private void handleTitleLabelClicked(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Developed By Asadullah Hil Galib 221820642");
        alert.showAndWait();
    }

    public CourseData getCourseData() {
        CourseData courseData = new CourseData();
        courseData.setCourseInitial(getCourseInital());
        courseData.setCourseName(getCourseName());
        courseData.setCredit(getCredit());
        courseData.setTotalSeat(getTotalSeat());
        courseData.setDescription(getDescription());
        courseData.setSyllabusFilePath(syllabusFolder.getAbsolutePath() + "\\" + getCourseInital() + ".pdf");

        return new CourseData(getCourseInital(), getCourseName(), getDescription(), getCredit(), getTotalSeat(), syllabusFolder.getAbsolutePath() + "\\" + getCourseInital() + ".pdf");
    }


    public void addCourse(CourseData courseData) {
        this.courseManagementController.getCourseDataList().add(courseData);
        if (this.courseManagementController != null) {
            this.courseManagementController.getCourseDataList().add(courseData);
        }
    }



    public String getCourseInital() {
        return courseInitialField.getText();
    }

    public String getCourseName() {
        return courseNameField.getText();
    }

    public Integer getCredit() {
        return Integer.parseInt(creditField.getText());
    }

    public Integer getTotalSeat() {
        return Integer.parseInt(totalSeatField.getText());
    }

    public String getDescription() {
        return descriptionField.getText();
    }

    public void setCourseInitial(String courseInitial) {
        courseInitialField.setText(courseInitial);
    }

    public void setCourseName(String courseName) {
        courseNameField.setText(courseName);
    }

    public void setCredit(String credit) {
        creditField.setText(credit);
    }

    public void setTotalSeat(String totalSeat) {
        totalSeatField.setText(totalSeat);
    }

    public void setDescription(String description) {
        descriptionField.setText(description);
    }

    public void setTableView(TableView<CourseData> tableView) {
        this.tableView = tableView;
    }

    public void setSyllabusFolder(File syllabusFolder) {
        this.syllabusFolder = syllabusFolder;
    }

    public AddCoursePrompController() {
        this.tableView = new TableView<>();
    }

    public ObservableList<CourseData> getCourseDataList() {
        return courseDataList;
    }


}
