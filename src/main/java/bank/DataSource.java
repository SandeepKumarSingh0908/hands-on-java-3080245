package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String dbFile = "jdbc:sqlite:resources/bank.db";
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dbFile);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return conn;

  }

  public static Customer getCustomer(String userName) {
    String sql = "select * from customers where username = ?";
    Customer customer = null;
    try (Connection conn = connect(); PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, userName);

      try (ResultSet resultSet = statement.executeQuery()) {
        customer = new Customer(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"),
            resultSet.getString("password"), resultSet.getInt("account_id"));
      } catch (SQLException e) {
        e.printStackTrace();
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customer;
  }

  public static void main(String[] args) {

    Customer customer = getCustomer("telloy3x@bigcartel.com");

    if (customer != null) {
      System.out.println(customer.getName());
    }

  }
}
