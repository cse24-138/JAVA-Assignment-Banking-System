package model;

public class InvestmentAccount extends Account {

    public InvestmentAccount(String accountNumber, double balance, String branch) {
        super(accountNumber, balance, branch);
    }

    @Override
    public void payMonthlyInterest() {
        balance += balance * 0.05; // 5% interest
    }
}
