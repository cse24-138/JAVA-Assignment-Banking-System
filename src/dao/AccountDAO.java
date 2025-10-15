package dao;

import model.*;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO {
    public Account save(Account a) {
        String sql = """
            INSERT INTO accounts(account_no,branch,balance,type,customer_id,employer_name,employer_address)
            VALUES(?,?,?,?,?,?,?)
        """;
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getAccountNo());
            ps.setString(2, a.getBranch());
            ps.setDouble(3, a.getBalance());
            ps.setString(4, a.getClass().getSimpleName());
            ps.setLong(5, a.getCustomerId());
            if (a instanceof ChequeAccount ch) {
                ps.setString(6, ch.getEmployerName());
                ps.setString(7, ch.getEmployerAddress());
            } else {
                ps.setNull(6, Types.VARCHAR);
                ps.setNull(7, Types.VARCHAR);
            }
            ps.executeUpdate();
            return a;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public Optional<Account> findByAccountNo(String accountNo) {
        String sql = "SELECT * FROM accounts WHERE account_no=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return Optional.empty();
            String type = rs.getString("type");
            Account a = switch (type) {
                case "SavingsAccount" -> new SavingsAccount(
                        rs.getString("account_no"), rs.getString("branch"), rs.getDouble("balance"), rs.getLong("customer_id"));
                case "InvestmentAccount" -> new InvestmentAccount(
                        rs.getString("account_no"), rs.getString("branch"), rs.getDouble("balance"), rs.getLong("customer_id"));
                case "ChequeAccount" -> new ChequeAccount(
                        rs.getString("account_no"), rs.getString("branch"), rs.getDouble("balance"), rs.getLong("customer_id"),
                        rs.getString("employer_name"), rs.getString("employer_address"));
                default -> null;
            };
            return Optional.ofNullable(a);
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<Account> findByCustomerId(long customerId) {
        List<Account> out = new ArrayList<>();
        String sql = "SELECT account_no FROM accounts WHERE customer_id=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) findByAccountNo(rs.getString(1)).ifPresent(out::add);
        } catch (SQLException e) { throw new RuntimeException(e); }
        return out;
    }

    public void update(Account a) {
        String sql = "UPDATE accounts SET balance=? WHERE account_no=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, a.getBalance());
            ps.setString(2, a.getAccountNo());
            ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}
