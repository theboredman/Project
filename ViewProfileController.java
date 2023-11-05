package controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXContextMenuItem;
import io.github.palexdev.materialfx.controls.MFXNotificationCenter;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;

import static jdk.jfr.internal.SecuritySupport.newFileInputStream;

public class ViewProfileController implements Initializable {
    public MFXButton updateProfileButton;
    @FXML
    private MFXContextMenuItem showUsername, showPhoneNumber, showID, showGender, showDateofBirth, showAddress, showMail, showDepartment, showTotalPassedCredit, showCGPA, showSemester,
            showGuardianName, showGuardianNumber, showGuardianMail, showGuardianRelation;


    @FXML
    private ImageView profileImageView, profileImageView2;

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
    private String profilePicturePath;

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

    public void displayUserData(String userId) throws FileNotFoundException {
        File userFile = new File("userData/" + userId + "/" + userId + ".txt");
        //String profilePicturePath = "userData/" + userId + "/" + userId + ".jpg";
        //  Image profileImage = new Image(new FileInputStream(profilePicturePath));
        //profileImageView.setImage(profileImage);

        if (userFile.exists()) {

            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String field = parts[0].trim();
                        String value = parts[1].trim();

                        String formattedData = field + ": " + value;

                        switch (field) {
                            case "ID":
                                showID.setText(formattedData);
                                break;
                            case "Username":
                                showUsername.setText(formattedData);
                                break;
                            case "Department":
                                showDepartment.setText(formattedData);
                                break;
                            case "Email":
                                showMail.setText(formattedData);
                                break;
                            case "Phone Number":
                                // Assuming you have a label named `showPhoneNumber`
                                showPhoneNumber.setText(formattedData);
                                break;
                            case "Passed Credit":
                                showTotalPassedCredit.setText(formattedData);
                                break;
                            case "CGPA":
                                showCGPA.setText(formattedData);
                                break;
                            case "Gender":
                                showGender.setText(formattedData);
                                break;
                            case "Relation":
                                showGuardianRelation.setText(formattedData);
                                break;
                            case "Address":
                                showAddress.setText(formattedData);
                                break;
                            case "Semester":
                                showSemester.setText(formattedData);
                                break;
                            case "Guardian Name":
                                showGuardianName.setText(formattedData);
                                break;
                            case "Guardian Phone Number":
                                showGuardianNumber.setText(formattedData);
                                break;
                            case "Guardian Mail":
                                showGuardianMail.setText(formattedData);
                                break;
                            case "Date of Birth":
                                showDateofBirth.setText(formattedData);
                                break;
                            // Add more cases for other fields as needed
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception (e.g., show an error message)
            }
        }
    }


    public void handleUpdateProfileButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UpdateProfile.fxml"));
            Parent root = loader.load();

            UpdateProfile controller = loader.getController();
            controller.setUserId(userId); // Set the user ID here

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleChangePasswordButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChangePassword.fxml"));
            Parent root = loader.load();

            ChangePassword controller = loader.getController();
            controller.setUserId(userId); // Pass the userId to ChangePassword controller

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleChangeProfilePictureButtonAction(ActionEvent event) {
        // Show a file chooser dialog to select a new profile picture
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try {
                // Save the selected profile picture as "new_userId.jpg"
                String newProfilePicturePath = "userData/" + userId + "/new_" + userId + ".jpg";
                Files.copy(selectedFile.toPath(), new File(newProfilePicturePath).toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Display alert box informing the user about the update
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Profile Picture Updated");
                alert.setHeaderText(null);
                alert.setContentText("Profile picture updated. Please log in again.");
                alert.showAndWait();

                // Take the user back to the login scene (replace this with your code to switch scenes)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StudentLogin.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception (e.g., show an error message)
            }
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

                            userMenuButton.setText(studentName);
                            ImageView profileImageView = getImageView(profilePicturePath);
                            userMenuButton.setGraphic(profileImageView);

                            // Set profileImageView to use the same image as profileImageView2
                            Image profileImage = new Image(new FileInputStream(profilePicturePath));
                            profileImageView2.setImage(profileImage);
                            // shape will be square with size 50x50
                            Rectangle rectangle = new Rectangle(250, 250 );
                            profileImageView2.setClip(rectangle);
                            profileImageView2.setFitWidth(400);
                            profileImageView2.setFitHeight(200);
                            profileImageView2.setLayoutX(0);
                            profileImageView2.setLayoutY(0);


                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static ImageView getImageView(String profilePicturePath) throws FileNotFoundException {
        Image profileImage = new Image(new FileInputStream(profilePicturePath));
        ImageView profileImageView = new ImageView(profileImage);

        Circle clip = new Circle(25, 25, 25);
        profileImageView.setClip(clip);
        profileImageView.setFitWidth(50);
        profileImageView.setFitHeight(50);
        profileImageView.setLayoutX(0);
        profileImageView.setLayoutY(0);
        return profileImageView;
    }


    public void setStudentId(String userId) throws FileNotFoundException {
        this.userId = userId;
        setStudentInfoInMenuItem(userId);
        displayUserData(userId); // Add this line
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
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

    public void handleDashboardButtonAction(ActionEvent event) {
    }


}
