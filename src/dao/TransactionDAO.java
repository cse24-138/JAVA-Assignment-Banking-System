package dao;

import model.Transaction;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public Transaction save(Transaction t) {
        String sql = "INSERT INTO transactions(account_no,type,amount,ts,note) VALUES(?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getAccountNo());
            ps.setString(2, t.getType().name());
            ps.setDouble(3, t.getAmount());
            ps.setTimestamp(4, Timestamp.valueOf(t.getTs()));
            ps.setString(5, t.getNote());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return new Transaction(rs.getLong(1), t.getAccountNo(), t.getType(), t.getAmount(), t.getTs(), t.getNote());
            }
            return t;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public List<Transaction> findByAccountNo(String accountNo) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_no=? ORDER BY ts DESC";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accountNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Transaction(
                    rs.getLong("id"),
                    rs.getString("account_no"),
                    Transaction.Type.valueOf(rs.getString("type")),
                    rs.getDouble("amount"),
                    rs.getTimestamp("ts").toLocalDateTime(),
                    rs.getString("note")
                ));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
}
