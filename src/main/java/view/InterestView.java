package view;

import dao.DataStore;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import model.Customer;
import model.Account;
import model.Transaction;

import java.time.LocalDateTime;

public class InterestView {

    public void start(Stage stage, Customer customer) {

        for (Account acc : customer.getAccounts()) {
            double before = acc.getBalance();
            acc.payMonthlyInterest();
            double after = acc.getBalance();

            double interest = after - before;

            if (interest > 0) {
                customer.addTransaction(new Transaction(
                        LocalDateTime.now(), "Interest", interest, acc.getAccountNumber()
                ));
            }
        }

        DataStore.saveCustomer(customer);

        new Alert(Alert.AlertType.INFORMATION,
                "Monthly interest applied successfully!"
        ).show();

        new DashboardView().start(stage, customer);
    }
}
