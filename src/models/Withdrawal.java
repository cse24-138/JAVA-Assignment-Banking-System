package model;

import java.time.Instant;
import java.util.Objects;

public class Withdrawal {
    private int id;
    private String accountId;
    private double amount;
    private Instant withdrawalDate;

    public Withdrawal() { }

    public Withdrawal(int id, String accountId, double amount, Instant withdrawalDate) {
        this.id = id;
        this.accountId = Objects.requireNonNull(accountId);
        this.amount = amount;
        this.withdrawalDate = (withdrawalDate == null ? Instant.now() : withdrawalDate);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = Objects.requireNonNull(accountId); }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Instant getWithdrawalDate() { return withdrawalDate; }
    public void setWithdrawalDate(Instant withdrawalDate) {
        this.withdrawalDate = (withdrawalDate == null ? Instant.now() : withdrawalDate);
    }
}
