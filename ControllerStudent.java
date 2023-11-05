package controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ControllerStudent {

    @FXML
    private TextField studentIDField;

    @FXML
    private TextField studentPasswordField;

    @FXML
    private Button studentLoginButton;

    @FXML
    private Button studentBackButton;
    @FXML
    private Hyperlink registerLink;

    private Stage loginStage;


    @FXML
    private void handleBackButton(ActionEvent event) throws IOException {
        // Load the Login Panel scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String input = studentIDField.getText();
        String password = studentPasswordField.getText();

        try (BufferedReader br = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            boolean loginSuccessful = false;

            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");

                if (userData.length == 8) {
                    String storedID = userData[0].trim();
                    String storedPassword = userData[7].trim();
                    String storedPhoneNumber = userData[4].trim();

                    if ((input.equals(storedID) || input.equals(storedPhoneNumber)) && password.equals(storedPassword)) {
                        loginSuccessful = true;
                        break;
                    }
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }

            if (loginSuccessful) {
                // Close the login scene
                Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                loginStage.close();

                // Show the loading screen and progress
                showLoadingScreen();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Wrong username or password.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showLoadingScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoadingScreen.fxml"));
            Parent root = loader.load();
            LoadingScreenController loadingController = loader.getController();

            loadingController.setUserId(getUserId());

            Scene scene = new Scene(root);

            // Assuming loadingScene is the name of your Stage in LoadingScreen.fxml
            Stage loadingStage = new Stage();
            loadingStage.initModality(Modality.WINDOW_MODAL);
            loadingStage.setScene(scene);
            loadingStage.show();

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i <= 100; i++) {
                        final int progress = i;
                        Platform.runLater(() -> loadingController.getProgressBar().setProgress(progress / 100.0));
                        Thread.sleep(30);
                    }
                    return null;
                }
            };

            task.setOnSucceeded(e -> {
                try {
                    showDashboard();
                    loadingStage.close(); // Close the loading window
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            new Thread(task).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDashboard() throws IOException {
        FXMLLoader manageLoader = new FXMLLoader(getClass().getResource("/fxml/StudentDashboard.fxml"));
        Parent manageRoot = manageLoader.load();
        Scene manageScene = new Scene(manageRoot);

        StudentDashboardController dashboardController = manageLoader.getController();
        dashboardController.setStudentId(studentIDField.getText());

        // Assuming dashboardStage is the name of your Stage in StudentDashboard.fxml
        Stage dashboardStage = new Stage();
        dashboardStage.setScene(manageScene);
        dashboardStage.show();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
        private void handleRegisterLink (ActionEvent event){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Use your given phone number instead.");

            alert.showAndWait();
        }
    public String getUserId() {
        return studentIDField.getText();
    }



}
