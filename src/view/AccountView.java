package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AccountView {
    private final VBox root = new VBox(10);
    private final ComboBox<String> accounts = new ComboBox<>();
    private final Label balanceLbl = new Label("Balance: 0.00");
    private final TextArea txArea = new TextArea();

    private Consumer<String> onSelect;
    private BiConsumer<String, Double> onDeposit;
    private BiConsumer<String, Double> onWithdraw;
    private Consumer<String> onApplyInterest;

    public AccountView() {
        Label title = new Label("Accounts");

        TextField amount = new TextField(); amount.setPromptText("Amount");
        Button dep = new Button("Deposit");
        Button wit = new Button("Withdraw");
        Button interest = new Button("Apply Interest");

        dep.setOnAction(e -> { if (onDeposit!=null) onDeposit.accept(accounts.getValue(), parse(amount.getText())); });
        wit.setOnAction(e -> { if (onWithdraw!=null) onWithdraw.accept(accounts.getValue(), parse(amount.getText())); });
        interest.setOnAction(e -> { if (onApplyInterest!=null) onApplyInterest.accept(accounts.getValue()); });

        accounts.setPromptText("Select account");
        accounts.setOnAction(e -> { if (onSelect!=null) onSelect.accept(accounts.getValue()); });

        txArea.setEditable(false); txArea.setPrefRowCount(10);

        HBox row = new HBox(8, amount, dep, wit, interest);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(title, accounts, balanceLbl, row, new Label("Transactions:"), txArea);
    }

    private double parse(String s){ try { return Double.parseDouble(s); } catch(Exception ex){ return -1; } }

    public void setAccounts(List<String> list){ accounts.getItems().setAll(list); if(!list.isEmpty()) accounts.getSelectionModel().selectFirst(); }
    public void setBalance(double b){ balanceLbl.setText(String.format("Balance: %.2f", b)); }
    public void setTransactions(List<String> lines){ txArea.setText(String.join("\n", lines)); }

    public Node getRoot(){ return root; }
    public void onSelect(Consumer<String> cb){ this.onSelect = cb; }
    public void onDeposit(BiConsumer<String,Double> cb){ this.onDeposit = cb; }
    public void onWithdraw(BiConsumer<String,Double> cb){ this.onWithdraw = cb; }
    public void onApplyInterest(Consumer<String> cb){ this.onApplyInterest = cb; }
}
