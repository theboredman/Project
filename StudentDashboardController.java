package controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controllers.ControllerStudent;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {

    @FXML
    private ImageView profileImageView;

    @FXML
    private MFXNotificationCenter notificationCenter;

    @FXML
    private MenuButton userMenuButton;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    private MFXScrollPane scrollPane;


    @FXML
    private MFXButton viewProfileButton;

    @FXML
    private MFXButton courseEnrollmentButton;

    @FXML
    private MFXButton viewCourseDetailsButton;

    @FXML
    private MFXButton viewTuitionFeeButton;

    @FXML
    private MFXButton manageBillingButton;

    @FXML
    private MFXButton manageScholarshipsButton;

    @FXML
    private MFXButton newsFeedButton;

    @FXML
    private MFXButton postManagementButton;
    @FXML
    private MFXButton viewGradesButton;
    @FXML
    private MFXButton submitAssignmentButton;
    @FXML
    private MFXButton trackAcademicProgressButton;
    @FXML
    private MFXButton viewUpcomingEventsButton;
    @FXML
    private MFXButton resourceManagementButton;
    @FXML
    private MFXButton makeAppointmentButton;
    @FXML
    private MFXButton viewAnnouncementsButton;

    private String userId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        // Set up your button actions here using lambda expressions
        viewProfileButton.setOnAction(event -> handleViewProfileButtonAction());
        courseEnrollmentButton.setOnAction(event -> handleCourseEnrollmentButtonAction());
        viewCourseDetailsButton.setOnAction(event -> handleViewCourseDetailsButtonAction());
        viewTuitionFeeButton.setOnAction(event -> handleViewTuitionFeeButtonAction());
        manageBillingButton.setOnAction(event -> handleManageBillingButtonAction());
        manageScholarshipsButton.setOnAction(event -> handleManageScholarshipsButtonAction());
        newsFeedButton.setOnAction(event -> handleNewsFeedButtonAction());
        postManagementButton.setOnAction(event -> handlePostManagementButtonAction());
        viewGradesButton.setOnAction(event -> handleViewGradesButtonAction());
        submitAssignmentButton.setOnAction(event -> handleSubmitAssignmentButtonAction());
        trackAcademicProgressButton.setOnAction(event -> handleTrackAcademicProgressButtonAction());
        viewUpcomingEventsButton.setOnAction(event -> handleViewUpcomingEventsButtonAction());
        resourceManagementButton.setOnAction(event -> handleResourceManagementButtonAction());
        makeAppointmentButton.setOnAction(event -> handleMakeAppointmentButtonAction());
        viewAnnouncementsButton.setOnAction(event -> handleViewAnnouncementsButtonAction());

        // Set up the menu item action
        logoutMenuItem.setOnAction(event -> handleLogoutMenuItemAction());
        setStudentInfoInMenuItem(userId);

    }

    // Define your button action methods here
    private void handleViewProfileButtonAction() {
        try {
            Stage dashboardStage = (Stage) viewProfileButton.getScene().getWindow();
            dashboardStage.close();

            FXMLLoader manageLoader = new FXMLLoader(getClass().getResource("/fxml/ViewProfile.fxml"));
            Parent manageRoot = manageLoader.load();
            ViewProfileController viewProfileController = manageLoader.getController();

            // Pass the user ID
            viewProfileController.setStudentId(userId);

            // Get the profile picture path
            String profilePicturePath = "userData/" + userId + "/" + userId + ".jpg";
            viewProfileController.setProfilePicturePath(profilePicturePath);

            Scene manageScene = new Scene(manageRoot);

            Stage manageStage = new Stage();
            manageStage.initModality(Modality.WINDOW_MODAL);
            manageStage.initOwner(viewProfileButton.getScene().getWindow());
            manageStage.setScene(manageScene);
            manageStage.show();
            manageStage.setTitle("View Profile");



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    private void handleCourseEnrollmentButtonAction() {
        // Implement the action for course enrollment button
    }

    private void handleViewCourseDetailsButtonAction() {
        // Implement the action for view course details button
    }

    private void handleViewTuitionFeeButtonAction() {
        // Implement the action for view tuition fee button
    }

    private void handleManageBillingButtonAction() {
        // Implement the action for manage billing button
    }

    private void handleManageScholarshipsButtonAction() {
        // Implement the action for manage scholarships button
    }

    private void handleNewsFeedButtonAction() {
        // Implement the action for news feed button
    }

    private void handlePostManagementButtonAction() {
        // Implement the action for post management button
    }

    private void handleViewGradesButtonAction() {
        // Implement the action for view grades button
    }

    private void handleSubmitAssignmentButtonAction() {
        // Implement the action for submit assignment button
    }

    private void handleTrackAcademicProgressButtonAction() {
        // Implement the action for track academic progress button
    }

    private void handleViewUpcomingEventsButtonAction() {
        // Implement the action for view upcoming events button
    }

    private void handleResourceManagementButtonAction() {
        // Implement the action for resource management button
    }

    private void handleMakeAppointmentButtonAction() {
        // Implement the action for make appointment button
    }

    private void handleViewAnnouncementsButtonAction() {
        // Implement the action for view announcements button
    }

    @FXML
    private void handleLogoutMenuItemAction() {
        // Implement the action for logout menu item
    }

    void setStudentInfoInMenuItem(String userId) {
        File userDirectory = new File("userData/" + userId);

        if (userDirectory.exists() && userDirectory.isDirectory()) {
            File[] imageFiles = userDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

            if (imageFiles != null && imageFiles.length > 0) {
                // Sort the files by name in descending order to get the latest profile picture
                Arrays.sort(imageFiles, Comparator.comparing(File::getName));
                File latestProfilePicture = imageFiles[imageFiles.length - 1];

                try {
                    File userFile = new File(userDirectory, userId + ".txt");

                    try (BufferedReader userFileReader = new BufferedReader(new FileReader(userFile))) {
                        String line;
                        String studentName = null;

                        while ((line = userFileReader.readLine()) != null) {
                            if (line.startsWith("Username:")) {
                                studentName = line.substring(10).trim();
                                break;
                            }
                        }

                        if (studentName != null) {
                            String profilePicturePath = latestProfilePicture.getPath();

                            // Set the student name and profile picture in the menu item
                            userMenuButton.setText(studentName);
                            Image profileImage = new Image(new FileInputStream(profilePicturePath));
                            ImageView profileImageView = new ImageView(profileImage);

                            Circle clip = new Circle(25, 25, 25);
                            profileImageView.setClip(clip);
                            profileImageView.setFitWidth(50);
                            profileImageView.setFitHeight(50);
                            profileImageView.setLayoutX(0);
                            profileImageView.setLayoutY(0);
                            userMenuButton.setGraphic(profileImageView);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }





    public void setStudentId(String userId) {
        this.userId = userId;
        setStudentInfoInMenuItem(userId);
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

    public void handleNotificationCenterAction(MouseEvent mouseEvent) {
    }
}
