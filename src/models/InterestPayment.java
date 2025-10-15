package model;

import java.time.Instant;
import java.util.Objects;

public class InterestPayment {
    private int id;
    private String accountId;
    private double amount;
    private Instant paymentDate;

    public InterestPayment() { }

    public InterestPayment(int id, String accountId, double amount, Instant paymentDate) {
        this.id = id;
        this.accountId = Objects.requireNonNull(accountId);
        this.amount = amount;
        this.paymentDate = (paymentDate == null ? Instant.now() : paymentDate);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = Objects.requireNonNull(accountId); }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Instant getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Instant paymentDate) {
        this.paymentDate = (paymentDate == null ? Instant.now() : paymentDate);
    }
}
