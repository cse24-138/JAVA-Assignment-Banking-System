package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.function.BiConsumer;

public class LoginView {
    private final VBox root = new VBox(12);
    private BiConsumer<String,String> onLogin;
    private final TextField email = new TextField();
    private final PasswordField pass = new PasswordField();
    private final Label status = new Label();

    public LoginView() {
        Label title = new Label("Banking System — Login");
        email.setPromptText("Email");
        pass.setPromptText("Password");
        Button btn = new Button("Login");
        btn.setOnAction(e -> { if (onLogin != null) onLogin.accept(email.getText(), pass.getText()); });

        root.setPadding(new Insets(20));
        root.getChildren().addAll(title, email, pass, btn, status);
    }

    public Node getRoot(){ return root; }
    public void setOnLogin(BiConsumer<String,String> cb){ this.onLogin = cb; }
    public void showError(String msg){ status.setText(msg); }
}
