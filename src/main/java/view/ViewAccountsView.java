package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Customer;
import model.Account;

public class ViewAccountsView {

    public void start(Stage stage, Customer customer) {

        Label title = new Label("Your Accounts");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: #0a3d62; -fx-font-weight: bold;");

        ListView<String> list = new ListView<>();

        // Show all accounts
        for (Account account : customer.getAccounts()) {
            String text =
                    "Account Number: " + account.getAccountNumber() +
                            "\nType: " + account.getClass().getSimpleName() +
                            "\nBalance: BWP " + account.getBalance() +
                            "\nBranch: " + account.getBranch() +
                            "\n-------------------------------------------";

            list.getItems().add(text);
        }

        // Back button
        Button btnBack = new Button("Back");
        btnBack.setStyle(
                "-fx-background-color:#1A73E8;" +      // BLUE like your dashboard
                        "-fx-text-fill:white;" +
                        "-fx-font-size:16px;" +
                        "-fx-background-radius:8;"
        );

        btnBack.setOnAction(e -> new DashboardView().start(stage, customer));

        // Layout
        VBox layout = new VBox(20, title, list, btnBack);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #EEF2FF;"); // Matches Login + Dashboard

        Scene scene = new Scene(layout, 900, 600);
        stage.setScene(scene);
    }
}
