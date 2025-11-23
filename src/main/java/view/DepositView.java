package view;

import dao.DataStore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Customer;
import model.Account;
import model.Transaction;

import java.time.LocalDateTime;

public class DepositView {

    public void start(Stage stage, Customer customer) {

        Label title = new Label("Deposit Money");
        title.setStyle("-fx-font-size: 26px; -fx-text-fill:#0A2A8C; -fx-font-weight:bold;");

        ComboBox<Account> accountBox = new ComboBox<>();
        accountBox.getItems().addAll(customer.getAccounts());
        accountBox.setPromptText("Select Account");
        accountBox.setMaxWidth(200);

        TextField txtAmount = new TextField();
        txtAmount.setPromptText("Enter amount");
        txtAmount.setMaxWidth(200);

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setStyle("-fx-background-color:#1A73E8; -fx-text-fill:white; -fx-font-size:15px;");
        btnDeposit.setPrefWidth(150);

        btnDeposit.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(txtAmount.getText());
                Account acc = accountBox.getValue();

                if (acc == null || amount <= 0) throw new Exception();

                acc.deposit(amount);

                customer.addTransaction(new Transaction(
                        LocalDateTime.now(), "Deposit", amount, acc.getAccountNumber()
                ));

                DataStore.saveCustomer(customer);

                new Alert(Alert.AlertType.INFORMATION,
                        "Deposited BWP " + amount +
                                "\nNew Balance: " + acc.getBalance()
                ).show();

            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR,
                        "Enter valid amount and select an account!"
                ).show();
            }
        });

        Button back = new Button("Back");
        back.setStyle("-fx-background-color:#c0392b; -fx-text-fill:white;");
        back.setOnAction(e -> new DashboardView().start(stage, customer));

        VBox root = new VBox(15, title, accountBox, txtAmount, btnDeposit, back);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color:#EEF2FF;");

        stage.setScene(new Scene(root, 900, 600));
    }
}
