package controllers;

import application.CourseData;
import application.CourseFileLoader;
import application.DataFileLoader;
import application.UserData;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManagementController {

    @FXML
    private MFXButton manageUserProfilesButton;

    @FXML
    private MFXButton registrationRequestsButton;

    @FXML
    private MFXButton courseManagementButton;

    @FXML
    private MFXButton viewCourseEnrollmentButton;

    @FXML
    private MFXButton viewTuitionAndFeeStatisticsButton;

    @FXML
    private MFXButton manageBillingButton;

    @FXML
    private MFXButton manageFinancialAidButton;

    @FXML
    private MFXButton manageScholarshipsButton;

    @FXML
    private MFXButton newsFeedButton;

    @FXML
    private MFXButton postManagementButton;

    @FXML
    private MFXButton viewUserEngagementInsightsButton;

    @FXML
    private MFXButton viewGradeDistributionsButton;

    @FXML
    private MFXButton viewPerformanceMetricsButton;

    @FXML
    private MFXButton eventManagementButton;

    @FXML
    private MFXButton resourceManagementButton;

    @FXML
    private MFXButton dashBoardButton;

    @FXML
    private MFXButton manageWaitlistsButton;

    @FXML
    private MFXButton accessReportsAndDataExportButton;

    @FXML
    private MFXButton createSystemWideAnnouncementsButton;

    @FXML
    private MFXButton manageAnnouncementsButton;

    @FXML
    private MFXButton sendNotificationsButton;

    @FXML
    private MFXScrollPane scrollPane;
    @FXML
    private MFXNotificationCenter notificationCenter;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    private MFXButton addCourseButton;
    @FXML
    private MFXButton removeCourseButton;
    @FXML
    private MFXButton editCourseButton;

    @FXML
    private TableView<CourseData> tableView;

    @FXML
    private TableColumn<CourseData, String> courseInitialColumn;

    @FXML
    private TableColumn<CourseData, String> courseNameColumn;

    @FXML
    private TableColumn<CourseData, String> descriptionColumn;

    @FXML
    private TableColumn<CourseData, Integer> creditColumn;

    @FXML
    private TableColumn<CourseData, Integer> totalSeatColumn;

    @FXML
    private TableColumn<CourseData, String> syllabusColumn;

    private static CourseData courseData;
    private ObservableList<CourseData> courseDataList = FXCollections.observableArrayList();

    public void initialize() {

        tableView.setRowFactory(tv -> {
            TableRow<CourseData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    CourseData rowData = row.getItem();
                    tableView.getSelectionModel().select(rowData);
                }
            });
            return row;
        });

        scrollPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY();
            double deltaX = event.getDeltaX();

            if (deltaY != 0) {
                scrollVertically(deltaY);
            }

            if (deltaX != 0) {
                scrollHorizontally(deltaX);
            }
        });

        dashBoardButton.setOnAction(this::handleDashboardButtonAction);
        manageUserProfilesButton.setOnAction(this::handleManageUserProfilesButtonAction);
        registrationRequestsButton.setOnAction(this::handleRegistrationRequestsButtonAction);
        courseManagementButton.setOnAction(this::handleCourseManagementButtonAction);
        viewCourseEnrollmentButton.setOnAction(this::handleViewCourseEnrollmentButtonAction);
        viewTuitionAndFeeStatisticsButton.setOnAction(this::handleViewTuitionAndFeeStatisticsButtonAction);
        manageBillingButton.setOnAction(this::handleManageBillingButtonAction);
        manageFinancialAidButton.setOnAction(this::handleManageFinancialAidButtonAction);
        manageScholarshipsButton.setOnAction(this::handleManageScholarshipsButtonAction);
        newsFeedButton.setOnAction(this::handleNewsFeedButtonAction);
        postManagementButton.setOnAction(this::handlePostManagementButtonAction);
        viewUserEngagementInsightsButton.setOnAction(this::handleViewUserEngagementInsightsButtonAction);
        viewGradeDistributionsButton.setOnAction(this::handleViewGradeDistributionsButtonAction);
        viewPerformanceMetricsButton.setOnAction(this::handleViewPerformanceMetricsButtonAction);
        eventManagementButton.setOnAction(this::handleEventManagementButtonAction);
        resourceManagementButton.setOnAction(this::handleResourceManagementButtonAction);
        manageWaitlistsButton.setOnAction(this::handleManageWaitlistsButtonAction);
        accessReportsAndDataExportButton.setOnAction(this::handleAccessReportsAndDataExportButtonAction);
        createSystemWideAnnouncementsButton.setOnAction(this::handleCreateSystemWideAnnouncementsButtonAction);
        manageAnnouncementsButton.setOnAction(this::handleManageAnnouncementsButtonAction);
        sendNotificationsButton.setOnAction(this::handleSendNotificationsButtonAction);
        // Initialize your GUI components here
        courseInitialColumn.setCellValueFactory(new PropertyValueFactory<>("courseInitial"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        creditColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));
        totalSeatColumn.setCellValueFactory(new PropertyValueFactory<>("totalSeat"));
        syllabusColumn.setCellValueFactory(new PropertyValueFactory<>("syllabusFilePath"));

        courseNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        courseInitialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        creditColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        totalSeatColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        courseNameColumn.setOnEditCommit(this::handleTableCellEdit);
        courseInitialColumn.setOnEditCommit(this::handleTableCellEdit);
        creditColumn.setOnEditCommit(this::handleCreditTableCellEdit);
        totalSeatColumn.setOnEditCommit(this::handleTotalSeatTableCellEdit);
        descriptionColumn.setOnEditCommit(this::handleTableCellEdit);
        // Add event handlers to buttons, etc.
        addCourseButton.setOnAction(this::handleAddCourseButtonAction);
        removeCourseButton.setOnAction(this::handleRemoveCourseButtonAction);
        editCourseButton.setOnAction(this::handleEditCourseButtonAction);
        notificationCenter.setOnIconClicked(this::handleNotificationCenterAction);

        List<CourseData> loadedData = CourseFileLoader.loadCourseDataFromFile("course_data.txt");

        courseDataList.addAll(loadedData);

        tableView.setItems(courseDataList);
        tableView.setEditable(true);


    }

    @FXML
    private void handleTableCellEdit(TableColumn.CellEditEvent<CourseData, String> event) {
        CourseData courseData = event.getRowValue();
        TableColumn<CourseData, String> column = event.getTableColumn();

        if (column == courseNameColumn) {
            courseData.setCourseName(event.getNewValue());
        } else if (column == courseInitialColumn) {
            courseData.setCourseInitial(event.getNewValue());
        } else if (column == descriptionColumn) {
            courseData.setDescription(event.getNewValue());
        }

        updateDataFile(); // Write the updated data back to the file
    }

    @FXML
    private void handleCreditTableCellEdit(TableColumn.CellEditEvent<CourseData, Integer> event) {
        CourseData courseData = event.getRowValue();
        courseData.setCredit(event.getNewValue());
        updateDataFile(); // Write the updated data back to the file
    }

    @FXML
    private void handleTotalSeatTableCellEdit(TableColumn.CellEditEvent<CourseData, Integer> event) {
        CourseData courseData = event.getRowValue();
        courseData.setTotalSeat(event.getNewValue());
        updateDataFile(); // Write the updated data back to the file
    }

    private void updateDataFile() {
        try (FileWriter writer = new FileWriter("course_data.txt")) {
            for (CourseData courseData : courseDataList) {
                String courseDataLine = String.format("%s,%s,%d,%d,%s\n",
                        courseData.getCourseInitial(),
                        courseData.getCourseName(),
                        courseData.getCredit(),
                        courseData.getTotalSeat(),
                        courseData.getDescription()
                );
                writer.write(courseDataLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message)
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while updating the data file.");
            alert.showAndWait();
        }
    }


    public ObservableList<CourseData> getCourseDataList() {
        return courseDataList;
    }

    public void setCourseDataList(ObservableList<CourseData> courseDataList) {
        this.courseDataList = courseDataList;
    }

    public void addUserData(CourseData courseData) {
        courseDataList.add(courseData);

    }

    private void loadCourseDataFromFile() {
        File file = new File("course_data.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    CourseData courseData = new CourseData(
                            parts[0], parts[1], parts[2],
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4]),
                            parts[5]
                    );
                    courseDataList.add(courseData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Platform.runLater(() -> {
            tableView.getItems().addAll(courseDataList);
            tableView.refresh();
        });
    }

    @FXML
    private void handleNotificationCenterAction(MouseEvent mouseEvent) {
    }

    // Define event handler methods here
    private void handleAddCourseButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddCoursePromp.fxml"));
            Parent root = loader.load();

            AddCoursePrompController addCoursePrompController = loader.getController();
            // Optionally, you can pass any necessary data to the controller here

            Stage popupStage = new Stage();
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Add Course");

            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRemoveCourseButtonAction(ActionEvent event) {

        CourseData selectedCourse = tableView.getSelectionModel().getSelectedItem();

        if (selectedCourse != null) {
            courseDataList.remove(selectedCourse);
            updateDataFile(); // Update the data file after removal
        } else {
            // Show an alert indicating no course is selected for removal
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Course Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a course to remove.");
            alert.showAndWait();
        }

    }

    private void handleEditCourseButtonAction(ActionEvent event) {
        // Code to handle editing a course
    }


    @FXML
    private void scrollVertically(double deltaY) {
        double newValue = scrollPane.getVvalue() - (deltaY / scrollPane.getHeight());
        scrollPane.setVvalue(newValue);
    }

    @FXML
    private void scrollHorizontally(double deltaX) {
        double newValue = scrollPane.getHvalue() - (deltaX / scrollPane.getWidth());
        scrollPane.setHvalue(newValue);
    }


    @FXML
    private void handleDashboardButtonAction(ActionEvent event) {
        System.out.println("Dashboard button clicked!");
    }

    private void handleManageUserProfilesButtonAction(ActionEvent event) {
        try {
            FXMLLoader manageLoader = new FXMLLoader(getClass().getResource("/fxml/ManageUserProfile.fxml"));
            Parent manageRoot = manageLoader.load();
            Scene manageScene = new Scene(manageRoot);

            Stage manageStage = new Stage();
            manageStage.initModality(Modality.WINDOW_MODAL);
            manageStage.initOwner(manageUserProfilesButton.getScene().getWindow());
            manageStage.setScene(manageScene);
            manageStage.show();
            manageStage.setTitle("User Management");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleRegistrationRequestsButtonAction(ActionEvent event) {
        try {
            FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("/fxml/RegistrationRequests.fxml"));
            Parent registerRoot = registerLoader.load();
            Scene registerScene = new Scene(registerRoot);

            Stage registerStage = new Stage();
            registerStage.initModality(Modality.WINDOW_MODAL);
            registerStage.initOwner(registrationRequestsButton.getScene().getWindow());
            registerStage.setScene(registerScene);
            registerStage.show();
            registerStage.setTitle("Manage Registration Requests");


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleCourseManagementButtonAction(ActionEvent event) {
        // Define what happens when the add new course button is clicked
    }

    private void handleViewCourseEnrollmentButtonAction(ActionEvent event) {
        // Define what happens when the view course enrollment button is clicked
    }

    private void handleViewTuitionAndFeeStatisticsButtonAction(ActionEvent event) {
        // Define what happens when the view tuition and fee statistics button is clicked
    }

    private void handleManageBillingButtonAction(ActionEvent event) {
        // Define what happens when the manage billing button is clicked
    }

    private void handleManageFinancialAidButtonAction(ActionEvent event) {
        // Define what happens when the manage financial aid button is clicked
    }

    private void handleManageScholarshipsButtonAction(ActionEvent event) {
        // Define what happens when the manage scholarships button is clicked
    }

    private void handleNewsFeedButtonAction(ActionEvent event) {
        // Define what happens when the news feed button is clicked
    }

    private void handlePostManagementButtonAction(ActionEvent event) {
        // Define what happens when the posts approval button is clicked
    }

    private void handleEventManagementButtonAction(ActionEvent event) {
        // Define what happens when the remove posts button is clicked
    }

    private void handleResourceManagementButtonAction(ActionEvent event) {
        // Define what happens when the remove posts button is clicked
    }

    private void handleViewUserEngagementInsightsButtonAction(ActionEvent event) {
        // Define what happens when the view user engagement insights button is clicked
    }

    private void handleViewGradeDistributionsButtonAction(ActionEvent event) {
        // Define what happens when the view grade distributions button is clicked
    }

    private void handleViewPerformanceMetricsButtonAction(ActionEvent event) {
        // Define what happens when the view performance metrics button is clicked
    }


    private void handleManageWaitlistsButtonAction(ActionEvent event) {
        // Define what happens when the manage waitlists button is clicked
    }

    private void handleAccessReportsAndDataExportButtonAction(ActionEvent event) {
        // Define what happens when the access reports and data export button is clicked
    }

    private void handleCreateSystemWideAnnouncementsButtonAction(ActionEvent event) {
        // Define what happens when the create system-wide announcements button is clicked
    }

    private void handleManageAnnouncementsButtonAction(ActionEvent event) {
        // Define what happens when the manage announcements button is clicked
    }

    private void handleSendNotificationsButtonAction(ActionEvent event) {
        // Define what happens when the send notifications button is clicked
    }


    public void handleLogoutMenuItemAction(ActionEvent event) {
    }
}
