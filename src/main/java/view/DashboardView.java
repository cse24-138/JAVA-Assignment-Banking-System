package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import model.Customer;

public class DashboardView {

    private Customer customer;

    public void start(Stage stage, Customer customer) {
        this.customer = customer;

        String username = customer.getFirstName();

        // TITLE
        Label welcome = new Label("Welcome, " + username);
        welcome.setFont(Font.font("Arial", 28));
        welcome.setTextFill(Color.web("#0A2A8C"));

        // BUTTONS
        Button btnAccounts = createBlueButton("View Accounts");
        Button btnDeposit  = createBlueButton("Deposit Money");
        Button btnWithdraw = createBlueButton("Withdraw Money");
        Button btnInterest = createBlueButton("Apply Monthly Interest");
        Button btnHistory  = createBlueButton("Transaction History");

        Button btnLogout = new Button("Logout");
        btnLogout.setPrefWidth(250);
        btnLogout.setStyle(
                "-fx-background-color: #D64541;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-background-radius: 10;"
        );

        // ACTIONS
        btnAccounts.setOnAction(e -> new ViewAccountsView().start(stage, customer));
        btnDeposit.setOnAction(e -> new DepositView().start(stage, customer));
        btnWithdraw.setOnAction(e -> new WithdrawView().start(stage, customer));
        btnInterest.setOnAction(e -> new InterestView().start(stage, customer));
        btnHistory.setOnAction(e -> new TransactionHistoryView().start(stage, customer));

        btnLogout.setOnAction(e -> new LoginView().start(stage));

        // LAYOUT
        VBox card = new VBox(20,
                welcome,
                btnAccounts,
                btnDeposit,
                btnWithdraw,
                btnInterest,
                btnHistory,
                btnLogout
        );

        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(400);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12;");
        card.setEffect(new DropShadow(18, Color.gray(0.4)));

        StackPane root = new StackPane(card);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #EEF2FF;");

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Banking Dashboard");
        stage.show();
    }

    private Button createBlueButton(String text) {
        Button btn = new Button(text);
        btn.setPrefWidth(250);
        btn.setStyle(
                "-fx-background-color: #1A73E8;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-background-radius: 10;"
        );
        return btn;
    }
}
