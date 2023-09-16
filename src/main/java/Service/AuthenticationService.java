package Service;

import java.sql.SQLException;

import Model.Account;

public interface AuthenticationService {
    Account loginAccount(String username, String password) throws SQLException;
}
