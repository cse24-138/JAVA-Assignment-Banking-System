package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime time;
    private String type;
    private double amount;
    private String accountNumber;

    public Transaction(LocalDateTime time, String type, double amount, String accountNumber) {
        this.time = time;
        this.type = type;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                " | " + type +
                " | BWP " + amount +
                " | Acc: " + accountNumber;
    }
}
