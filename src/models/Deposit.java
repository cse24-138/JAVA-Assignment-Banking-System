package model;

import java.time.Instant;
import java.util.Objects;

public class Deposit {
    private int id;
    private String accountId;
    private double amount;
    private Instant depositDate;

    public Deposit() { }

    public Deposit(int id, String accountId, double amount, Instant depositDate) {
        this.id = id;
        this.accountId = Objects.requireNonNull(accountId);
        this.amount = amount;
        this.depositDate = (depositDate == null ? Instant.now() : depositDate);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = Objects.requireNonNull(accountId); }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Instant getDepositDate() { return depositDate; }
    public void setDepositDate(Instant depositDate) {
        this.depositDate = (depositDate == null ? Instant.now() : depositDate);
    }
}
