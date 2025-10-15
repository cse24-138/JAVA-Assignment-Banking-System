package controller;

import dao.AccountDAO;
import dao.TransactionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class AccountController {
    @FXML private ComboBox<String> accountCombo;
    @FXML private Label balanceLabel;
    @FXML private TextField amountField;
    @FXML private TextArea transactionsArea;

    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO txDAO = new TransactionDAO();

    private static Customer currentCustomer;

    public static void setCurrentCustomer(Customer c) { currentCustomer = c; }

    @FXML
    public void initialize() {
        if (currentCustomer != null) {
            var accounts = accountDAO.findByCustomerId(currentCustomer.getId());
            accountCombo.getItems().setAll(accounts.stream()
                    .map(Account::getAccountNo).collect(Collectors.toList()));
            if (!accounts.isEmpty()) accountCombo.getSelectionModel().selectFirst();
            refresh();
        }
    }

    @FXML private void handleDeposit() {
        String acc = accountCombo.getValue();
        double amt = parse(amountField.getText());
        if (amt <= 0) return;
        accountDAO.findByAccountNo(acc).ifPresent(a -> {
            a.deposit(amt);
            accountDAO.update(a);
            txDAO.save(new Transaction(0, acc, Transaction.Type.DEPOSIT, amt, java.time.LocalDateTime.now(), "deposit"));
            refresh();
        });
    }

    @FXML private void handleWithdraw() {
        String acc = accountCombo.getValue();
        double amt = parse(amountField.getText());
        if (amt <= 0) return;
        accountDAO.findByAccountNo(acc).ifPresent(a -> {
            try {
                a.withdraw(amt);
                accountDAO.update(a);
                txDAO.save(new Transaction(0, acc, Transaction.Type.WITHDRAW, amt, java.time.LocalDateTime.now(), "withdraw"));
            } catch (Exception ignored) {}
            refresh();
        });
    }

    @FXML private void handleInterest() {
        String acc = accountCombo.getValue();
        accountDAO.findByAccountNo(acc).ifPresent(a -> {
            if (a instanceof InterestBearing ib) {
                double before = a.getBalance();
                ib.applyMonthlyInterest();
                accountDAO.update(a);
                double earned = a.getBalance() - before;
                txDAO.save(new Transaction(0, acc, Transaction.Type.INTEREST, earned, java.time.LocalDateTime.now(), "interest"));
                refresh();
            }
        });
    }

    @FXML private void logout() { SceneController.switchTo("LoginView.fxml"); }

    private void refresh() {
        String acc = accountCombo.getValue();
        if (acc == null) return;
        accountDAO.findByAccountNo(acc).ifPresent(a -> {
            balanceLabel.setText(String.format("Balance: %.2f", a.getBalance()));
            var txs = txDAO.findByAccountNo(acc);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            List<String> lines = txs.stream().map(t ->
                    String.format("[%s] %-8s %8.2f  %s", fmt.format(t.getTs()), t.getType(), t.getAmount(), t.getNote()))
                    .toList();
            transactionsArea.setText(String.join("\n", lines));
        });
    }

    private double parse(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return -1; }
    }
}
