package view;

import dao.DataStore;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ChequeAccount;
import model.Customer;
import model.InvestmentAccount;
import model.SavingsAccount;

public class RegistrationView {

    public void start(Stage stage) {

        Label title = new Label("Create New Account");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill:#0A2A8C; -fx-font-weight:bold;");

        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Create username");
        txtUsername.setMaxWidth(300);

        TextField txtFirst = new TextField();
        txtFirst.setPromptText("First name");
        txtFirst.setMaxWidth(300);

        TextField txtLast = new TextField();
        txtLast.setPromptText("Last name");
        txtLast.setMaxWidth(300);

        TextField txtAddress = new TextField();
        txtAddress.setPromptText("Address");
        txtAddress.setMaxWidth(300);

        PasswordField txtPass = new PasswordField();
        txtPass.setPromptText("Create password");
        txtPass.setMaxWidth(300);

        PasswordField txtConfirm = new PasswordField();
        txtConfirm.setPromptText("Confirm password");
        txtConfirm.setMaxWidth(300);

        Button btnCreate = new Button("Register");
        btnCreate.setStyle("-fx-background-color:green; -fx-text-fill:white; -fx-font-size:16px;");

        Button btnBack = new Button("Back to Login");
        btnBack.setStyle("-fx-background-color:#B71C1C; -fx-text-fill:white; -fx-font-size:16px;");

        btnCreate.setOnAction(e -> {

            // VALIDATION
            if (txtUsername.getText().isEmpty() ||
                    txtFirst.getText().isEmpty() ||
                    txtLast.getText().isEmpty() ||
                    txtAddress.getText().isEmpty() ||
                    txtPass.getText().isEmpty()) {

                new Alert(Alert.AlertType.ERROR, "All fields required").show();
                return;
            }

            if (!txtPass.getText().equals(txtConfirm.getText())) {
                new Alert(Alert.AlertType.ERROR, "Passwords do not match").show();
                return;
            }

            // CREATE CUSTOMER
            Customer customer = new Customer(
                    txtUsername.getText(),
                    txtFirst.getText(),
                    txtLast.getText(),
                    txtAddress.getText(),
                    txtPass.getText()
            );

            // ==== DEFAULT ACCOUNTS (CORRECT CONSTRUCTORS) ====
            customer.addAccount(new SavingsAccount("SAV123", 0.0, "Main Branch"));
            customer.addAccount(new ChequeAccount("CHQ123", 0.0, "Main Branch", 500));  // overdraft is int
            customer.addAccount(new InvestmentAccount("INV123", 0.0, "Main Branch"));

            // SAVE CUSTOMER
            DataStore.saveCustomer(customer);

            new Alert(Alert.AlertType.INFORMATION, "Account Created Successfully!").show();
            new LoginView().start(stage);
        });

        btnBack.setOnAction(e -> new LoginView().start(stage));

        VBox layout = new VBox(15, title, txtUsername, txtFirst, txtLast, txtAddress, txtPass, txtConfirm, btnCreate, btnBack);
        layout.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(layout, 900, 600));
    }
}
