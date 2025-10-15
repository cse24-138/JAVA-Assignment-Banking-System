package util;

import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:h2:~/bankingdb;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
            try (Connection c = getConnection(); Statement st = c.createStatement()) {
                st.execute("""
                    CREATE TABLE IF NOT EXISTS customers(
                      id IDENTITY PRIMARY KEY,
                      first_name VARCHAR(60),
                      last_name  VARCHAR(60),
                      address    VARCHAR(120),
                      email      VARCHAR(80) UNIQUE,
                      password_hash VARCHAR(100)
                    )
                """);
                st.execute("""
                    CREATE TABLE IF NOT EXISTS accounts(
                      account_no VARCHAR(30) PRIMARY KEY,
                      branch     VARCHAR(60) NOT NULL,
                      balance    DOUBLE NOT NULL,
                      type       VARCHAR(20) NOT NULL, -- SavingsAccount | InvestmentAccount | ChequeAccount
                      customer_id BIGINT NOT NULL,
                      employer_name VARCHAR(100),
                      employer_address VARCHAR(150),
                      CONSTRAINT fk_accounts_customer FOREIGN KEY(customer_id) REFERENCES customers(id)
                    )
                """);
                st.execute("""
                    CREATE TABLE IF NOT EXISTS transactions(
                      id IDENTITY PRIMARY KEY,
                      account_no VARCHAR(30) NOT NULL,
                      type       VARCHAR(12) NOT NULL, -- DEPOSIT | WITHDRAW | INTEREST
                      amount     DOUBLE NOT NULL,
                      ts         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      note       VARCHAR(200),
                      CONSTRAINT fk_tx_acct FOREIGN KEY(account_no) REFERENCES accounts(account_no)
                    )
                """);
            }
        } catch (Exception e) {
            throw new RuntimeException("DB init failed", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
