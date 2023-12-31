package controllers;

import application.InvalidInputException;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class UpdateProfile {

    @FXML
    private MFXTextField genderTextField;

    @FXML
    private MFXTextField relationField;

    @FXML
    private MFXTextField addressField;

    @FXML
    private MFXTextField semesterField;

    @FXML
    private MFXTextField guardianNameField;

    @FXML
    private MFXTextField guardianNumberField;

    @FXML
    private MFXTextField guardianMailField;

    @FXML
    private MFXButton addButton;

    @FXML
    private MFXButton cancelButton;

    @FXML
    private MFXDatePicker datePicker;

    @FXML
    private Label titleLabel;

    private String userId;

    @FXML
    void handleAddButtonAction(ActionEvent event) {

        try {
            validateInput();
            // Check if user ID is not null or empty
            if (userId != null && !userId.isEmpty()) {
                try {
                    // Create the directory if it doesn't exist
                    File userDirectory = new File("userData/" + userId);
                    if (!userDirectory.exists()) {
                        userDirectory.mkdirs();
                    }

                    // Create the file if it doesn't exist
                    File userFile = new File(userDirectory, userId + ".txt");
                    if (!userFile.exists()) {
                        userFile.createNewFile();
                    }

                    // Read existing data
                    StringBuilder existingData = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            existingData.append(line).append("\n");
                        }
                    }

                    // Append or update data
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
                        writer.write(existingData.toString()); // Write back the existing data

                        writer.write("Gender: " + genderTextField.getText() + "\n");
                        writer.write("Relation: " + relationField.getText() + "\n");
                        writer.write("Address: " + addressField.getText() + "\n");
                        writer.write("Semester: " + semesterField.getText() + "\n");
                        writer.write("Guardian Name: " + guardianNameField.getText() + "\n");
                        writer.write("Guardian Phone Number: " + guardianNumberField.getText() + "\n");
                        writer.write("Guardian Mail: " + guardianMailField.getText() + "\n");
                        writer.write("Date of Birth: " + datePicker.getText() + "\n");

                        // Add more fields as needed
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle the exception (e.g., show an error message)
                }
            } else {
                // Handle the case where the user ID is not available
            }

            closeStage(event);
        } catch (InvalidInputException e) {
            // Show an error message for invalid input
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    private void validateInput() throws InvalidInputException {
        String name = guardianNameField.getText();
        String email = guardianMailField.getText();
        String gender = genderTextField.getText();
        String semester = semesterField.getText();
        String guardianNumber = guardianNumberField.getText();

        if (!isValidName(name)) {
            throw new InvalidInputException("Name is invalid. It should only contain letters and spaces.");
        }

        if (!isValidEmail(email)) {
            throw new InvalidInputException("Email is invalid.");
        }

        if (!isValidGender(gender)) {
            throw new InvalidInputException("Gender is invalid. It should be 'male' or 'female'.");
        }

        if (!isValidSemester(semester)) {
            throw new InvalidInputException("Semester is invalid. It should be a number less than or equal to 20.");
        }

        if (!isValidGuardianNumber(guardianNumber)) {
            throw new InvalidInputException("Guardian number is invalid. It should be 11 digits.");
        }
    }

// ... other methods ...

    private boolean isValidName(String name) {
        return name.matches("^[a-zA-Z\\s]+$");
    }

    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female");
    }

    private boolean isValidSemester(String semester) {
        try {
            int value = Integer.parseInt(semester);
            return value >= 1 && value <= 20;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidGuardianNumber(String guardianNumber) {
        return guardianNumber.matches("\\d{11}");
    }

    @FXML
    void handleCancelButtonAction(ActionEvent event) {
        closeStage(event);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private void closeStage(ActionEvent event) {
        Stage stage = (Stage) ((MFXButton) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
