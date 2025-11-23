package view;

import dao.DataStore;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Customer;

public class LoginView {

    public void start(Stage stage) {

        Label title = new Label("Welcome to Banking System");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill:#0A2A8C; -fx-font-weight:bold;");

        TextField username = new TextField();
        username.setPromptText("Username");
        username.setMaxWidth(250);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(250);

        Button login = new Button("Login");
        login.setStyle("-fx-background-color:#1A73E8; -fx-text-fill:white; -fx-font-size:16px;");

        Button register = new Button("Register");
        register.setStyle("-fx-background-color:green; -fx-text-fill:white; -fx-font-size:16px;");

        login.setOnAction(e -> {
            Customer c = DataStore.getCustomer(username.getText().trim());

            if (c == null) {
                new Alert(Alert.AlertType.ERROR, "User not found!").show();
                return;
            }

            if (!c.getPassword().equals(password.getText())) {
                new Alert(Alert.AlertType.ERROR, "Wrong password!").show();
                return;
            }

            new DashboardView().start(stage, c);
        });

        register.setOnAction(e -> new RegistrationView().start(stage));

        VBox layout = new VBox(20, title, username, password, register, login);
        layout.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(layout, 900, 600));
    }
}
