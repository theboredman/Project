package controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

public class ChangePassword {

    @FXML
    private MFXTextField currentPasswordField;

    @FXML
    private MFXTextField newPasswordField;

    @FXML
    private MFXTextField rewriteNewPasswordField;

    @FXML
    private MFXButton saveButton;

    @FXML
    private MFXButton cancelButton;
    private String userId;

    @FXML
    void handleSaveButtonAction(ActionEvent event) {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String rewriteNewPassword = rewriteNewPasswordField.getText();

        if (!currentPassword.isEmpty() && !newPassword.isEmpty() && !rewriteNewPassword.isEmpty()) {
            if (newPassword.equals(rewriteNewPassword)) {
                try {
                    // Update user-specific file
                    File userFile = new File("userData/" + userId + "/" + userId + ".txt");

                    if (userFile.exists()) {
                        // Read existing data
                        StringBuilder existingData = new StringBuilder();
                        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                existingData.append(line).append("\n");
                            }
                        }

                        // Update password
                        String updatedData = existingData.toString().replaceAll("Password: .*", "Password: " + newPassword);

                        // Write back the updated data
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
                            writer.write(updatedData);
                        }

                        // Show success message for user-specific file
                        showAlert(Alert.AlertType.INFORMATION, "Password Changed", "Your password has been successfully changed.");

                        // Update general user data file
                        File generalUserFile = new File("user_data.txt");

                        if (generalUserFile.exists()) {
                            // Read existing data
                            StringBuilder generalExistingData = new StringBuilder();
                            try (BufferedReader reader = new BufferedReader(new FileReader(generalUserFile))) {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    String[] parts = line.split(",");
                                    if (parts.length >= 8 && parts[0].equals(userId)) {
                                        // Update password
                                        parts[7] = newPassword;
                                    }
                                    generalExistingData.append(String.join(",", parts)).append("\n");
                                }
                            }

                            // Write back the updated data
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(generalUserFile))) {
                                writer.write(generalExistingData.toString());
                            }


                            // Show success message for general user data file
                            showAlert(Alert.AlertType.INFORMATION, "Password Changed", "Your password has been successfully changed in general user data.");
                            closeStage(event);

                        } else {
                            showAlert(Alert.AlertType.ERROR, "General File Not Found", "General user data file not found.");
                        }
                    } else {
                        showAlert(Alert.AlertType.ERROR, "File Not Found", "User data file not found.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception (e.g., show an error message)
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Password Mismatch", "New passwords do not match. Please try again.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Empty Fields", "Please fill in all the fields.");
        }
    }


    @FXML
    void handleCancelButtonAction(ActionEvent event) {
        closeStage(event);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) ((MFXButton) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
