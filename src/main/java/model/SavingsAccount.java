package model;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountNumber, double balance, String branch) {
        super(accountNumber, balance, branch);
    }

    @Override
    public void payMonthlyInterest() {
        balance += balance * 0.02; // 2% interest
    }
}
