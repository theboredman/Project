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
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoard implements Initializable {

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
    }

    @FXML
    private void handleNotificationCenterAction(ActionEvent event) {
        // Add your code to handle notification center action here
    }

    @FXML
    private void handleLogoutMenuItemAction(ActionEvent event) {
        // Add your code to handle logout menu item action here
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
            FXMLLoader manageLoader = new FXMLLoader(getClass().getResource("/fxml/RegistrationRequests.fxml"));
            Parent manageRoot = manageLoader.load();
            Scene manageScene = new Scene(manageRoot);

            Stage manageStage = new Stage();
            manageStage.initModality(Modality.WINDOW_MODAL);
            manageStage.initOwner(registrationRequestsButton.getScene().getWindow());
            manageStage.setScene(manageScene);
            manageStage.show();
            manageStage.setTitle("Manage Registration Requests");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleCourseManagementButtonAction(ActionEvent event) {
        try {
            FXMLLoader manageLoader = new FXMLLoader(getClass().getResource("/fxml/CourseManagement.fxml"));
            Parent manageRoot = manageLoader.load();
            Scene manageScene = new Scene(manageRoot);

            Stage manageStage = new Stage();
            manageStage.initModality(Modality.WINDOW_MODAL);
            manageStage.initOwner(registrationRequestsButton.getScene().getWindow());
            manageStage.setScene(manageScene);
            manageStage.show();
            manageStage.setTitle("Course Management");

        } catch (IOException ex) {
            ex.printStackTrace();
        }    }
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

    private void handleViewUserEngagementInsightsButtonAction(ActionEvent event) {
        // Define what happens when the view user engagement insights button is clicked
    }

    private void handleViewGradeDistributionsButtonAction(ActionEvent event) {
        // Define what happens when the view grade distributions button is clicked
    }

    private void handleViewPerformanceMetricsButtonAction(ActionEvent event) {
        // Define what happens when the view performance metrics button is clicked
    }

    private void handleEventManagementButtonAction(ActionEvent event) {
        // Define what happens when the add new event button is clicked
    }


    private void handleResourceManagementButtonAction(ActionEvent event) {
        // Define what happens when the view available resources button is clicked
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

}
