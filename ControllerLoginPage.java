package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLoginPage implements Initializable {

    @FXML
    private VBox rightVBox;


    @FXML
    private Button adminLoginButton;

    @FXML
    private Button studentLoginButton;

    @FXML
    private Button facultyLoginButton;

    @FXML
    private Hyperlink registerLink;

    @FXML
    private Label titleLabel;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label developerLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize any components or add event handlers here
        if (rightVBox != null) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(javafx.scene.paint.Color.BLACK);
            dropShadow.setHeight(20);
            dropShadow.setWidth(20);
            dropShadow.setRadius(20);
            rightVBox.setEffect(dropShadow);
        }
    }


    @FXML
    private void handleAdminLoginButton(ActionEvent event) {
        try {
            // Load the FXML file for the admin login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminLogin.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) adminLoginButton.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any potential exceptions
        }
    }


    @FXML
    private void handleStudentLoginButton(ActionEvent event) {
        try {
            // Load the FXML file for the admin login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StudentLogin.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) studentLoginButton.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any potential exceptions
        }
    }

    @FXML
    private void handleFacultyLoginButton(ActionEvent event) {
        try {
            // Load the FXML file for the admin login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FacultyLogin.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the stage information
            Stage stage = (Stage) facultyLoginButton.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Handle any potential exceptions
        }
    }

    @FXML
    private void handleRegisterLink(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Registration");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
