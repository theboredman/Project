package controllers;

import application.DataFileLoader;
import application.UserData;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ManageUserProfile implements Initializable {

    private static UserData userData;

    @FXML
    private MFXButton dashBoardButton;

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
    @FXML
    private MFXButton addButton;

    @FXML
    private MFXButton removeButton;

    @FXML
    private MFXTextField searchTextField;

    @FXML
    private MFXButton searchButton;


    @FXML
    private TableView<UserData> tableView;
    @FXML
    private TableColumn<UserData, String> idColumn;
    @FXML
    private TableColumn<UserData, String> userNameColumn;
    @FXML
    private TableColumn<UserData, String> departmentColumn;
    @FXML
    private TableColumn<UserData, String> emailColumn;
    @FXML
    private TableColumn<UserData, String> passwordColumn;
    @FXML
    private TableColumn<UserData, String> phoneColumn;
    @FXML
    private TableColumn<UserData, String> cgpaColumn;
    @FXML
    private TableColumn<UserData, String> passedCreditColumn;
    private ObservableList<UserData> userDataList = FXCollections.observableArrayList();



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
        tableView.setRowFactory(tv -> {
            TableRow<UserData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    UserData rowData = row.getItem();
                    tableView.getSelectionModel().select(rowData);
                }
            });
            return row;
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

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        cgpaColumn.setCellValueFactory(new PropertyValueFactory<>("cgpa"));
        passedCreditColumn.setCellValueFactory(new PropertyValueFactory<>("passedCredit"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        List<UserData> loadedData = DataFileLoader.loadUserDataFromFile("user_data.txt");

        userDataList.addAll(loadedData);

        tableView.setItems(userDataList);

    }

    @FXML
    private void handleAddUserButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddUserPromp.fxml"));
            Parent root = loader.load();

            AddUserPromp addUserPromp = loader.getController();
            addUserPromp.setManageUserProfile(ManageUserProfile.this);

            Stage popupStage = new Stage();
            Scene scene = new Scene(root);
            popupStage.setScene(scene);

            scene.getStylesheets().add(getClass().getResource("/css/AddUserPromp.css").toExternalForm());

            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Add User");

            popupStage.showAndWait();

            userDataList.add(addUserPromp.getUserData());
            tableView.setItems(userDataList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove the selected item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
        }
    }

    @FXML
    private void handleSearchButtonAction(ActionEvent event) {

    }

    @FXML
    private void handleNotificationCenterAction(ActionEvent event) {
        notificationCenter.setVisible(!notificationCenter.isVisible());


    }

    @FXML
    private void handleLogoutMenuItemAction(ActionEvent event) {
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
        }    }

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
        }    }

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

    private void handlePostManagementButtonAction(ActionEvent event) {
        // Define what happens when the edit course details button is clicked
    }

    private void handleEventManagementButtonAction(ActionEvent event) {
        // Define what happens when the remove course button is clicked
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

    private void handleResourceManagementButtonAction(ActionEvent event) {
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

    public ObservableList<UserData> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(ObservableList<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    public void addUserData(UserData userData) {
        userDataList.add(userData);
    }


}
