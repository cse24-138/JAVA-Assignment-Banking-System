package dao;

import model.Customer;
import util.DBUtil;

import java.sql.*;
import java.util.Optional;

public class CustomerDAO {
    public Customer save(Customer c) {
        String sql = "INSERT INTO customers(first_name,last_name,address,email,password_hash) VALUES(?,?,?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getPasswordHash());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) c.setId(rs.getLong(1)); }
            return c;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public Optional<Customer> findByEmail(String email) {
        String sql = "SELECT * FROM customers WHERE email=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Customer(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("address"),
                    rs.getString("email"),
                    rs.getString("password_hash")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}
