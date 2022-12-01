package bank;

import javax.security.auth.login.LoginException;

public class Authenticator {
  public static Customer doLogin(String userName, String password) throws LoginException {
    Customer customer = DataSource.getCustomer(userName);

    if (customer == null) {
      throw new LoginException("User not found");
    }

    if (customer != null && customer.getPassword().equals(password)) {
      customer.setIsAuthenticated(true);
      return customer;
    } else {
      throw new LoginException("Incorrect Password");
    }
  }

  public static void doLogout(Customer customer) {
    customer.setIsAuthenticated(false);
  }
}
