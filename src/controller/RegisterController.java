package controller;

import dao.CustomerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Customer;
import util.HashUtil;

public class RegisterController {
    @FXML private TextField firstNameField, lastNameField, addressField, emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private final CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    private void handleRegister() {
        try {
            Customer c = new Customer(
                    0,
                    firstNameField.getText(),
                    lastNameField.getText(),
                    addressField.getText(),
                    emailField.getText(),
                    HashUtil.sha256(passwordField.getText())
            );
            customerDAO.save(c);
            statusLabel.setText("Account created! You can now login.");
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void goToLogin() {
        SceneController.switchTo("LoginView.fxml");
    }
}
