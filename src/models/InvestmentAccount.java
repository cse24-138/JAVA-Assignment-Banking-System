package model;

public class InvestmentAccount extends Account implements InterestBearing {
    public static final double MIN_OPENING = 500.0;
    public InvestmentAccount(String accountNo, String branch, double openingBalance, long customerId) {
        super(accountNo, branch, openingBalance, customerId);
        if (openingBalance < MIN_OPENING) throw new IllegalArgumentException("Min opening is BWP 500");
    }
    @Override public void applyMonthlyInterest() { setBalance(getBalance() * 1.05); } // 5%
    @Override public void withdraw(double amount) {
        if (amount <= 0 || amount > getBalance()) throw new IllegalArgumentException("invalid amount");
        setBalance(getBalance() - amount);
    }
}
