package controller;

import dao.CustomerDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Customer;
import util.HashUtil;

import java.util.Optional;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;

    private final CustomerDAO customerDAO = new CustomerDAO();

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isBlank() || password.isBlank()) {
            statusLabel.setText("Enter email and password");
            return;
        }

        Optional<Customer> user = customerDAO.findByEmail(email)
                .filter(c -> c.getPasswordHash().equals(HashUtil.sha256(password)));

        if (user.isPresent()) {
            AccountController.setCurrentCustomer(user.get());
            SceneController.switchTo("AccountView.fxml");
        } else {
            statusLabel.setText("Invalid credentials");
        }
    }

    @FXML
    private void goToRegister() {
        SceneController.switchTo("RegisterView.fxml");
    }
}
