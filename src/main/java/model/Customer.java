package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String password;

    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Customer(String username, String firstName, String lastName, String address, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
    }

    // ===== GETTERS =====
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getPassword() { return password; }
    public ArrayList<Account> getAccounts() { return accounts; }
    public ArrayList<Transaction> getTransactions() { return transactions; }

    // ===== ACCOUNT MANAGEMENT =====
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // ===== TRANSACTION MANAGEMENT =====
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }
}
