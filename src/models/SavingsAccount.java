package model;

public class SavingsAccount extends Account implements InterestBearing {
    public SavingsAccount(String accountNo, String branch, double openingBalance, long customerId) {
        super(accountNo, branch, openingBalance, customerId);
    }
    @Override public void applyMonthlyInterest() { setBalance(getBalance() * 1.0005); } // 0.05%
    @Override public void withdraw(double amount) {
        throw new UnsupportedOperationException("Withdrawals not permitted on SavingsAccount");
    }
}
