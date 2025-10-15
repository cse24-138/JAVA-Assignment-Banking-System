package model;

public class ChequeAccount extends Account {
    private final String employerName;
    private final String employerAddress;

    public ChequeAccount(String accountNo, String branch, double openingBalance,
                         long customerId, String employerName, String employerAddress) {
        super(accountNo, branch, openingBalance, customerId);
        if (employerName == null || employerName.isBlank()) throw new IllegalArgumentException("employer required");
        if (employerAddress == null || employerAddress.isBlank()) throw new IllegalArgumentException("employer address required");
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }
    @Override public void withdraw(double amount) {
        if (amount <= 0 || amount > getBalance()) throw new IllegalArgumentException("invalid amount");
        setBalance(getBalance() - amount);
    }
    public String getEmployerName(){ return employerName; }
    public String getEmployerAddress(){ return employerAddress; }
}
