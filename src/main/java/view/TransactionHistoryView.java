package view;

import dao.DataStore;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Customer;
import model.Transaction;

public class TransactionHistoryView {

    public void start(Stage stage, Customer customer) {

        Label title = new Label("Transaction History");
        title.setStyle("-fx-font-size: 26px; -fx-text-fill:#0A2A8C; -fx-font-weight:bold;");

        ListView<String> list = new ListView<>();

        for (Transaction t : customer.getTransactions()) {
            list.getItems().add(t.toString());
        }

        Button back = new Button("Back");
        back.setStyle("-fx-background-color:#1A73E8; -fx-text-fill:white;");
        back.setOnAction(e -> new DashboardView().start(stage, customer));

        VBox layout = new VBox(15, title, list, back);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25));
        layout.setStyle("-fx-background-color:#EEF2FF;");

        stage.setScene(new Scene(layout, 900, 600));
    }
}
