package model;

import java.time.LocalDateTime;

public class Transaction {
    public enum Type { DEPOSIT, WITHDRAW, INTEREST }

    private final long id;
    private final String accountNo;
    private final Type type;
    private final double amount;
    private final LocalDateTime ts;
    private final String note;

    public Transaction(long id, String accountNo, Type type, double amount, LocalDateTime ts, String note) {
        this.id=id; this.accountNo=accountNo; this.type=type; this.amount=amount; this.ts=ts; this.note=note;
    }
    public long getId(){ return id; }
    public String getAccountNo(){ return accountNo; }
    public Type getType(){ return type; }
    public double getAmount(){ return amount; }
    public LocalDateTime getTs(){ return ts; }
    public String getNote(){ return note; }
}
