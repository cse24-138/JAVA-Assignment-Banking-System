package model;

public class ChequeAccount extends Account {

    private int overdraftLimit;

    public ChequeAccount(String accountNumber, double balance, String branch, int overdraftLimit) {
        super(accountNumber, balance, branch);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void payMonthlyInterest() {
        // Cheque account has no interest
    }
}
