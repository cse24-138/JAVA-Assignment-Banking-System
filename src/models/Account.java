package model;

public abstract class Account {
    private final String accountNo;
    private final String branch;
    private double balance;
    private final long customerId;

    protected Account(String accountNo, String branch, double openingBalance, long customerId) {
        if (accountNo == null || accountNo.isBlank()) throw new IllegalArgumentException("accountNo required");
        if (branch == null || branch.isBlank()) throw new IllegalArgumentException("branch required");
        if (openingBalance < 0) throw new IllegalArgumentException("opening >= 0");
        this.accountNo = accountNo;
        this.branch = branch;
        this.balance = openingBalance;
        this.customerId = customerId;
    }

    public void deposit(double amount){
        if (amount <= 0) throw new IllegalArgumentException("amount > 0");
        balance += amount;
    }
    public void withdraw(double amount){
        throw new UnsupportedOperationException("Withdraw not allowed for this account type");
    }

    public String getAccountNo(){ return accountNo; }
    public String getBranch(){ return branch; }
    public double getBalance(){ return balance; }
    protected void setBalance(double b){ balance = b; }
    public long getCustomerId(){ return customerId; }

    @Override public String toString(){ return getClass().getSimpleName()+"{"+accountNo+", bal="+balance+"}"; }
}
