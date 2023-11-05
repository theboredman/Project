package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerFaculty  {

    @FXML
    private TextField facultyUsernameField;

    @FXML
    private TextField facultyPasswordField;

    @FXML
    private Button facultyLoginButton;

    @FXML
    private Button facultyBackButton;

    @FXML
    private Hyperlink registerLink;
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
    private void handleLoginButton(ActionEvent event) {
        String username = facultyUsernameField.getText();
        String password = facultyPasswordField.getText();
        // Add your logic for faculty login here
    }

    @FXML
    private void handleRegisterLink(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Use your given phone number instead.");

        alert.showAndWait();
    }

    

}
